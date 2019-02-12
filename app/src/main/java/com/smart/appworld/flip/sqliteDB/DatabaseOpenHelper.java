package com.smart.appworld.flip.sqliteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.smart.appworld.flip.model.Coin;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static DatabaseOpenHelper sInstance;

    private static final String DATABASE_NAME = "flip";
    private static final int DATABASE_VERSION = 1;


    public static final String TABLE_NAME = "watcher_coins";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SYMBOL = "symbol";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIMESTAMP = "timestamp";


    // Create table SQL query
    public static final String CREATE_WATCHER_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " STRING PRIMARY KEY ,"
                    + COLUMN_SYMBOL + " TEXT,"
                    + COLUMN_NAME + " name,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";


    public static synchronized DatabaseOpenHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseOpenHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */
    private DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(CREATE_WATCHER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);

    }

    public void insertCoin(Coin coin) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(COLUMN_ID, coin.getId());
        values.put(COLUMN_SYMBOL, coin.getId());
        values.put(COLUMN_NAME, coin.getName());

        // insert row
        db.insert(TABLE_NAME, null, values);

        // close db connection
        db.close();


    }

    public Coin getCoin(String id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_SYMBOL, COLUMN_NAME},
                COLUMN_ID + "=?",
                new String[]{id}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Coin coin = new Coin(
                cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_SYMBOL)),
                cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));

        // close the db connection
        cursor.close();

        return coin;
    }

    public List<Coin> getAllCoins() {
        List<Coin> coins = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " +
                COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Coin coin = new Coin( cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SYMBOL)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                coins.add(coin);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return coins list
        return coins;
    }

    public String getAllCoinsIds() {
        List<Coin> coins = getAllCoins();
        StringBuilder coinsIds = new StringBuilder();
        if(coins.size()>0)
        {
            for (Coin coin:coins) {
                coinsIds.append(coin.getId());
                coinsIds.append(",");

            }

            // return coins list
            return coinsIds.substring(0,coinsIds.length()-1);
        }
        else
        {
            return "";
        }


    }

    public int getCoinsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateCoin(Coin coin) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, coin.getId());
        values.put(COLUMN_SYMBOL, coin.getId());
        values.put(COLUMN_NAME, coin.getName());

        // updating row
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(coin.getId())});
    }

    public boolean hasObject(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " =?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {id});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;

            //region if you had multiple records to check for, use this region.

            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
            //here, count is records found
            Log.d(TAG, String.format("%d records found", count));

            //endregion

        }

        cursor.close();
        db.close();
        return hasObject;
    }

    public void deleteCoin(Coin coin) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(coin.getId())});
        db.close();
    }
}