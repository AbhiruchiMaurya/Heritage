package com.abhiruchimaurya.heritage;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MyMaps extends AppCompatActivity implements LocationListener,OnMapReadyCallback
{


    private SupportMapFragment supportMapFragment;
    private GoogleMap googleMap;
    private LocationManager locationManager;
    Heritage h;

    String value;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_maps);


        Intent i=getIntent();
        value = i.getStringExtra("mapinfo");




        supportMapFragment = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mapContainer, supportMapFragment);
        fragmentTransaction.commit();
        supportMapFragment.getMapAsync(this);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.ic_action_normal:
                changeMapType(GoogleMap.MAP_TYPE_NORMAL);
                // googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.ic_action_hybrid:
                changeMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.ic_action_satellite:
                changeMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.ic_action_terrain:
                changeMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;


        }

        return true;
    }


    private void changeMapType(int mapType)
    {
        if (googleMap != null) {
            googleMap.setMapType(mapType);
        }
    }


    @Override
    public void onLocationChanged(Location location) {

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

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-18.142, 178.431), 2));

        if(value.equals("Basilica of Bom Jesus"))

        {

                googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(15.5009, 73.9116 ))
                .title("Basilica of Bom Jesus"));

        }

        else if(value.equals("Church of St. Fransis"))
                {
                                    googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(15.5002, 73.9191))
                                    .title("Church of St. Fransis"));
                }

        else if(value.equals("RED FORT"))
        {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(28.6562, 77.2410))
                    .title("RED FORT"));
        }

        else if(value.equals("Hawa Mahal"))
        {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(26.9239, 75.8267))
                    .title("Hawa Mahal"));
        }

        else if(value.equals("Qutub Minar"))
        {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(28.5244, 77.1855))
                    .title("Qutub Minar"));
        }

        else if(value.equals("Lal Mahal"))
        {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(18.5187, 73.8566))
                    .title("Lal Mahal"));
        }

        else if(value.equals("Shaniwar Wada"))
        {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(18.5196, 73.8554))
                    .title("Shaniwar Wada"));
        }

        else if(value.equals("Charminar"))
        {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(17.3616, 78.4747))
                    .title("Charminar"));
        }

        else if(value.equals("Golcanda Fort"))
        {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(17.3833, 78.4011))
                    .title("Golcanda Fort"));
        }

        else if(value.equals("St Peter’s Square"))
        {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(41.9022, 12.4568))
                    .title("St Peter’s Square"));
        }

        else if(value.equals("The Sistine Chapel"))
        {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(41.9029, 12.4545))
                    .title("The Sistine Chapel"));
        }


       else
                {
                    Toast.makeText(this, "***", Toast.LENGTH_SHORT).show();
                }


    }
}




