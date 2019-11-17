package com.fsit.englishpractice;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class Quiz1Activity extends AppCompatActivity {
    private Button b1,b2,b3,b4;
    private TextView position,pquestion,questiontext,qudetails;
    int p=0;
    int correct=0;
    int wrong=0;
    DatabaseReference reference;
    private TextToSpeech tts;
    private SharedPreferences preferences;
    private SharedPreferences.Editor edit;

    private SharedPreferences preferencesj7;
    private SharedPreferences.Editor editk7;

    private SharedPreferences preferencesl7;
    private SharedPreferences.Editor editm7;


    long backPressedTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



    /*    AudienceNetworkAds.initialize(this);
        AdView adView = new AdView(getApplicationContext(),getString(R.string.bannerads) , AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();*/


        RelativeLayout adContainer =findViewById(R.id.banner_container);
        AdView adView = new AdView(getApplicationContext());
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getString(R.string.bannerads));
        // Initiate a generic request to load it with an ad
        adView.loadAd(new AdRequest.Builder().build());
        // Place the ad view.
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adContainer.addView(adView, params);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
            }
        });


        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b4=findViewById(R.id.button4);

        position=findViewById(R.id.positionid);
        qudetails=findViewById(R.id.qudetails);
        questiontext=findViewById(R.id.questiontextid);

        // quiz  preferences
        preferences=getSharedPreferences("rabbi1",MODE_PRIVATE);
        edit=getSharedPreferences("rabbi1",MODE_PRIVATE).edit();
        p=preferences.getInt("s1",0);

        // correct preferences
        preferencesj7=getSharedPreferences("correctf1",MODE_PRIVATE);
        editk7=getSharedPreferences("correctf1",MODE_PRIVATE).edit();
        correct=preferencesj7.getInt("c1",0);

        // wrong preferences
        preferencesl7=getSharedPreferences("wrong1",MODE_PRIVATE);
        editm7=getSharedPreferences("wrong1",MODE_PRIVATE).edit();
        wrong=preferencesl7.getInt("r1",0);
enable_button(false);
        updatequestion();



    }


    public  void updatequestion(){
        p=p+1;
        if(p<=100)
        {
            enable_button(true);
            reference= FirebaseDatabase.getInstance().getReference().child("Grammar Practice 1").child(String.valueOf(p));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question =dataSnapshot.getValue(Question.class);
                    position.setText("Question:"+p+"/"+"100");
                    qudetails.setText(question.getQdetails());
                    questiontext.setText(question.getQuestion());
                    b1.setText(question.getOption1());
                    b2.setText(question.getOption2());
                    b3.setText(question.getOption3());
                    b4.setText(question.getOption4());





                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            enable_button(false);

                            if (b1.getText().toString().equals(question.getAnswer())){

                                b1.setBackgroundColor(Color.GREEN);

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct=correct+1;

                                        // correct cont
                                        editk7.putInt("c1",correct);
                                        Log.i("1233212",""+correct);
                                        editk7.commit();
                                        editk7=getSharedPreferences("correctf1",MODE_PRIVATE).edit();
                                        correct=preferencesj7.getInt("c1",0);

                                        // quiz cont
                                        edit.putInt("s1",p);
                                        Log.i("1233212",""+p);
                                        edit.commit();
                                        edit=getSharedPreferences("rabbi1",MODE_PRIVATE).edit();
                                        p=preferences.getInt("s1",0);
                                        updatequestion();
                                        if(p<100){
                                            startActivity(new Intent(getApplicationContext(),Quiz1Activity.class));
                                            finish();
                                        }
                                        b1.setBackgroundColor(Color.parseColor("#F03524"));
                                    }
                                },1500);
                            }else {
                                wrong++;
                                // wrong cont
                                editm7.putInt("r1",wrong);
                                Log.i("1233212",""+wrong);
                                editm7.commit();
                                editm7=getSharedPreferences("wrong1",MODE_PRIVATE).edit();
                                wrong=preferencesl7.getInt("r1",0);

                                b1.setBackgroundColor(Color.RED);

                                if (b2.getText().toString().equals(question.getAnswer())){

                                    b2.setBackgroundColor(Color.GREEN);
                                }else if(b3.getText().toString().equals(question.getAnswer())){

                                    b3.setBackgroundColor(Color.GREEN);

                                }else if(b4.getText().toString().equals(question.getAnswer())){

                                    b4.setBackgroundColor(Color.GREEN);

                                }


                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        edit.putInt("s1",p);
                                        Log.i("1233212",""+p);
                                        edit.commit();
                                        edit=getSharedPreferences("rabbi1",MODE_PRIVATE).edit();
                                        p=preferences.getInt("s1",0);
                                        updatequestion();
                                        if(p<100){
                                            startActivity(new Intent(getApplicationContext(),Quiz1Activity.class));
                                            finish();
                                        }

                                        b1.setBackgroundColor(Color.parseColor("#F03524"));
                                        b2.setBackgroundColor(Color.parseColor("#F03524"));
                                        b3.setBackgroundColor(Color.parseColor("#F03524"));
                                        b4.setBackgroundColor(Color.parseColor("#F03524"));



                                    }
                                },1500);

                            }


                        }
                    });



                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
