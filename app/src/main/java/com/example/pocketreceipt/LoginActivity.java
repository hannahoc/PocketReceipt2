package com.example.pocketreceipt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    //Firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    //facebook
    private CallbackManager callbackManager;
    //xml
    TextInputEditText uEmail, uPassword;
    TextView tLogin;
    Button LoginBtn2, btnResetPassword, SignUpBtn;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        uEmail = findViewById( R.id.uEmail );
        uPassword = findViewById( R.id.uPassword );
        tLogin = findViewById( R.id.tLogin );
        btnResetPassword = findViewById( R.id.btn_reset_password );
        SignUpBtn = findViewById( R.id.SignUpBtn );
        LoginBtn2 = findViewById( R.id.LoginBtn2 );
        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        LoginBtn2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = uEmail.getText().toString().trim();
                String password = uPassword.getText().toString().trim();

                if (TextUtils.isEmpty( email )) {
                    uEmail.setError( "Email is Required." );
                    return;
                }

                if (TextUtils.isEmpty( password )) {
                    uPassword.setError( "Password is Required." );
                    return;
                }

                if (password.length() < 6) {
                    uPassword.setError( "Password Must be >= 6 Characters" );
                    return;
                }

                //     progressBar.setVisibility(View.VISIBLE);

                // authenticate the user

                fAuth.signInWithEmailAndPassword( email, password ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText( LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT ).show();
                            startActivity( new Intent( getApplicationContext(), MenuActivity.class ) );
                        } else {
                            Toast.makeText( LoginActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT ).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                } );

            }
        } );


        btnResetPassword.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( LoginActivity.this, ResetPasswordActivity.class ) );
            }
        } );

        SignUpBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( LoginActivity.this, SignUpActivity.class ) );
            }
        } );



//facebook login
        LoginButton loginButton = findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setPermissions(Arrays.asList("email", "public profile"));


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }


}

