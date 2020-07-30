package com.example.pocketreceipt;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidx.appcompat.app.AppCompatActivity;

public class CodeQR extends AppCompatActivity {

    Button generateBtn;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_code_q_r );


        generateBtn = (Button) findViewById( R.id.generate_btn );
        qrImage = (ImageView) findViewById( R.id.imageview );

        generateBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //you can put a check here for null user
                setupUserQRcode( FirebaseAuth.getInstance().getCurrentUser().getUid() );

            }
        } );
    }

    private void setupUserQRcode(String firebaseUID) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode( firebaseUID, BarcodeFormat.QR_CODE, 500, 500 );
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap( bitMatrix );
            qrImage.setImageBitmap( bitmap );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
