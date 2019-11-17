package com.fsit.englishpractice;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;




public class AboutActivity extends AppCompatActivity {


    private TextView fsit,fsit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textView = (TextView) findViewById(R.id.fulltext);
        /*Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Black.ttf");
        textView.setTypeface(typeface);*/
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        //fsit=findViewById(R.id.fsit);


       /* String text="Friends Software IT";
        SpannableString  ss=new SpannableString(text);
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.WHITE);

            }
        };

        ss.setSpan(clickableSpan,0,19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        fsit.setText(ss);
        fsit.setMovementMethod(LinkMovementMethod.getInstance());
*/


       /* fsit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   fsitfaceboook();
            }
        });*/
        fsit2=findViewById(R.id.fsitgmail);
        fsit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       sendEmail();
            }
        });


    }

    private void fsitEmail() {
        Log.i("Send email", "");
        String[] TO = {"friendssoftwareitofficial@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "About Friends Software IT");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hlw \n");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AboutActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    public void fsitfaceboook(){
        String url = "https://www.facebook.com/friendssoftwareIt/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

        Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();
    }
    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"homedecoration009@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "About English Practice");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "This is very good and useful apps.\n");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AboutActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       /* int id = item.getItemId();
        if (id == android.R.id.home)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }*/

        return super.onOptionsItemSelected(item);
    }
}