enable_button(false);
                            if (b2.getText().toString().equals(question.getAnswer())){


                                b2.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct=correct+1;

                                        // correct cont
                                        editk7.putInt("c1",correct);
                                        Log.i("1233212",""+correct);
                                        editk7.commit();
                                        editk7=getSharedPreferences("correctf1",MODE_PRIVATE).edit();
                                        correct=preferencesj7.getInt("c1",0);

                                        // quiz cont
                                        edit.putInt("s1",p);
                                        Log.i("1233212",""+p);
                                        edit.commit();
                                        edit=getSharedPreferences("rabbi1",MODE_PRIVATE).edit();
                                        p=preferences.getInt("s1",0);
                                        updatequestion();
                                        if(p<100){
                                            startActivity(new Intent(getApplicationContext(),Quiz1Activity.class));
                                            finish();
                                        }

                                        b2.setBackgroundColor(Color.parseColor("#F03524"));
                                    }
                                },1500);

                            }else {

                                wrong++;

                                // wrong cont
                                editm7.putInt("r1",wrong);
                                Log.i("1233212",""+wrong);
                                editm7.commit();
                                editm7=getSharedPreferences("wrong1",MODE_PRIVATE).edit();
                                wrong=preferencesl7.getInt("r1",0);

                                b2.setBackgroundColor(Color.RED);

                                if (b1.getText().toString().equals(question.getAnswer())){

                                    b1.setBackgroundColor(Color.GREEN);
                                }else if(b3.getText().toString().equals(question.getAnswer())){

                                    b3.setBackgroundColor(Color.GREEN);

                                }else if(b4.getText().toString().equals(question.getAnswer())){

                                    b4.setBackgroundColor(Color.GREEN);

                                }


                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        edit.putInt("s1",p);
                                        Log.i("1233212",""+p);
                                        edit.commit();

                                        edit=getSharedPreferences("rabbi1",MODE_PRIVATE).edit();
                                        p=preferences.getInt("s1",0);
                                        updatequestion();
                                        if(p<100){
                                            startActivity(new Intent(getApplicationContext(),Quiz1Activity.class));
                                            finish();
                                        }
//
//                                            b1.setBackgroundColor(R.drawable.quizbtngradent);
//                                            b2.setBackgroundColor(R.drawable.quizbtngradent);
//                                            b3.setBackgroundColor(R.drawable.quizbtngradent);
//                                            b4.setBackgroundColor(R.drawable.quizbtngradent);


                                        b1.setBackgroundColor(Color.parseColor("#F03524"));
                                        b2.setBackgroundColor(Color.parseColor("#F03524"));
                                        b3.setBackgroundColor(Color.parseColor("#F03524"));
                                        b4.setBackgroundColor(Color.parseColor("#F03524"));



                                    }
                                },1500);

                            }


                        }
                    });

                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

