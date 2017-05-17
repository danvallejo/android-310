package com.cstructor.android310;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AmazonActivity extends AppCompatActivity {
    private AmazonDynamoDBClient dbClient;

    @Bind(R.id.uxEditText)
    public EditText mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amazon);
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

        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),    /* get the context for the application */
                "us-east-1:141457d3-a54b-4b5c-98b7-bc6e252e43ad",    /* Identity Pool ID */
                Regions.US_EAST_1           /* Region for your identity pool--US_EAST_1 or EU_WEST_1*/
        );
        dbClient = new AmazonDynamoDBClient(credentialsProvider);
    }

    private class GetTableResult extends AsyncTask<Void, Void, Void> {
        DescribeTableResult mResult;
        String text;

        public GetTableResult(String text){
            this.text = text;
        }

        protected Void doInBackground(Void... voids) {
            try {
                DynamoDBMapper mapper = new DynamoDBMapper(dbClient);

                ChatMapper chatMapper = new ChatMapper();

                chatMapper.setChatId(UUID.randomUUID().toString());
                chatMapper.setUserName("dave");
                chatMapper.setText(text);

                mapper.save(chatMapper);

                mResult = dbClient.describeTable("Chat");
                TableDescription description =  mResult.getTable();
            }catch (Exception ex){
                // just eat it
                int x = 4;
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            mTextView.setText( mResult.toString());
        }
    }

    @OnClick(R.id.uxSend)
    public void onSend(View view){
        new GetTableResult(mTextView.getText().toString()).execute();
    }
}
