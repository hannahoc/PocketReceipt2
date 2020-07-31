package com.example.pocketreceipt;

import android.os.Bundle;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class Calendar extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference receiptRef = db.collection( "users" )
            .document( "D40pvQ5PuOT6NaiFfQiNN87z8Qb2" )
            .collection( "Receipts" );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_calendar );

        CalendarView calendarView = findViewById( R.id.calendarView2 );
        java.util.Calendar c_now = java.util.Calendar.getInstance();
        try {
            calendarView.setDate( c_now );
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        receiptRef.get().addOnSuccessListener( new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documents =  queryDocumentSnapshots.getDocuments();
                List<EventDay> events = new ArrayList<EventDay>( );
                SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
                java.util.Calendar c = java.util.Calendar.getInstance();

                for (DocumentSnapshot document : documents){
                    String date = document.get( "date" ).toString();
                    try {
                        c.setTime( sdf.parse( date ) );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    events.add( new EventDay( c, R.drawable.sample_circle));
                }
                calendarView.setEvents( events );
                try {
                    java.util.Calendar c_now = java.util.Calendar.getInstance();
                   // c_now.add( java.util.Calendar.DATE, 10 );
                    calendarView.setDate( c_now );
                } catch (OutOfDateRangeException e) {
                    e.printStackTrace();
                }
            }
        } );

    }
}