package com.fsit.englishpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.valdesekamdem.library.mdtoast.MDToast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private TextView btnquiz1,btnquiz2,btnquiz3,btnquiz4,btnquiz5,btnquiz6,btnquiz7;
    private TextView notice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * firebaaes app connected
         * gmail=azlearning007@gmail.com
         * firebase apps name=English Practice
         * developer=Fozle Rabbi
         *
         */

        btnquiz1=findViewById(R.id.quiz1);
        btnquiz2=findViewById(R.id.quiz2);
        btnquiz3=findViewById(R.id.quiz3);
        btnquiz4=findViewById(R.id.quiz4);
        btnquiz5=findViewById(R.id.quiz5);
        btnquiz6=findViewById(R.id.quiz6);
        btnquiz7=findViewById(R.id.quiz7);

        notice=findViewById(R.id.tvnotice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin Notice");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              notice.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        navigationView.setNavigationItemSelectedListener(this);
        btnquiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Quiz1Activity.class));
                finish();
            }
        });
        btnquiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Quiz2Activity.class));
                finish();
            }
        });
        btnquiz3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Quiz3Activity.class));
                finish();
            }
        });
        btnquiz4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Quiz4Activity.class));
                finish();
            }
        });
        btnquiz5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Quiz5Activity.class));
                finish();
            }
        });
        btnquiz6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Quiz6Activity.class));
                finish();
            }
        });

        btnquiz7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Quiz7Activity.class));
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuprivacypolicy)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/1Ek0mHpvULmYKYrx428DE2zNuwn9I7eF4hY9TpnRw2Qk/edit?fbclid=IwAR09Q-xtAMzu-QYSZnnU84vH_YiM1CAazBnKNH2dz4mMlN08EKdxmAG-RkE")));
            Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.menushare) {
           shareAppLink();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_abaout) {
            // Handle the camera action
            startActivity(new Intent(getApplicationContext(),AboutActivity.class));
            finish();
        }
        else if (id == R.id.nav_privacypolicy)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/1Ek0mHpvULmYKYrx428DE2zNuwn9I7eF4hY9TpnRw2Qk/edit?fbclid=IwAR09Q-xtAMzu-QYSZnnU84vH_YiM1CAazBnKNH2dz4mMlN08EKdxmAG-RkE")));
            MDToast.makeText(getApplicationContext(),"Please wait......",MDToast.LENGTH_SHORT).show();
        }

        else if (id == R.id.nav_moreapps)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=F%20%26%20E&fbclid=IwAR0dkkEXXShz8o4Xe7GfxwZvG5xZ503bDzqxWMmwMJy0QvS4RhhwzU-TKMQ")));
            MDToast.makeText(getApplicationContext(),"Please wait......",MDToast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_rateus)
        {
            final String appPackageName = getPackageName();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+appPackageName)));
            MDToast.makeText(getApplicationContext(),"Please wait......",MDToast.LENGTH_SHORT).show();
        }else if(id==R.id.nav_share){
            shareAppLink();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void shareAppLink(){
        final String appPackageName = getPackageName();
        Intent sendIntent =new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Download app");
        sendIntent.putExtra(Intent.EXTRA_TEXT,"Hello I am useing apps name English Practice for app download it from play store,"
                +"check out the App at: https://play.google.com/store/apps/details?id="+appPackageName);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}