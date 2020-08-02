//Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
//Icons made by <a href="" title="fjstudio">fjstudio</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
//Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
package com.example.pocketreceipt;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private CardView receipts, codeQR, calendar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseAuth fAuth;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu );



        /*---------------------Hooks------------------------*/
        drawerLayout = findViewById( R.id.drawerLayout );
        navigationView = findViewById( R.id.nav_view );
        toolbar = findViewById( R.id.toolbar );
        receipts = (CardView) findViewById( R.id.receipts );
        codeQR = (CardView) findViewById( R.id.codeQR );
        calendar = (CardView) findViewById( R.id.calendar );

        //add onclicklistener to direct dashboard card to correct java
        receipts.setOnClickListener( this );
        codeQR.setOnClickListener( this );
        calendar.setOnClickListener( this );


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawerLayout.addDrawerListener( toggle );
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener( this );
        navigationView.setCheckedItem( R.id.nav_dash );

    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.receipts:
                i = new Intent( this, ReceiptActivity.class );
                startActivity( i );
                break;
            case R.id.codeQR:
                i = new Intent( this, CodeQR.class );
                startActivity( i );
                break;
            case R.id.calendar:
                i = new Intent( this, Calendar.class );
                startActivity( i );
                break;
            default:
                break;


        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen( GravityCompat.START )) {
            drawerLayout.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
//                case R.id.nav_dash:
//                    break;
            case R.id.nav_dash:
                Intent i = new Intent( MenuActivity.this, MenuActivity.class );
                startActivity( i );
                break;
            case R.id.nav_codeQR:
                i = new Intent( MenuActivity.this, CodeQR.class );
                startActivity( i );
                break;
            case R.id.nav_receipts:
                i = new Intent( MenuActivity.this, ReceiptActivity.class );
                startActivity( i );
                break;
            case R.id.nav_calendar:
                i = new Intent( MenuActivity.this, Calendar.class );
                startActivity( i );
                break;
            case R.id.nav_profile:
                i = new Intent( MenuActivity.this, Profile.class );
                startActivity( i );
                break;
            //sign user out
            case R.id.nav_logout:
                Toast.makeText( this, "User logged out", Toast.LENGTH_SHORT ).show();
                FirebaseAuth.getInstance().signOut();
                startActivity( new Intent( getApplicationContext(), LoginActivity.class ) );
                finish();
                break;
            case R.id.nav_feedback:
                i = new Intent( MenuActivity.this, Feedback.class );
                startActivity( i );
                break;
        }
        return true;
    }
}




