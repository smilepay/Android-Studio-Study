package com.example.paysm.r1614263_3;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final LatLng SEOUL = new LatLng(37.566535, 126.977969);
    ArrayList<LatLng> arrayPoints;
    double latitude;
    double longitude;
    LatLng position,position2;
    private GoogleMap mMap;
    Button button;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        arrayPoints = new ArrayList<LatLng>();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        flag=0;

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (flag==0) {
            arrayPoints.add(SEOUL);
            position = SEOUL;
        }


        LocationListener mLocationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                if (flag==0) {
                    arrayPoints.add(SEOUL);
                    position = SEOUL;
                }

                latitude = location.getLatitude();
                longitude = location.getLongitude();
                position2 = new LatLng(latitude, longitude);
                arrayPoints.add(position2);
                flag=1;
                onMapReady(mMap);
             }

             public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            public void onProviderEnabled(String provider) {

            }

            public void onProviderDisabled(String provider) {
            }
        };

        try {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, mLocationListener);
            }
        }
        catch(SecurityException e){
            e.printStackTrace();
        }


        button = findViewById(R.id.button);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
       mMap = googleMap;
       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL,15));
        if (flag == 1) {
            PolylineOptions line = new PolylineOptions()
                    .color(Color.RED)
                    .width(5)
                    .add(position)
                    .add(position2);
            line.add(position2);
            mMap.addPolyline(line);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position2, 15));
            position = position2;
            line.addAll(arrayPoints);
            Polyline pl = mMap.addPolyline(line);
        }

    }
}
