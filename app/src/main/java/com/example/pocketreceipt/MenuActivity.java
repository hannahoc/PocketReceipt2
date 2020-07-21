//Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
//Icons made by <a href="" title="fjstudio">fjstudio</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
//Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
package com.example.pocketreceipt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button logout;
    private Button receipt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        receipt = (Button) findViewById(R.id.receipt);
        receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReceiptActivity();
            }
        });
    }
    public void openReceiptActivity(){
        Intent intent=new Intent(this, ReceiptActivity.class);
        startActivity(intent);

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(MenuActivity.this, "Successfully Logged out", Toast.LENGTH_SHORT).show();
            }

        });
    }

}