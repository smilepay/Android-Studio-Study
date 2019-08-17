package com.example.paysm.p1614263_10;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int status = 1;
    Button bt;
    public double first_lat, first_long, last_lat, last_long, final_distance;
    EditText distance;
    LinearLayout distanceShow;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button)findViewById(R.id.button);
        distance = (EditText)findViewById(R.id.editText);
        distanceShow = (LinearLayout)findViewById(R.id.distance_layout);
        distanceShow.setVisibility(distanceShow.INVISIBLE);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        LocationListener locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                last_lat = location.getLatitude();
                last_long = location.getLongitude();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            public void onProviderEnabled(String provider) {

            }
            public void onProviderDisabled(String provider) {
            }
        };

        try {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
        }
        catch(SecurityException e){
            e.printStackTrace();
        }
    }

    public void Test_Start_or_End(View view){
        if(status == 1){
            distanceShow.setVisibility(distanceShow.INVISIBLE);
            status = 2;
            bt.setText("측정 종료");
            try {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    first_lat = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
                    first_long = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
                }
            }
            catch(SecurityException e){
                e.printStackTrace();
            }
        }
        else if(status == 2){
            distanceShow.setVisibility(distanceShow.VISIBLE);
            status = 1;
            bt.setText("측정 시작");
            final_distance = Math.sqrt(Math.pow((last_lat - first_lat)*109.95,2) + Math.pow((last_long - first_long)*88.74,2));
            distance.setText(String.valueOf(final_distance));
        }
    }
}