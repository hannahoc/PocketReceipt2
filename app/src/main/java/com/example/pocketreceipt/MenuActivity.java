//Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
//Icons made by <a href="" title="fjstudio">fjstudio</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
//Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
package com.example.pocketreceipt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView receipts, codeQR, charts, calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu );
        receipts = (CardView) findViewById( R.id.receipts );
        codeQR = (CardView) findViewById( R.id.codeQR );
        charts = (CardView) findViewById( R.id.charts );
        calendar = (CardView) findViewById( R.id.calendar );
        //add listener
        receipts.setOnClickListener( this );
        codeQR.setOnClickListener( this );
        charts.setOnClickListener( this );
        calendar.setOnClickListener( this );


    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.receipts: i = new Intent( this, ReceiptActivity.class );startActivity( i ); break;
            case R.id.codeQR: i = new Intent( this, ReceiptActivity.class );;startActivity( i ); break;
            case R.id.charts: i = new Intent( this, CodeQR.class );startActivity( i ); break;
            case R.id.calendar: i = new Intent( this, Calendar.class );startActivity( i );break;
            default:break;


        }
    }
}