package com.cstructor.android310;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerViewActivity extends AppCompatActivity {

    @Bind(R.id.uxRecyclerView)
    public RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        String BASE_URL = "https://api.gettyimages.com/v3/search/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GettyService gettyService = retrofit.create(GettyService.class);

        Call<ImagesModel> call = gettyService.getImagesByPhrase("kittens");

        call.enqueue(new Callback<ImagesModel>() {
            @Override
            public void onResponse(Call<ImagesModel> call, Response<ImagesModel> response) {
                int statusCode = response.code();
                ImagesModel imagesModel = response.body();

                mAdapter = new RecyclerAdapter(RecyclerViewActivity.this, imagesModel.images);

                // divider code
                mRecyclerView.addItemDecoration(new DividerItemDecoration(
                        RecyclerViewActivity.this, LinearLayoutManager.VERTICAL));

                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ImagesModel> call, Throwable t) {

            }
        });
    }
}

