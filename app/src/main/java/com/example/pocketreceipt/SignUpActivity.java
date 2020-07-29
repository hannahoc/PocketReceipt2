package com.example.pocketreceipt;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.pocketreceipt.R.layout.activity_sign_up;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    // Declare what fields ive used and assign variable names
    TextInputLayout Email, Password, FullName, Phone;
    Button btnSignIn, btnSignUp;
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
        FullName = findViewById(R.id.fName);
        Phone = findViewById(R.id.phone);
        Password = findViewById(R.id.password);
        btnSignIn =  (Button) findViewById(R.id.btnSignIn);
        btnSignUp =  (Button) findViewById(R.id.btnSignUp);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

//        // Check if user is already registered or is a returning user
//        if(fAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

        // Register the user if the right username password etc are inputted.
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // final String email = Email.getText().toString().trim();
                String email=Email.getEditText().getText().toString().trim();
                String fName=FullName.getEditText().getText().toString().trim();
                String phone =Phone.getEditText().getText().toString().trim();
                //String password = Password.getText().toString().trim();
                String password=Password.getEditText().getText().toString().trim();


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
                            // Adding users email, name and phone number to firestore(will be used later in Profile.java)

                            user.put("Email", email);
                            user.put("Full Name",fName);
                            user.put("Phone",phone);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: User profile is created for: " + userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
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