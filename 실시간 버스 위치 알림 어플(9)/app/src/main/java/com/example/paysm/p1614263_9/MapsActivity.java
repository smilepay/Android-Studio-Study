package com.example.paysm.p1614263_9;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        num = intent.getIntExtra("number",1);
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

        // Add a marker in Sydney and move the camera
        LatLng seoul = new LatLng(37.517719, 127.0094791);
        //   mMap.addMarker(new MarkerOptions().position(seoul).title("Marker in Seoul"));
        busLocation();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));
    }

    private void busLocation() {

        String serviceUrl;
        String serviceKey;
        //  String strSrch;
        String strUrl;

        //  serviceUrl = "http://openapi.tago.go.kr/openapi/service/BusLcInfoInqireService/getRouteAcctoBusLcList";
        //   serviceKey = "VZk4EhfOFiAaRhqvId46im%2BuOcPd%2FzIiLaquayzXt6xEWy2G8n4hojoJnJel4KFwlDV5b5988PmYZZTx9mXWQw%3D%3D";
        //  serviceKey = URLEncoder.encode(serviceKey);
        //  String cityCode = "25";
        //  String routeId = "DJB30300101ND";
        //  strUrl = serviceUrl+"?ServiceKey="+serviceKey+"cityCode="+ cityCode + "&routeId="+routeId;
        if (num ==1){
            strUrl= "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?ServiceKey=VZk4EhfOFiAaRhqvId46im%2BuOcPd%2FzIiLaquayzXt6xEWy2G8n4hojoJnJel4KFwlDV5b5988PmYZZTx9mXWQw%3D%3D&busRouteId=100100522";
            new DownloadWebpageTask().execute(strUrl);
        }
        else if( num==2){
            strUrl= "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?ServiceKey=VZk4EhfOFiAaRhqvId46im%2BuOcPd%2FzIiLaquayzXt6xEWy2G8n4hojoJnJel4KFwlDV5b5988PmYZZTx9mXWQw%3D%3D&busRouteId=100100596";
            new DownloadWebpageTask().execute(strUrl);
        }
        else if(num==3){
            strUrl="http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?ServiceKey=VZk4EhfOFiAaRhqvId46im%2BuOcPd%2FzIiLaquayzXt6xEWy2G8n4hojoJnJel4KFwlDV5b5988PmYZZTx9mXWQw%3D%3D&busRouteId=100100537";
            new DownloadWebpageTask().execute(strUrl);
        }
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String) downloadUrl((String) urls[0]);
            } catch (IOException e) {
                return "다운로드 실패";
            }
        }

        protected void onPostExecute(String result) {
            displayBuspos(result);
        }

        private String downloadUrl(String myurl) throws IOException {

            HttpURLConnection conn = null;
            try {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                String line = null;
                String page = "";
                while ((line = bufreader.readLine()) != null) {
                    page += line;
                }

                return page;
            } finally {
                conn.disconnect();
            }
        }

        private void displayBuspos(String result) {
            String plainNo = "";
            String gpsX = "";
            String gpsY = "";
            boolean bSet_plainNo = false;
            boolean bSet_gpsX = false;
            boolean bSet_gpsY = false;

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if (eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("plainNo"))
                            bSet_plainNo = true;
                        if (tag_name.equals("gpsX"))
                            bSet_gpsX = true;
                        if (tag_name.equals("gpsY"))
                            bSet_gpsY = true;
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bSet_gpsX) {
                            gpsX = xpp.getText();
                            bSet_gpsX = false;
                        }
                        if (bSet_gpsY) {
                            gpsY = xpp.getText();
                            bSet_gpsY = false;
                        }
                        if (bSet_plainNo) {
                            plainNo = xpp.getText();
                            bSet_plainNo = false;

                            displayMap(gpsX, gpsY, plainNo);
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                }

            } catch (Exception e) {
                ;
            }
        }

        private void displayMap(String gpsX, String gpsY, String plainNo) {
            double latitude = Double.parseDouble(gpsY);
            double longitude = Double.parseDouble(gpsX);
            final LatLng LOC = new LatLng(latitude, longitude);

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LOC, 12));
            Marker mk = mMap.addMarker(new MarkerOptions()
                    .position(LOC)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus))
                    .title(plainNo));
            mk.showInfoWindow();
        }
    }
}