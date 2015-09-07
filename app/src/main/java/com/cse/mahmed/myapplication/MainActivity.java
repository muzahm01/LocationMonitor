package com.cse.mahmed.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements LocationListener {

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 2; // 2 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 Sec
    TextView tvLat;
    TextView tvLong;
    TextView tvSpeed;
    TextView tvBearing;
    TextView tvAccuracy;
    TextView tvDistA;
    TextView tvDistB;
    TextView tvDistC;
    TextView tvDistD;
    LocationManager locationManager;
    ActivityRecognitionIntentService activityRecognitionIntentService;
    Location locationA = new Location("point A");
    Location locationB = new Location("point B");
    Location locationC = new Location("point C");
    Location locationD = new Location("point D");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityRecognitionIntentService = new ActivityRecognitionIntentService("muz");
        locationA.setLatitude(65.039272);
        locationA.setLongitude(25.465155);

        locationB.setLatitude(65.039434);
        locationB.setLongitude(25.465254);

        locationC.setLatitude(65.006635);
        locationC.setLongitude(25.48093);

        locationD.setLatitude(65.0584013);
        locationD.setLongitude(25.4652056);

        tvLat = (TextView) findViewById(R.id.tvLat);
        tvLong = (TextView) findViewById(R.id.tvLong);
        tvBearing = (TextView) findViewById(R.id.tvBearing);
        tvAccuracy = (TextView) findViewById(R.id.tvAccuracy);
        tvSpeed = (TextView) findViewById(R.id.tvSpeed);
        tvDistA = (TextView) findViewById(R.id.tvDistA);
        tvDistB = (TextView) findViewById(R.id.tvDistB);
        tvDistC = (TextView) findViewById(R.id.tvDistC);
        tvDistD = (TextView) findViewById(R.id.tvDistD);


        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);


            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {

        updateLoc(location);


    }

    private void updateLoc(Location location) {
        try {
            int distanceA = (int) locationA.distanceTo(location);
            int distanceB = (int) locationB.distanceTo(location);
            int distanceC = (int) locationC.distanceTo(location);
            int distanceD = (int) locationD.distanceTo(location);

            tvDistA.setText(distanceA + "");
            tvDistB.setText(distanceB + "");
            tvDistC.setText(distanceC + "");
            tvDistD.setText(distanceD + "");
            tvDistA.setBackgroundColor(Color.GREEN);
            tvDistA.setBackgroundColor(Color.GREEN);
            tvDistB.setBackgroundColor(Color.GREEN);
            tvDistC.setBackgroundColor(Color.GREEN);
            tvDistD.setBackgroundColor(Color.GREEN);
            if (distanceA <= 3000) {
                tvDistA.setBackgroundColor(Color.YELLOW);
                if (distanceA <= 1000) {
                    tvDistA.setBackgroundColor(Color.RED);
                }

            }
            if (distanceB <= 3000) {
                tvDistB.setBackgroundColor(Color.YELLOW);
                if (distanceB <= 1000) {
                    tvDistB.setBackgroundColor(Color.RED);
                }

            }
            if (distanceC <= 3000) {
                tvDistA.setBackgroundColor(Color.YELLOW);
                if (distanceC <= 1000) {
                    tvDistA.setBackgroundColor(Color.RED);
                }

            }
            if (distanceD <= 3000) {
                tvDistD.setBackgroundColor(Color.YELLOW);
                if (distanceD <= 1000) {
                    tvDistD.setBackgroundColor(Color.RED);
                }
            }

            tvLat.setText(location.getLatitude() + "");
            tvLong.setText(location.getLongitude() + "");
            tvSpeed.setText(location.getSpeed() + "");
            tvBearing.setText(location.getBearing() + "");
            tvAccuracy.setText(location.getAccuracy() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
