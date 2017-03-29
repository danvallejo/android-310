package com.cstructor.android310;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.uxSnackbar)
    public void onSnackbar(View view){
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
}
