package edu.cs4730.contentproviderroomdemo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import edu.cs4730.contentproviderroomdemo.databinding.ActivityMainBinding;

/**
 * Simple example of how to create a content provider with a room Database.
 * <p>
 * Note this example adds sample data in the initial load of the database, because of this, loaders or a thread is need
 * for the insert.  Normally we won't have a database create and insert together for a content provider, but needs of the example
 * <p>
 * The data is displayed in a listview with a cursor adapter.  We are using the loaders observer, instead of the roomDatabase observers.
 * <p>
 * Note, Loaders have been deprecated in API 28.  Supported to replace them with modelview and livedata.  set the observer on the cursor
 * coming back via the content provider should work.
 */

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    String TAG = "MainActivity";
    ActivityMainBinding binding;

    private SimpleCursorAdapter dataAdapter;
    Uri CONTENT_URI = Uri.parse("content://edu.cs4730.scoreroomprovider/score");
    //setup the information we want for the content provider.
    String[] projection = new String[]{Score.COLUMN_ID, Score.COLUMN_NAME, Score.COLUMN_SCORE};
    //just for fun, sort return data by name, which instead of default which is _ID I think.
    String SortOrder = Score.COLUMN_SCORE;  //"column name, column name"  except only have one column name.

    private static final int TUTORIAL_LIST_LOADER = 0x01;  //Loader ID number.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //initialize the loader
        LoaderManager.getInstance(this).initLoader(TUTORIAL_LIST_LOADER, null, this);

        // The desired columns to be bound
        String[] columns = new String[]{Score.COLUMN_NAME, Score.COLUMN_SCORE};

        // the XML defined views which the data will be bound to
        int[] to = new int[]{R.id.name, R.id.score};

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(this, R.layout.highscore,
                null,   //cursor is filled in by the loaders.
                columns, to, 0);

        // Assign adapter to ListView
        binding.listView1.setAdapter(dataAdapter);
        binding.listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                @SuppressLint("Range") //literally, fixing in the statement.  lint is dumb.
                String name = cursor.getString(cursor.getColumnIndex(Score.COLUMN_NAME));
                Toast.makeText(getBaseContext(), name, Toast.LENGTH_SHORT).show();

            }
        });

        binding.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //setup the information we want for the contentprovider.
        CursorLoader cursorLoader =
                new CursorLoader(this, CONTENT_URI, projection, null, null, SortOrder);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        Log.d(TAG, "onLoadFinished");
        // "later",  once the data has been loaded, now we set the cursor in the adapter
        dataAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset");
        // called when the data is no longer valid, so remove the cursor
        dataAdapter.swapCursor(null);
    }

    //added purely to test that the loaders in this example are working correctly.
    public void addData() {
        //must be in an async task, so the it doesn't lock up the main thread (or done on another thread)
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Insert Data
                ContentValues initialValues = new ContentValues();
                initialValues.put(Score.COLUMN_NAME, "Fred Flintstone");
                initialValues.put(Score.COLUMN_SCORE, "11223344");
                Uri uri = getContentResolver().insert(CONTENT_URI, initialValues);
            }
        });

    }

}
