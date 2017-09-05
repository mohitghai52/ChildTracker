package com.example.dell.childtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

    EditText etEmail,etPassoword;
    TextView tvForgotPassword;
    Button btnLogIn;

    CheckBox ckbxRememberMe;
    ImageView imgbtnSpeedometer;

    String email,password,mobile,message;
    SmsManager sm;
    Boolean rememberMe;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("Registration Detail",MODE_PRIVATE);
        editor=preferences.edit();


        etEmail = (EditText) findViewById(R.id.editTextEmail);

        rememberMe=preferences.getBoolean("keyRememberMe",false);
        ckbxRememberMe=(CheckBox) findViewById(R.id.cbRememberMe);

        if(rememberMe==true)
        {
            etEmail.setText(preferences.getString("keyEmail",""));
            ckbxRememberMe.setChecked(true);
        }

        initViews();
    }

    void initViews()
    {


        etPassoword = (EditText) findViewById(R.id.editTextPassword);
        tvForgotPassword=(TextView) findViewById(R.id.textViewForgotPassword);
        btnLogIn = (Button) findViewById(R.id.buttonLogin);

        imgbtnSpeedometer=(ImageView) findViewById(R.id.imageViewSpeedometer);


        btnLogIn.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        imgbtnSpeedometer.setOnClickListener(this);

        preferences=getSharedPreferences("Registration Detail",MODE_PRIVATE);



        sm=SmsManager.getDefault();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.buttonLogin)
        {
            if (etEmail.getText().toString().trim().equals("") || !etEmail.getText().toString().contains("@") || !etEmail.getText().toString().contains("."))
            {
                if (etEmail.getText().toString().trim().equals(""))
                {
                    etEmail.setError("Please Enter Email Address");
                }
                else
                {
                    etEmail.setError("Please Enter Valid Email");
                }

            }

            else if (etPassoword.getText().toString().trim().equals(""))
            {
                etPassoword.setError("Please Enter Password");
            }

            else
            {
                email = preferences.getString("keyEmail", "");
                password = preferences.getString("keyPassword", "");


                if(ckbxRememberMe.isChecked())
                {
                    editor.putBoolean("keyRememberMe",true);
                    editor.commit();
                }
                else
                {
                    editor.remove("keyRememberMe");
                    editor.commit();
                }


                if (etEmail.getText().toString().trim().equals(email) && etPassoword.getText().toString().trim().equals(password))
                {
                     Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                     startActivity(intent);
                     finish();
                }
                else
                {
                    Toast.makeText(this, "Email or Password Doesn't Matched", Toast.LENGTH_SHORT).show();
                }
            }

        }

        if (id == R.id.textViewForgotPassword)
        {
            email=preferences.getString("keyEmail", "");

            if(etEmail.getText().toString().trim().equals(email))
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Message Confirmation");
                builder.setIcon(R.drawable.confirmationicon);
                builder.setMessage("Your Password Will Be Sent At Your Registered Mobile Number");

                 builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
                 {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i)
                     {
                         password = preferences.getString("keyPassword", "");
                         mobile=preferences.getString("keyMobile","");
                         System.out.print(mobile);

                         message="Your Password Is: "+password;

                         sm.sendTextMessage(mobile,null,message,null,null);

                         Toast.makeText(LoginActivity.this, "Your Password Has Been Send To Your Registered Mobile Number", Toast.LENGTH_SHORT).show();
                     }


                 });

                builder.setNegativeButton("NO",null);
                AlertDialog dialog=builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

            }

        else
        {
            Toast.makeText(LoginActivity.this, "Please Enter Registered Email", Toast.LENGTH_SHORT).show();
        }

        }

        if(id==R.id.imageViewSpeedometer)
        {
            Toast.makeText(this, "Soon You Will Be Helped", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(LoginActivity.this,HelpActivity.class);
            startActivity(intent);
        }

    }

}
