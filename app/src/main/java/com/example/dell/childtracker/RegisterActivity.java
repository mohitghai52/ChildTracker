package com.example.dell.childtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView tvRegister;
    EditText etName,etEmail,etPassword,etReTypePassword,etMobile,etChildName;
    Button btnRegister;
    Typeface tf1;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    String name,email,password,mobile,childName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setTitle("Registration Page");

        //tf1=Typeface.createFromAsset(getAssets(),"BRUSHSCI.TTF");
        //tvRegister.setTypeface(tf1);

        initViews();

    }

    void initViews()
    {

        tvRegister=(TextView) findViewById(R.id.textViewRegister);
        etName=(EditText) findViewById(R.id.editTextName);
        etEmail=(EditText) findViewById(R.id.editTextEmail);
        etPassword=(EditText) findViewById(R.id.editTextPassword);
        etReTypePassword=(EditText) findViewById(R.id.editTextReTypePassword);
        etMobile=(EditText) findViewById(R.id.editTextMobile);
        etChildName=(EditText) findViewById(R.id.editTextChildName);

        btnRegister=(Button) findViewById(R.id.buttonRegister);;

        preferences=getSharedPreferences("Registration Detail",MODE_PRIVATE);
        editor=preferences.edit();

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id=view.getId();

        if(id==R.id.buttonRegister)
        {

            if (etName.getText().toString().equals(""))
            {
                etName.setError("Please Enter Name");
            }
            else if (etEmail.getText().toString().equals("") || !etEmail.getText().toString().contains("@") || !etEmail.getText().toString().contains("."))
            {
                if(etEmail.getText().toString().equals(""))
                {
                    etEmail.setError("Please Enter Email");
                }

                else
                {
                    etEmail.setError("Please Enter Valid Email");
                }

            }
            else if (etPassword.getText().toString().equals("") || etPassword.getText().toString().trim().length()<5)
            {
                if(etPassword.getText().toString().equals(""))
                {
                    etPassword.setError("Please Enter Password");
                }
                else
                {
                    etPassword.setError("Password Must Have 5 or More Characters");
                }
            }
            else if (etReTypePassword.getText().toString().equals("")||!etReTypePassword.getText().toString().equals(etPassword.getText().toString()))
            {
                if(etReTypePassword.getText().toString().equals(""))
                {
                    etReTypePassword.setError("Please Enter Retype Password");
                }

                else
                {
                    etReTypePassword.setError("Retype Password Doesn't Match");
                }

            }
             else if (etMobile.getText().toString().equals("") || etMobile.getText().toString().trim().length()<6 || etMobile.getText().toString().trim().length()>13)
            {
                if(etMobile.getText().toString().equals(""))
                {
                    etMobile.setError("Please Enter Mobile No.");
                }
                else
                {
                    etMobile.setError("Please Enter Valid Mobile No.");
                }
            }
            else if (etChildName.getText().toString().equals(""))
            {
                etChildName.setError("Please Enter ChildName");
            }

            else
            {
                name = etName.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                mobile = etMobile.getText().toString().trim();
                childName = etChildName.getText().toString().trim();

                editor.putString("keyName", name);
                editor.putString("keyEmail", email);
                editor.putString("keyPassword", password);
                editor.putString("keyMobile", mobile);
                editor.putString("keyChildName", childName);

                editor.commit();

                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Registration Done");
                builder.setMessage("You Are Successfully Registered \nPlease Login To Continue");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setCancelable(false);
                builder.setIcon(R.drawable.ok);
                AlertDialog dialog=builder.create();
                dialog.show();

            }

        }

    }

    @Override
    public void onBackPressed()
    {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Registration Cancelled");
        builder.setMessage("Are You Sure You Want To Quit \nEntered Details Will Be Lost");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                finish();
            }
        });
        builder.setNegativeButton("CANCEL",null);
        builder.setIcon(R.drawable.warning);
        builder.setCancelable(false);
        AlertDialog dialog=builder.create();
        dialog.show();

    }
}

