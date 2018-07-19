package edu.cs4730.contentproviderroomdemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.support.annotation.NonNull;


public class myDBContentProvider extends ContentProvider {

	/**
	 *
	 * We could use the Score RoomDatabase Plus need  to add stuff for the loader classes as well.
	 *
     * This implementation is not complete, since some of the them throw errors, where we should
     * allow it to happen.  delete all and update all
     *
	 * Note there is only one table, so the provider only has score and score_id
	 * If there were more tables publicly accessible, then we would need more names/numbers for
	 * urimatcher.
     *
     * NOTE this example is ignoring the where cause/selection that is passed to the content provider
     * This is likely a very bad idea, but is allowed for the purpose of the example.
	 */

    public static final String PROVIDER_NAME = "edu.cs4730.scoreroomprovider";

    public static final Uri CONTENT_URI =
            Uri.parse("content://" + PROVIDER_NAME + "/score");

    private static final int SCORE = 1;
    private static final int SCORE_ID = 2;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "score", SCORE);
        uriMatcher.addURI(PROVIDER_NAME, "score/#", SCORE_ID);
    }

    static final String TAG = "myDBCP";


    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            // get all rows
            case SCORE:
                return "vnd.android.cursor.dir/vnd.cs4730.score";
            // get a particular row
            case SCORE_ID:
                return "vnd.android.cursor.item/vnd.cs4730.score";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    /*
     * So delete is a simple function
     *   Remember, content providers should be used a a wrapper class, so it calls into the ScoreDatabase
     *   to do the work.  But it does need setup the selection arg correctly.
     *
     */
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        switch (uriMatcher.match(uri)) {
            case SCORE:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case SCORE_ID:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        final Context context = getContext();
        if (context == null) {
            return 0;
        }
        int count = AppDatabase.getInstance(context).ScoreDao().deleteById(ContentUris.parseId(uri));
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    /*
     * 	For the query method, but need to provide a couple more piece of information to the query
     * but are null values, plus pass onthe sortOrder on to it.
     */
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        final Context context = getContext();
        if (context == null) {
            return null;
        }
        ScoreDao score = AppDatabase.getInstance(context).ScoreDao();
        Cursor c;
        switch (uriMatcher.match(uri)) {
            case SCORE:
                c = score.selectAll();
                break;
            case SCORE_ID:
                c = score.selectById(ContentUris.parseId(uri));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        //this line is added for the loaders.  if we  changed the database, this allows a notification to be set.
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        if (uriMatcher.match(uri) != SCORE) {  //can't insert by id number, so only generic is allowed.
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (values == null) {   //basic error checking.  values is null, provider an empty one
            values = new ContentValues();  //or we could through an SQLExecption as well.
        }
        final Context context = getContext();
        if (context == null) {
            return null;
        }

        long rowId = AppDatabase.getInstance(context).ScoreDao()
                .insert(Score.fromContentValues(values));
        if (rowId > 0) {   //add the row id to the uri and return it to the user.
            Uri noteUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            //this line is added for the loaders.  We changed the database, so notify everyone.
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }

        throw new SQLException("Failed to insert row into " + uri);
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int count;
        switch (uriMatcher.match(uri)) {
            case SCORE:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case SCORE_ID:

                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        final Context context = getContext();
        if (context == null) {
            return 0;
        }
        final Score score = Score.fromContentValues(values);
        score.id = ContentUris.parseId(uri);
        count =  AppDatabase.getInstance(context).ScoreDao().update(score);
        //this line is added for the loaders.  We changed the database, so notify everyone.
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }



}
