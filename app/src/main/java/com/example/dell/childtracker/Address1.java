package com.example.dell.childtracker;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class Address1 extends Fragment implements View.OnClickListener, LocationListener {
    EditText etAddress1;
    EditText etCity1;
    EditText etState1;
    EditText etMobileNo1;

    Button btnSubmit1;
    Button btnFetchAddress1;
    Button btnEdit1;

    View view;

    LocationManager lm1;

    Context context;

    double latitude1, longitude1;

    ProgressDialog pd1;

    Geocoder gcd;
    StringBuffer buffer;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etAddress1 = (EditText) view.findViewById(R.id.editTextAddress1);
        etCity1 = (EditText) view.findViewById(R.id.editTextCity1);
        etState1 = (EditText) view.findViewById(R.id.editTextState1);
        etMobileNo1 = (EditText) view.findViewById(R.id.editTextMobile1);

        btnSubmit1 = (Button) view.findViewById(R.id.buttonSubmit1);
        btnFetchAddress1 = (Button) view.findViewById(R.id.buttonFetchAddress1);
        btnEdit1 = (Button) view.findViewById(R.id.buttonEdit1);

        gcd=new Geocoder(getContext());


        btnSubmit1.setOnClickListener(this);
        btnFetchAddress1.setOnClickListener(this);
        btnEdit1.setOnClickListener(this);

        pd1=new ProgressDialog(getContext());
        pd1.setMessage("Fetching Address....");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_address1, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchAddress();
    }

    @Override
    public void onClick(View view)
    {
        int id=view.getId();

        if(id==R.id.buttonFetchAddress1)
        {
            fetchAddress();
        }

    }

    public void fetchAddress() {
        context = getContext();
        lm1 = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        else
        {
            lm1.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5, 5, Address1.this);
            pd1.show();
        }
    }

    @Override
    public void onLocationChanged(Location location)
    {
        latitude1=location.getLatitude();
        longitude1=location.getLongitude();

        String city="";
        String State="";

        try {
            List<Address> adrsList = gcd.getFromLocation(latitude1, longitude1, 1);
            Address address;

            if (adrsList != null && adrsList.size() > 0)
            {

                address = adrsList.get(0);

                city=adrsList.get(0).getLocality();
                State=adrsList.get(0).getAdminArea();

                buffer = new StringBuffer();

                for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                {
                    buffer.append(address.getAddressLine(i) + "\n");
                }

                int start=buffer.toString().trim().indexOf(city);
                int end=buffer.toString().trim().indexOf(State)+ State.length();
                buffer.delete(start,end);

                etAddress1.setText(buffer.toString());

            } else
                {
                    Toast.makeText(context, "No Nearby Location Found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }

        etCity1.setText(city);
        etState1.setText(State);
        pd1.dismiss();
        lm1.removeUpdates(this);

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
}
