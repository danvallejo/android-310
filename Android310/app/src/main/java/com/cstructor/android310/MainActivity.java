package com.cstructor.android310;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.Toast;


import android.view.Menu;
import android.os.Build;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.app.SearchManager;
import android.content.Context;
import android.app.SearchableInfo;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    private Intent mShareIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mShareIntent = new Intent();
        mShareIntent.setAction(Intent.ACTION_SEND);
        mShareIntent.setType("text/plain");
        mShareIntent.putExtra(Intent.EXTRA_TEXT, "From me to you, this text is new.");

    }

    @OnClick(R.id.uxSnackbar)
    public void onSnackbar(View view) {
        Snackbar
                .make(view, "I want a carrot", Snackbar.LENGTH_LONG)
                .setAction("I want cake", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Here's your cake", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    @OnClick(R.id.uxFire)
    public void onFirebase(View view){
        Intent intent = new Intent(this, FirebaseActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxMaps)
    public void onMaps(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxGeofencing)
    public void onGeofencing(View view){
        Intent intent = new Intent(this, GeofenceActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxLocation)
    public void onLocation(View view){
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxRecyclerView)
    public void onRecyclerView(View view){
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxSticky)
    public void onSticky(View view){
        Intent intent = new Intent(this, StickyNotesActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.uxNfcWriter)
    public void onWriter(View view){
        Intent intent = new Intent(this, NfcWriterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxBluetooth)
    public void onBluetooth(View view){
        Intent intent = new Intent(this, com.cstructor.android310.bluetooth.BluetoothActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxConnectivity)
    public void onConnectivity() {
        Intent intent = new Intent(this, ConnectivityActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxRetrofit)
    public void onretro() {
        Intent intent = new Intent(this, OpenWeatherActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxOkHttp)
    public void onOkHttp() {
        Intent intent = new Intent(this, OkHttpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxVolley)
    public void onVOlley() {
        Intent intent = new Intent(this, VolleyActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxRotation)
    public void onRotation() {
        Intent intent = new Intent(this, RotationVectorDemo.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxCompass)
    public void onCompass() {
        Intent intent = new Intent(this, CompassActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxSensor)
    public void onSensor() {
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxSearch)
    public void onSearch() {
        onSearchRequested();
    }

    private ShareActionProvider mShareActionProvider;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(mShareIntent);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Get the SearchView and set the searchable configuration
            MenuItem menuItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) menuItem.getActionView();

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());

            // Assumes current activity is the searchable activity
            searchView.setSearchableInfo(info);
            searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        }

        return true;
    }
}