enable_button(false);
                            if (b3.getText().toString().equals(question.getAnswer())){


                                b3.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct=correct+1;

                                        // correct cont
                                        editk7.putInt("c1",correct);
                                        Log.i("1233212",""+correct);
                                        editk7.commit();
                                        editk7=getSharedPreferences("correctf1",MODE_PRIVATE).edit();
                                        correct=preferencesj7.getInt("c1",0);

                                        // quiz cont
                                        edit.putInt("s1",p);
                                        Log.i("1233212",""+p);
                                        edit.commit();
                                        edit=getSharedPreferences("rabbi1",MODE_PRIVATE).edit();
                                        p=preferences.getInt("s1",0);
                                        updatequestion();
                                        if(p<100){
                                            startActivity(new Intent(getApplicationContext(),Quiz1Activity.class));
                                            finish();
                                        }

                                        b3.setBackgroundColor(Color.parseColor("#F03524"));
                                    }
                                },1500);

                            }else {

                                wrong++;
                                // wrong cont
                                editm7.putInt("r1",wrong);
                                Log.i("1233212",""+wrong);
                                editm7.commit();
                                editm7=getSharedPreferences("wrong1",MODE_PRIVATE).edit();
                                wrong=preferencesl7.getInt("r1",0);

                                b3.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(question.getAnswer())){

                                    b1.setBackgroundColor(Color.GREEN);
                                }else if(b2.getText().toString().equals(question.getAnswer())){

                                    b2.setBackgroundColor(Color.GREEN);

                                }else if(b4.getText().toString().equals(question.getAnswer())){

                                    b4.setBackgroundColor(Color.GREEN);

                                }


                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        edit.putInt("s1",p);
                                        Log.i("1233212",""+p);
                                        edit.commit();
                                        edit=getSharedPreferences("rabbi1",MODE_PRIVATE).edit();
                                        p=preferences.getInt("s1",0);
                                        updatequestion();
                                        if(p<100){
                                            startActivity(new Intent(getApplicationContext(),Quiz1Activity.class));
                                            finish();
                                        }
//
//                                            b1.setBackgroundColor(R.drawable.quizbtngradent);
//                                            b2.setBackgroundColor(R.drawable.quizbtngradent);
//                                            b3.setBackgroundColor(R.drawable.quizbtngradent);
//                                            b4.setBackgroundColor(R.drawable.quizbtngradent);

                                        b1.setBackgroundColor(Color.parseColor("#F03524"));
                                        b2.setBackgroundColor(Color.parseColor("#F03524"));
                                        b3.setBackgroundColor(Color.parseColor("#F03524"));
                                        b4.setBackgroundColor(Color.parseColor("#F03524"));

                                    }
                                },1500);

                            }

                        }
                    });


                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

