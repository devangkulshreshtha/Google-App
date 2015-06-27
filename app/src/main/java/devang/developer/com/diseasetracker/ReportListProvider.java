package devang.developer.com.diseasetracker;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import java.util.HashMap;

/**
 * Created by devang kulshreshtha on 5/5/2015.
 */
public class ReportListProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.developer.devang.ReportListProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/cte";
    static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String KEY_ROWID = "_id";
    public static final String KEY_DISEASE1 = "disease1";
    public static final String KEY_DISEASE2 = "disease2";
    public static final String KEY_DISEASE3 = "disease3";
    public static final String KEY_DISEASE4 = "disease4";
    public static final String KEY_DISEASE5 = "disease5";
    public static final String KEY_DISEASE6 = "disease6";
    public static final String KEY_DISEASE7 = "disease7";
    public static final String KEY_DISEASE8 = "disease8";
    public static final String KEY_DISEASE9 = "disease9";
    public static final String KEY_DISEASE10 = "disease10";
    public static final String KEY_VALUE1 = "value1";
    public static final String KEY_VALUE2 = "value2";
    public static final String KEY_VALUE3 = "value3";
    public static final String KEY_VALUE4 = "value4";
    public static final String KEY_VALUE5 = "value5";
    public static final String KEY_VALUE6 = "value6";
    public static final String KEY_VALUE7 = "value7";
    public static final String KEY_VALUE8 = "value8";
    public static final String KEY_VALUE9 = "value9";
    public static final String KEY_VALUE10 = "value10";
    public static final String KEY_MONTH = "month";
    public static final String KEY_YEAR = "year";
    public static final String KEY_DATE = "date";
    public static final String KEY_RATING = "rating";
    public static final String KEY_NOTE = "note";
    static final int uriCode = 1;
    static final UriMatcher uriMatcher;
    private static HashMap<String, String> values;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "cte", uriCode);
        uriMatcher.addURI(PROVIDER_NAME, "cte/*", uriCode);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        if (db != null) {
            return true;
        }
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case uriCode:
                qb.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == "") {
            sortOrder = null;
        }
        Cursor c = qb.query(db, projection, selection, selectionArgs, null,
                null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case uriCode:
                return "vnd.android.cursor.dir/cte";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = db.insert(TABLE_NAME, "", values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case uriCode:
                count = db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case uriCode:
                count = db.update(TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "mydb";
    static final String TABLE_NAME = "reports";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_DISEASE1 + " TEXT NOT NULL DEFAULT '', " +
            KEY_DISEASE2 + " TEXT NOT NULL DEFAULT '', " +
            KEY_DISEASE3 + " TEXT NOT NULL DEFAULT '', " +
            KEY_DISEASE4 + " TEXT NOT NULL DEFAULT '', " +
            KEY_DISEASE5 + " TEXT NOT NULL DEFAULT '', " +
            KEY_DISEASE6 + " TEXT NOT NULL DEFAULT '', " +
            KEY_DISEASE7 + " TEXT NOT NULL DEFAULT '', " +
            KEY_DISEASE8 + " TEXT NOT NULL DEFAULT '', " +
            KEY_DISEASE9 + " TEXT NOT NULL DEFAULT '', " +
            KEY_DISEASE10 + " TEXT NOT NULL DEFAULT '', " +
            KEY_VALUE1 + " INTEGER DEFAULT 0, " +
            KEY_VALUE2 + " INTEGER DEFAULT 0, " +
            KEY_VALUE3 + " INTEGER DEFAULT 0, " +
            KEY_VALUE4 + " INTEGER DEFAULT 0, " +
            KEY_VALUE5 + " INTEGER DEFAULT 0, " +
            KEY_VALUE6 + " INTEGER DEFAULT 0, " +
            KEY_VALUE7 + " INTEGER DEFAULT 0, " +
            KEY_VALUE8 + " INTEGER DEFAULT 0, " +
            KEY_VALUE9 + " INTEGER DEFAULT 0, " +
            KEY_VALUE10 + " INTEGER DEFAULT 0, " +
            KEY_DATE + " TEXT NOT NULL DEFAULT '', " +
            KEY_YEAR + " INTEGER DEFAULT 0, " +
            KEY_MONTH + " INTEGER DEFAULT 0, " +
            KEY_NOTE + " TEXT NOT NULL DEFAULT '', " +
            KEY_RATING + " INTEGER DEFAULT 0);";
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}