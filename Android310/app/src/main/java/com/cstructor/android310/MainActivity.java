package com.cstructor.android310;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.Menu;
import android.os.Build;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.app.SearchManager;
import android.content.Context;
import android.app.SearchableInfo;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
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

    @OnClick(R.id.uxSensor)
    public void onSensor() {
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.uxSearch)
    public void onSearch() {
        onSearchRequested();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
