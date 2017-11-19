package edu.cs4730.contentproviderroomdemo;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/*
 *
 * example requires loaders to work, because it will throw an example about length time on the first query.
 */

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
    String TAG = "MainActivity";

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
        setContentView(R.layout.activity_main);

        //initialize the loader
        getSupportLoaderManager().initLoader(TUTORIAL_LIST_LOADER, null, this);

        // The desired columns to be bound
        String[] columns = new String[]{
                Score.COLUMN_NAME,
                Score.COLUMN_SCORE
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[]{
                R.id.name,
                R.id.score
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.highscore,
                null,   //cursor is filled in by the loaders.
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);


                String name = cursor.getString(cursor.getColumnIndex(Score.COLUMN_NAME));
                Toast.makeText(getBaseContext(), name, Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        //setup the information we want for the contentprovider.

        CursorLoader cursorLoader = new CursorLoader(this,
                CONTENT_URI, projection, null, null, SortOrder);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d(TAG, "onLoadFinished");
        // "later",  once the data has been loaded, now we set the cursor in the adapter
        dataAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset");
        // called when the data is no longer valid, so remove the cursor
        dataAdapter.swapCursor(null);
    }

}
