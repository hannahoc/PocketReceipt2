package com.example.pocketreceipt;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.NonNull;
import android.text.TextUtils;


import java.util.HashMap;
import java.util.Map;

import static com.example.pocketreceipt.R.layout.activity_sign_up;



public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    // Declare what fields ive used and assign variable names
    EditText Email, Password;
    Button btnSignIn, btnSignUp, btnResetPassword;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_sign_up);

        // Initiate each variable by getting them by ID

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        btnSignIn =  (Button) findViewById(R.id.sign_in_button);
        btnSignUp =  (Button) findViewById(R.id.sign_up_button);
        btnResetPassword =  (Button) findViewById(R.id.btn_reset_password);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        // Check if user is already registered or is a returning user
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        // Register the user if the right username password etc are inputted.
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    Email.setError("Email Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Password.setError("Password required");
                    return;
                }
                if(password.length() < 6){
                    Password.setError("Password must be at least 6 characters long!!");
                }
                /// Make checks for special character and a capital letter in password

                progressBar.setVisibility(View.VISIBLE);

                btnResetPassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SignUpActivity.this, ResetPasswordActivity.class));
                    }
                });


                btnSignIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    }
                });

                /// Register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                            //Store user data by userID in document
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            // Add in more user info if needed.

                            user.put("Email", email);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: User profile is created for: " + userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(SignUpActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
