package com.cstructor.android310;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NfcWriterActivity extends AppCompatActivity {
    private NfcAdapter adapter;
    private PendingIntent pendingIntent;
    private IntentFilter writeTagFilters[];
    private boolean writeMode;
    private Tag mytag;

    @Bind(R.id.uxEditText)
    public TextView mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_writer);
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

        adapter = NfcAdapter.getDefaultAdapter(this);

        // Don't launch this activity if it's already running
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        // Look for NFC tags
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);

        writeTagFilters = new IntentFilter[]{tagDetected};
    }

    @OnClick(R.id.uxButton)
    public void onClick(View view) {
        try {
            if (mytag == null) {
                Toast.makeText(this, getString(R.string.error_detected), Toast.LENGTH_LONG).show();
            } else {
                write(mMessage.getText().toString(), mytag);

                Toast.makeText(this, getString(R.string.ok_writing), Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, getString(R.string.error_writing), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (FormatException e) {
            Toast.makeText(this, getString(R.string.error_writing), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void write(String text, Tag tag) throws IOException, FormatException {
        NdefRecord[] records = {createRecord(text)};

        NdefMessage message = new NdefMessage(records);

        // Get an instance of Ndef for the tag.
        Ndef ndef = Ndef.get(tag);

        // Enable I/O
        ndef.connect();

        // Write the message
        ndef.writeNdefMessage(message);

        // Close the connection
        ndef.close();
    }

    private NdefRecord createRecord(String text) throws UnsupportedEncodingException {
        String lang = "en";
        byte[] textBytes = text.getBytes();
        byte[] langBytes = lang.getBytes("US-ASCII");
        int langLength = langBytes.length;
        int textLength = textBytes.length;
        byte[] payload = new byte[1 + langLength + textLength];

        // set status byte (see NDEF spec for actual bits)
        payload[0] = (byte) langLength;

        // copy langbytes and textbytes into payload
        System.arraycopy(langBytes, 0, payload, 1, langLength);
        System.arraycopy(textBytes, 0, payload, 1 + langLength, textLength);

        NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload);

        return recordNFC;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            mytag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            Toast.makeText(this, this.getString(R.string.tag_dectected) + mytag.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        WriteModeOff();
    }

    @Override
    public void onResume() {
        super.onResume();
        WriteModeOn();
    }

    private void WriteModeOn() {
        writeMode = true;
        adapter.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null);
    }

    private void WriteModeOff() {
        writeMode = false;
        adapter.disableForegroundDispatch(this);
    }
}

