package com.example.pocketreceipt;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    TextView date,store,store_location,store_phone,total,item_1,item_2, item_1_price,item_2_price,receiptNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        date = findViewById(R.id.view_date);
        item_1 = findViewById(R.id.item_1);
        item_2 = findViewById(R.id.item_2);
        item_1_price = findViewById(R.id.item_1_price);
        item_2_price = findViewById(R.id.item_2_price);
        store = findViewById(R.id.view_store);
        store_location = findViewById(R.id.store_location);
        store_phone = findViewById(R.id.store_phone);
        total = findViewById(R.id.view_total );
        receiptNo = findViewById(R.id.view_receipt_no );

        date.setText(getIntent().getStringExtra("date")+"");
        item_1.setText(getIntent().getStringExtra("item_1")+"");
        item_2.setText(getIntent().getStringExtra("item_2")+"");
        item_1_price.setText(getIntent().getStringExtra("item_1_price")+"");
        item_2_price.setText(getIntent().getStringExtra("item_2_price")+"");
        store.setText(getIntent().getStringExtra("store")+"");
        store_location.setText(getIntent().getStringExtra("store_location")+"");
        store_phone.setText(getIntent().getStringExtra("store_phone")+"");
        total.setText(getIntent().getStringExtra("total")+"");
        receiptNo.setText(getIntent().getStringExtra("receiptNo")+"");

    }
}