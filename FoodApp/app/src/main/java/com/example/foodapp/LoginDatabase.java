package com.example.foodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LoginDatabase extends SQLiteOpenHelper {

    // set up initial database columns for stored user information
    public static final int DB_VERSION = 2;
    public static String DB_NAME = "UserDatabase.db";
    public static String DB_TABLE = "UserDatabase";
    public static String COLUMN_ID = "UserID";
    public static String COLUMN_EMAIL = "UserEmail";
    public static String COLUMN_PASSWORD = "UserPassword";
    public static String COLUMN_FIRSTNAME = "UserFirstName";
    public static String COLUMN_LASTNAME = "UserLastName";


    public LoginDatabase(@Nullable Context context) {super(context, DB_NAME, null, DB_VERSION); }

    // creates database table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DB_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_FIRSTNAME + " TEXT," +
                COLUMN_LASTNAME + " TEXT" + ")";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }

    // checks if a given user's credentials exist in the database upon login
    public boolean userExists(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DB_TABLE +
                " WHERE " + COLUMN_EMAIL + " = ?" +
                " AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    // adds a registered user's credentials to the database
    public long addUser(ModelUser modelUser){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, modelUser.getFirstName());
        contentValues.put(COLUMN_LASTNAME, modelUser.getLastName());
        contentValues.put(COLUMN_EMAIL, modelUser.getEmail());
        contentValues.put(COLUMN_PASSWORD, modelUser.getPassword());
        long ID = db.insert(DB_TABLE, null, contentValues);
        Log.d("Inserted", "id ->" + ID);
        return ID;
    }

    // finds gets the user's id based on their email and password login
    public int getUserId(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        int userId = -1;
        String query = "SELECT " + COLUMN_ID + " FROM " + DB_TABLE +
                " WHERE " + COLUMN_EMAIL + " = ?" +
                " AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COLUMN_ID);
            if (columnIndex >= 0) {
                userId = cursor.getInt(columnIndex);
            }
        }
        cursor.close();
        return userId;
    }


    // to get info of a specific user based on its ID
    public ModelUser getUser(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] query = new String[]{COLUMN_ID, COLUMN_FIRSTNAME, COLUMN_LASTNAME, COLUMN_EMAIL, COLUMN_PASSWORD};
        Cursor cursor = db.query(DB_TABLE, query, COLUMN_ID + "=?", new String[]{String.valueOf(ID)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            return new ModelUser(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
        }
        else {
            return null;
        }
    }
}
