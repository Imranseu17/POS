package com.example.root.pos.activities;


import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.root.pos.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    GoogleMap nMap;
    MapFragment mapMessageLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    double latitude, longitude;
    double endlatitude, endlongitude;
    String placeName;
    private static final int REQUEST_FINE_LOCATION = 0;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        setTitle(" Location");
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_clear_all_black_24dp);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);


        loadPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION,REQUEST_FINE_LOCATION);

        if (mapMessageLocation == null) {
            mapMessageLocation = ((MapFragment) getFragmentManager().findFragmentById(R.id.map));
        }

        mapMessageLocation.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                nMap = map;
                if (ActivityCompat.checkSelfPermission(LocationActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                        (LocationActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                nMap.setMyLocationEnabled(true);
            }
        });

        final PlaceAutocompleteFragment places= (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        places.setHint("Search Destination point");
        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                nMap.clear();
                placeName = place.getName().toString();
                LatLng latLng = place.getLatLng();

                endlatitude = latLng.latitude;
                endlongitude = latLng.longitude;

                nMap.addMarker(new MarkerOptions().position(latLng).title(placeName)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                nMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                nMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));




                Toast.makeText(LocationActivity.this,place.getName(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(LocationActivity.this,status.toString(),Toast.LENGTH_LONG).show();

            }
        });

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {

                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(new LatLng(latitude,longitude));
                            markerOptions.title("It is my location");
                            nMap.addMarker(markerOptions);
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                            nMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                            nMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude)));

                        }
                    }
                });

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    private void loadPermissions(String perm,int requestCode) {
        if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
                ActivityCompat.requestPermissions(this, new String[]{perm},requestCode);
            }
        }
    }


}
