package com.cstructor.android310;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        ButterKnife.bind(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @OnClick(R.id.uxSeattle)
    public void onSeattle(View view){
        LatLng seattle = new LatLng(47.608887, -122.335502);
    }

    @OnClick(R.id.uxSydney)
    public void onSydney(View view){
        LatLng sydney = new LatLng(-33.873035, 151.254741);
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
        LatLng sydney = new LatLng(-33.873035, 151.254741);
        LatLng seattle = new LatLng(47.608887, -122.335502);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(seattle).title("Marker in Seattle"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Other supported types include: MAP_TYPE_NORMAL,
        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        //mMap.addMarker(new MarkerOptions()
        //        .icon(BitmapDescriptorFactory.fromResource(R.drawable.common_plus_signin_btn_icon_light))
        //        .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
        //        .position(sydney));

        // mMap.animateCamera(CameraUpdateFactory.zoomIn());
    }
}

