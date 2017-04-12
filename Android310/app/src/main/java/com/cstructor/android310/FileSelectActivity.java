package com.cstructor.android310;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.File;

public class FileSelectActivity extends AppCompatActivity {

    // The path to the root of this app's internal storage
    private File mPrivateRootDir;

    // The path to the "images" subdirectory
    private File mImagesDir;

    // Array of files in the images subdirectory
    private File[] mImageFiles;

    // Array of filenames corresponding to mImageFiles
    private String[] mImageFilenames;

    private Intent mResultIntent;

    private ListView mFileListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_select);

        // Set up an Intent to send back to apps that request a file
        mResultIntent = new Intent("com.example.myapp.ACTION_RETURN_FILE");

        // Get the files/ subdirectory of internal storage
        mPrivateRootDir = getFilesDir();

        // Get the files/images subdirectory;
        mImagesDir = new File(mPrivateRootDir, "images");

        // Get the files in the images subdirectory
        mImageFiles = mImagesDir.listFiles();

        // Set the Activity's result to null to begin with
        setResult(Activity.RESULT_CANCELED, null);
        /*
         * Display the file names in the ListView mFileListView.
         * Back the ListView with the array mImageFilenames, which
         * you can create by iterating through mImageFiles and
         * calling File.getAbsolutePath() for each File
         */
        mImageFilenames = new String[mImageFiles.length];
        int index = 0;
        for (File file : mImageFiles) {
            mImageFilenames[index++] = file.getAbsolutePath();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                mImageFilenames);

        mFileListView = (ListView) findViewById(R.id.uxListView);
        mFileListView.setAdapter(adapter);

        // Define a listener that responds to clicks on a file in the ListView
        mFileListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    /*
                     * When a filename in the ListView is clicked, get its
                     * content URI and send it to the requesting app
                     */
                    @Override
                    public void onItemClick(AdapterView adapterView,
                                            View view,
                                            int position,
                                            long rowId) {
                        /*
                         * Get a File for the selected file name.
                         * Assume that the file names are in the
                         * mImageFilename array.
                         */
                        File requestFile = new File(mImageFilenames[position]);

                        /*
                         * Most file-related method calls need to be in
                         * try-catch blocks.
                         */
                        // Use the FileProvider to get a content URI
                        try {
                            Uri fileUri = FileProvider.getUriForFile(
                                    FileSelectActivity.this,
                                    "com.cstructor.android310.fileprovider",
                                    requestFile);

                            if (fileUri != null) {
                                // Grant temporary read permission to the content URI
                                mResultIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                // Put the Uri and MIME type in the result Intent
                                mResultIntent.setDataAndType(
                                        fileUri,
                                        getContentResolver().getType(fileUri));
                                // Set the result
                                FileSelectActivity.this.setResult(Activity.RESULT_OK, mResultIntent);

                                // We are done, close the activity and return
                                finish();
                            } else {
                                mResultIntent.setDataAndType(null, "");
                                FileSelectActivity.this.setResult(RESULT_CANCELED,
                                        mResultIntent);
                            }
                        } catch (IllegalArgumentException e) {
                            Log.e("File Selector", "The selected file can't be shared: " + mImageFilenames[position]);
                        }
                    }
                }
        );
    }

}

