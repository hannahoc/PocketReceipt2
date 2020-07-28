package com.example.pocketreceipt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {

    private EditText pEmail, pSubject, pFeedback;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_feedback );

        pEmail = findViewById( R.id. email_text);
        pSubject = findViewById( R.id. subject_text);
        pFeedback = findViewById( R.id. feedback_text);

        Button submit = findViewById( R.id.submitBtn );
        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        } );
    }

    private void sendMail() {
        String recipientList = pEmail.getText().toString();
        String recipients = "pocketreceipt@info.ie";


        String subject = pSubject.getText().toString();
        String message = pFeedback.getText().toString();

        Intent intent = new Intent( Intent.ACTION_SEND);
//        intent.putExtra(Intent.EXTRA_EMAIL, "pocketreceipt@info.ie");
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
//only open email clients
        intent.setType( "message/rfc822" );
        startActivity( Intent.createChooser( intent, "Select an email client" ) );

    }
}