package com.example.dell.childtracker;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener, LocationListener
{

    TextView tvChildHelper,tvChildLocation;
    EditText etMessage;
    CheckBox cbParents;
    Button btnHelp;
    Typeface tf1;

    LocationManager lm;

    ProgressDialog pd;

    Geocoder gcd;
    StringBuffer buffer;
    String childName;

    Double childLatitude,childLongitude;

    SharedPreferences preferencesRegistration;

    SharedPreferences preferencesAddress1;
    SharedPreferences preferencesAddress2;
    SharedPreferences preferencesAddress3;

    SmsManager sm;



    String message, messageSend;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        tvChildHelper=(TextView) findViewById(R.id.textViewChildHelper);
        tvChildLocation=(TextView) findViewById(R.id.textViewChildLocation);
        etMessage=(EditText) findViewById(R.id.editTextMessage);
        cbParents=(CheckBox) findViewById(R.id.checkBoxParents);
        btnHelp=(Button) findViewById(R.id.buttonHelp);

        //tf1=Typeface.createFromAsset(getAssets(),"Billabong.ttf");
        tvChildHelper.setTypeface(tf1);

        pd=new ProgressDialog(this);
        pd.setMessage("Fetching Address....");

        gcd=new Geocoder(this);

        preferencesAddress1=this.getSharedPreferences("Registration Detail",this.MODE_PRIVATE);
        preferencesAddress1=this.getSharedPreferences("Address1",this.MODE_PRIVATE);
        preferencesAddress1=this.getSharedPreferences("Address2",this.MODE_PRIVATE);
        preferencesAddress1=this.getSharedPreferences("Address3",this.MODE_PRIVATE);

        childName=preferencesRegistration.getString("keyChildName","");

        sm=SmsManager.getDefault();

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        else
        {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);
            pd.show();
        }

        btnHelp.setOnClickListener(this);
    }


    @Override
    public void onLocationChanged(Location location)
    {

        childLatitude = location.getLatitude();
        childLongitude = location.getLongitude();

        try {
            List<Address> adrsList = gcd.getFromLocation(childLatitude, childLongitude, 1);
            Address address;

            if (adrsList != null && adrsList.size() > 0)
            {

                address = adrsList.get(0);

                buffer = new StringBuffer();

                for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                {
                    buffer.append(address.getAddressLine(i) + "\n");
                }

                tvChildLocation.setText(tvChildLocation.getText() + " " + buffer.toString());

            } else
            {
                Toast.makeText(this, "No Nearby Location Found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }


        pd.dismiss();
        lm.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    @Override
    public void onClick(View view)
    {

        message=tvChildHelper.getText().toString().trim();


        if(cbParents.isChecked())
        {
            messageSend="Your Child, " + childName + " Is In Trouble. He Is Currently At Location - " + buffer.toString();

            if(!message.isEmpty())
            {
                String isMessage=messageSend + "Message Is: " + message;
            }

           // sm.sendTextMessage(mobile,null,isMessage,null,null);

            // Toast.makeText(HelpActivity.this, "Your Details Has Been Sent To Your Parents. You Will Be Helped Soon", Toast.LEGTH_SHORT).show();


        }

        else
        {
            messageSend=childName + " Is In Trouble. He Is Currently At Location - " + buffer.toString();

            if(!message.isEmpty())
            {
                String isMessage=messageSend + "Message Is: " + message;
            }

            // sm.sendTextMessage(mobile,null,isMessage,null,null);

            // Toast.makeText(HelpActivity.this, "Your Details Has Been Sent To Your Nearby Relatives. You Will Be Helped Soon", Toast.LEGTH_SHORT).show();
        }


    }
}