enable_button(false);
                            if (b4.getText().toString().equals(question.getAnswer())){


                                b4.setBackgroundColor(Color.GREEN);

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct=correct+1;

                                        // correct cont
                                        editk7.putInt("c1",correct);
                                        Log.i("1233212",""+correct);
                                        editk7.commit();
                                        editk7=getSharedPreferences("correctf1",MODE_PRIVATE).edit();
                                        correct=preferencesj7.getInt("c1",0);

                                        // quiz cont
                                        edit.putInt("s1",p);
                                        Log.i("1233212",""+p);
                                        edit.commit();

                                        edit=getSharedPreferences("rabbi1",MODE_PRIVATE).edit();
                                        p=preferences.getInt("s1",0);
                                        updatequestion();
                                        if(p<100){
                                            startActivity(new Intent(getApplicationContext(),Quiz1Activity.class));
                                            finish();
                                        }

                                        b4.setBackgroundColor(Color.parseColor("#F03524"));
                                    }
                                },1500);


                            }else {

                                wrong++;
                                // wrong cont
                                editm7.putInt("r1",wrong);
                                Log.i("1233212",""+wrong);
                                editm7.commit();
                                editm7=getSharedPreferences("wrong1",MODE_PRIVATE).edit();
                                wrong=preferencesl7.getInt("r1",0);

                                b4.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(question.getAnswer())){

                                    b1.setBackgroundColor(Color.GREEN);
                                }else if(b3.getText().toString().equals(question.getAnswer())){

                                    b3.setBackgroundColor(Color.GREEN);

                                }else if(b2.getText().toString().equals(question.getAnswer())){

                                    b2.setBackgroundColor(Color.GREEN);

                                }


                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        edit.putInt("s1",p);
                                        Log.i("1233212",""+p);
                                        edit.commit();
                                        edit=getSharedPreferences("rabbi1",MODE_PRIVATE).edit();
                                        p=preferences.getInt("s1",0);
                                        updatequestion();
                                        if(p<100){
                                            startActivity(new Intent(getApplicationContext(),Quiz1Activity.class));
                                            finish();
                                        }

//                                            b1.setBackgroundColor(R.drawable.quizbtngradent);
//                                            b2.setBackgroundColor(R.drawable.quizbtngradent);
//                                            b3.setBackgroundColor(R.drawable.quizbtngradent);
//                                            b4.setBackgroundColor(R.drawable.quizbtngradent);
                                        b1.setBackgroundColor(Color.parseColor("#F03524"));
                                        b2.setBackgroundColor(Color.parseColor("#F03524"));
                                        b3.setBackgroundColor(Color.parseColor("#F03524"));
                                        b4.setBackgroundColor(Color.parseColor("#F03524"));


                                    }
                                },1500);

                            }

                        }
                    });


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else{

            result();
            reset();

        }


    }
    public void result(){
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.quizresult);
        dialog.setCancelable(false);
        final TextView tvcorrect = dialog.findViewById(R.id.tvcorrct);
        TextView tvwrong = dialog.findViewById(R.id.tvwrong);
        Button button=dialog.findViewById(R.id.btnback);
        String co=Integer.toString(correct);
        String wr=Integer.toString(wrong);
        tvcorrect.setText("Correct:"+co);
        tvwrong.setText("Wrong:"+wr);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    dialog.dismiss();

                Intent intent=new Intent(getApplicationContext(),Quiz1Activity.class);
                startActivity(intent);
                correcReset();
                wrongReset();
                finish();
            }
        });

        dialog.show();
    }
    public void waitm(){
        Date date=new Date();
        long l2= System.currentTimeMillis()+2*60*60*1000;
        SharedPreferences.Editor editor=getSharedPreferences("time",MODE_PRIVATE).edit();
        editor.putString("time",l2+"");
        editor.commit();
    }
    private void finishQuiz () {
//            Intent resultIntent = new Intent();
//            resultIntent.putExtra(EXTRA_SCORE, correct);
//            setResult(RESULT_OK, resultIntent);
//            finish();

        if(p==100){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            correcReset();
            wrongReset();
        }else
         {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


    }

    @Override
    public void onBackPressed ()
    {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    public  void reset(){
        edit.putInt("s1",0);
        Log.i("1233211","reset success");
        edit.commit();
        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
    public  void correcReset(){
        editk7.putInt("c1",0);
        Log.i("1233211","reset success");
        editk7.commit();

    }
    public  void wrongReset(){
        editm7.putInt("r1",0);
        Log.i("1233211","reset success");
        editm7.commit();

    }
    public  void  enable_button(boolean b){
        b1.setEnabled(b);
        b1.setClickable(b);
        b3.setClickable(b);
        b2.setClickable(b);
        b4.setClickable(b);
    }
}