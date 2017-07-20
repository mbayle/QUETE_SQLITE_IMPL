package sport.fr.wcs.quete_sqlite_impl;

/**
 * Created by wilder on 20/07/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static sport.fr.wcs.quete_sqlite_impl.DatabaseContract.SQL_CREATE_BELONG_ENTRY;
import static sport.fr.wcs.quete_sqlite_impl.DatabaseContract.SQL_CREATE_ORGANIZATION;
import static sport.fr.wcs.quete_sqlite_impl.DatabaseContract.SQL_CREATE_TWEET;
import static sport.fr.wcs.quete_sqlite_impl.DatabaseContract.SQL_CREATE_USER;
import static sport.fr.wcs.quete_sqlite_impl.DatabaseContract.SQL_DELETE_BELONG_ENTRY;
import static sport.fr.wcs.quete_sqlite_impl.DatabaseContract.SQL_DELETE_TWEET;


public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_TWEET);
        db.execSQL(SQL_CREATE_ORGANIZATION);
        db.execSQL(SQL_CREATE_BELONG_ENTRY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TWEET);
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_ORGANIZATION);
        db.execSQL(SQL_DELETE_BELONG_ENTRY);
        onCreate(db);

    }
}