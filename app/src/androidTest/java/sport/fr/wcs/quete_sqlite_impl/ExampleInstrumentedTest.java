package sport.fr.wcs.quete_sqlite_impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("sport.fr.wcs.quete_sqlite_impl", appContext.getPackageName());
    }

    @Test
    public void readTest() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DbHelper mDbHelper = new DbHelper(appContext);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        ContentValues user = new ContentValues();
        user.put(DatabaseContract.UserEntry.COLUMN_NAME_NAME, "User name");
        user.put(DatabaseContract.UserEntry.COLUMN_NAME_EMAIL, "toto@gmail.com");
        long newUserId = db.insert(DatabaseContract.UserEntry.TABLE_NAME, null, user);


        for(int i = 0; i < 10; i++){
            ContentValues tweet = new ContentValues();
            tweet.put(DatabaseContract.TweetEntry.COLUMN_NAME_TWEET, "content" + i);
            tweet.put(DatabaseContract.TweetEntry.COLUMN_NAME_ID_USER, newUserId);
            long newTweetId = db.insert(DatabaseContract.TweetEntry.TABLE_NAME, null, tweet);
            assertNotEquals(-1, newTweetId);
        }
        String query = "SELECT "+ DatabaseContract.TweetEntry.COLUMN_NAME_TWEET+" FROM "+DatabaseContract.TweetEntry.TABLE_NAME + " WHERE "+DatabaseContract.TweetEntry.COLUMN_NAME_ID_USER + " = " + newUserId;

        Cursor cursor = db.rawQuery(query, null);

        int i = 0;
        while(cursor.moveToNext()){
            String toto = cursor.getString(0);
            assertEquals(true, toto.contains("content"));
            i++;
        }
        cursor.close();


    }
}
