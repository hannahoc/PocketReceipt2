package com.example.pocketreceipt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    TextView fullName, email, phone;
    Button reset_password, logout;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        fullName = findViewById( R.id.fullName );
        email = findViewById( R.id.email_txt );
        phone = findViewById( R.id.phonetxt );
        reset_password = (Button) findViewById( R.id.reset_password );
        logout = (Button) findViewById( R.id.logout );
        profilePic = (ImageView) findViewById( R.id.profilePic );

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //firestore to get current user that is logged in and to follow the User path in Firestore to print out current users
        // email, full name and phone number
        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection( "users" ).document( userID );
        documentReference.addSnapshotListener( this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                email.setText( documentSnapshot.getString( "Email" ) );
                fullName.setText( documentSnapshot.getString( "Full Name" ) );
                phone.setText( documentSnapshot.getString( "Phone" ) );
            }
        } );
        // restset button links to reset password activity displayed on the login activity
        reset_password.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( Profile.this, ResetPasswordActivity.class ) );
            }
        } );
    }
//sign user out
        public void logout (View view) {
            Toast.makeText( this, "User logged out", Toast.LENGTH_SHORT ).show();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();

        }


    }

