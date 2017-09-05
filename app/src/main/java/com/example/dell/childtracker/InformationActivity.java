package com.example.dell.childtracker;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity
{
    TextView tvTitle;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        this.setTitle("About The App");

        tvTitle=(TextView) findViewById(R.id.textViewTitle);

        tf1=Typeface.createFromAsset(getAssets(),"Billabong.ttf");
        tvTitle.setTypeface(tf1);
    }
}
