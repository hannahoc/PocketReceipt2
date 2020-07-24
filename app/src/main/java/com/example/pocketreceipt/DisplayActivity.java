package com.example.pocketreceipt;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    TextView date,store,total, bed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        date = findViewById(R.id.view_date);
        bed = findViewById(R.id.view_bed);
        store = findViewById(R.id.view_store);
        total = findViewById(R.id.view_total );

        date.setText(getIntent().getStringExtra("date")+"");
        bed.setText(getIntent().getStringExtra("bed")+"");
        store.setText(getIntent().getStringExtra("store")+"");
        total.setText(getIntent().getStringExtra("total")+"");
        
    }
}
