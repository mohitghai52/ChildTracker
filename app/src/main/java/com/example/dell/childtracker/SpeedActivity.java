package com.example.dell.childtracker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class SpeedActivity extends AppCompatActivity implements View.OnClickListener, NumberPicker.OnValueChangeListener
{
    LinearLayout linearLayout1;
    TextView tvDisplaySpeed1;
    NumberPicker npSpeed1;
    Button btnNext;

    LinearLayout linearLayout2;
    TextView tvDisplaySpeed2;
    NumberPicker npSpeed2;
    Button btnSubmit;

    LinearLayout linearLayout3;
    TextView tvSpeed1Is;
    TextView tvSpeed2Is;
    Button btnEdit;

    int speed1,speed2;
    int getSpeed1,getSpeed2;

    EditText etPassword;

    SharedPreferences preferences,preferencesRegistration;
    SharedPreferences.Editor editor;

    Button btnOk,btnCancel;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);


        ActionBar bar=this.getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#cee7fc")));
        bar.setTitle(Html.fromHtml("<font color='#000000'>Set Speed </font>"));


        linearLayout1=(LinearLayout) findViewById(R.id.LinearLayout1);
        linearLayout2=(LinearLayout) findViewById(R.id.LinearLayout2);
        linearLayout3=(LinearLayout) findViewById(R.id.LinearLayout3);

        preferences=getSharedPreferences("Speed",MODE_PRIVATE);
        preferencesRegistration=getSharedPreferences("Registration Detail",MODE_PRIVATE);
        editor=preferences.edit();

        tvSpeed1Is=(TextView) findViewById(R.id.TextViewSpeed1Is);
        tvSpeed2Is=(TextView) findViewById(R.id.TextViewSpeed2Is) ;

        if(preferences.contains("keySpeed1") && preferences.contains("keySpeed2"))
        {
            linearLayout1.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.GONE);
            linearLayout3.setVisibility(View.VISIBLE);

            int get1=preferences.getInt("keySpeed1",0);
            tvSpeed1Is.setText("Speed 1: "+get1+" km/h");

            int get2=preferences.getInt("keySpeed2",0);
            tvSpeed2Is.setText("Speed 2: "+get2+" km/h");
        }

        initViews();
    }

    public void initViews()
    {
        tvDisplaySpeed1=(TextView) findViewById(R.id.textViewDisplaySpeed1);
        npSpeed1=(NumberPicker) findViewById(R.id.numberPickerSpeed1);
        btnNext=(Button) findViewById(R.id.buttonNext);

        tvDisplaySpeed2=(TextView) findViewById(R.id.textViewDisplaySpeed2);
        npSpeed2=(NumberPicker) findViewById(R.id.numberPickerSpeed2);
        btnSubmit=(Button) findViewById(R.id.buttonSubmit);

        btnEdit=(Button) findViewById(R.id.buttonEdit);

        String speed[]=new String[20];
        int start=10;

        for(int i=0;i<speed.length;i++)
        {
            speed[i]=start+"";
            start=start+10;
        }

        npSpeed1.setMaxValue(20);
        npSpeed1.setMinValue(1);
        npSpeed1.setWrapSelectorWheel(true);
        npSpeed1.setDisplayedValues(speed);

        npSpeed2.setMaxValue(20);
        npSpeed2.setMinValue(1);
        npSpeed2.setWrapSelectorWheel(true);
        npSpeed2.setDisplayedValues(speed);

        npSpeed1.setOnValueChangedListener(this);
        npSpeed2.setOnValueChangedListener(this);


        btnNext.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnEdit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view)
    {
        int id=view.getId();

        if(id==R.id.buttonNext)
        {
            if (speed1 == 0)
            {
                Toast.makeText(this, "Please Select Speed", Toast.LENGTH_SHORT).show();
            }

            else if(speed1==200)
            {
                Toast.makeText(this, "Speed 1 Cannot Be Maximum Speed ", Toast.LENGTH_SHORT).show();
            }

            else
            {
                editor.putInt("keySpeed1",speed1);
                editor.commit();

                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);
            }



        }

        if(id==R.id.buttonSubmit)
        {
            if(speed2==0)
            {
                Toast.makeText(this, "Please Select Speed", Toast.LENGTH_SHORT).show();

            }

            else if(speed2<=speed1)
            {
                Toast.makeText(this, "Speed 2 Must Be Greater Than Speed 1", Toast.LENGTH_SHORT).show();
            }

            else
            {
                editor.putInt("keySpeed2", speed2);
                editor.commit();

                getSpeed1=preferences.getInt("keySpeed1",0);
                tvSpeed1Is.setText("Speed 1: "+getSpeed1+" km/h");


                getSpeed2=preferences.getInt("keySpeed2",0);
                tvSpeed2Is.setText("Speed 2: "+getSpeed2+" km/h");

                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.VISIBLE);

                Intent intentService=new Intent(SpeedActivity.this,SpeedService.class);
                startService(intentService);
            }
        }

        if(id==R.id.buttonEdit)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Confirmation");
            builder.setIcon(R.drawable.ok);
            builder.setMessage("Are You Sure You Want To Edit");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    final Dialog dialog=new Dialog(SpeedActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
                    dialog.setContentView(R.layout.password_dialog);
                    dialog.setTitle("Password Confirmation");
                    dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.confirmpassword);
                    dialog.setCancelable(false);

                    etPassword=dialog.findViewById(R.id.editTextPassword);

                    btnOk=dialog.findViewById(R.id.buttonOk);
                    btnCancel=dialog.findViewById(R.id.buttonCancel);

                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view)
                        {
                            String matchpassword=preferencesRegistration.getString("keyPassword","");

                            if(etPassword.getText().toString().trim().equals(matchpassword))
                            {
                                dialog.hide();
                                editor.remove("keySpeed1");
                                editor.remove("keySpeed2");
                                editor.commit();

                                linearLayout3.setVisibility(View.GONE);
                                linearLayout1.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                Toast.makeText(SpeedActivity.this, "Password Doesn't Matched", Toast.LENGTH_SHORT).show();
                                dialog.hide();
                            }
                        }
                    });

                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.hide();
                        }
                    });

                    dialog.show();
                }
            });

            builder.setNegativeButton("NO",null);

            builder.setCancelable(false);
            AlertDialog dialog=builder.create();
            dialog.show();

        }

    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1)
    {
        int id=numberPicker.getId();

        if(id==R.id.numberPickerSpeed1)
        {
            tvDisplaySpeed1.setText("Speed 1 : "+i1*10+" km/h");
            speed1=i1*10;
        }

        if(id==R.id.numberPickerSpeed2)
        {
            tvDisplaySpeed2.setText("Speed 2 : "+i1*10+" km/h");
            speed2=i1*10;
        }

    }

}
