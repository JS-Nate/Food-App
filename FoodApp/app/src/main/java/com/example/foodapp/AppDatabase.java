package com.example.foodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.foodapp.models.ModelMenuItem;
import com.example.foodapp.models.ModelUser;
import com.example.foodapp.models.ModelVendor;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase extends SQLiteOpenHelper {

    // USER TABLE
    private static final int DB_VERSION = 6;
    private static String DB_NAME = "FoodApp.db";
    private static String USER_DB_TABLE = "users";
    private static String USER_COLUMN_ID = "user_id";
    private static String USER_COLUMN_EMAIL = "email";
    private static String USER_COLUMN_PASSWORD = "password";
    private static String USER_COLUMN_FIRST_NAME = "first_name";
    private static String USER_COLUMN_LAST_NAME = "last_name";

    // ORDER TABLE
    private static String ORDER_DB_TABLE = "orders";
    private static String ORDER_COLUMN_ID = "order_id";
    private static String ORDER_COLUMN_USER_ID = "user_id";
    private static String ORDER_COLUMN_VENDOR_ID = "vendor_id";
    private static String ORDER_COLUMN_ORDER_DATE = "order_date";
    private static String ORDER_COLUMN_ORDER_STATUS = "order_status";
    private static String ORDER_COLUMN_TOTAL_AMOUNT = "total_amount";

    // VENDOR TABLE
    private static String VENDOR_DB_TABLE = "vendors";
    private static String VENDOR_COLUMN_ID = "vendor_id";
    private static String VENDOR_COLUMN_VENDOR_NAME = "vendor_name";
    private static String VENDOR_COLUMN_VENDOR_DESCRIPTION = "vendor_description";
    private static String VENDOR_COLUMN_VENDOR_LONGITUDE = "vendor_longitude";
    private static String VENDOR_COLUMN_VENDOR_LATITUDE  = "vendor_latitude";
    private static String VENDOR_COLUMN_VENDOR_CONTACT = "vendor_contact";

    // MENU ITEMS TABLE
    private static String MENU_ITEM_DB_TABLE = "menu_items";
    private static String MENU_ITEM_COLUMN_ID = "item_id";
    private static String MENU_ITEM_COLUMN_VENDOR_ID = "vendor_id";
    private static String MENU_ITEM_COLUMN_ITEM_NAME = "item_name";
    private static String MENU_ITEM_COLUMN_FEATURED = "item_featured";
    private static String MENU_ITEM_COLUMN_DESCRIPTION = "item_description";
    private static String MENU_ITEM_COLUMN_CATEGORY = "item_category";
    private static String MENU_ITEM_COLUMN_IMAGE = "item_image";
    private static String MENU_ITEM_COLUMN_PRICE = "price";

    // ORDER ITEM TABLE
    private static String ORDER_ITEM_DB_TABLE = "order_items";
    private static String ORDER_ITEM_COLUMN_ID = "order_item_id";
    private static String ORDER_ITEM_COLUMN_ORDER_ID = "order_id";
    private static String ORDER_ITEM_COLUMN_ITEM_ID = "item_id";
    private static String ORDER_ITEM_COLUMN_QUANTITY = "quantity";
    private static String ORDER_ITEM_COLUMN_ITEM_PRICE = "item_price";
    private static String ORDER_ITEM_COLUMN_SUBTOTAL = "subtotal";


    public AppDatabase(@Nullable Context context) {super(context, DB_NAME, null, DB_VERSION); }

    // creates database tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Users table
        String query = "CREATE TABLE " + USER_DB_TABLE +
                "(" + USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COLUMN_EMAIL + " TEXT," +
                USER_COLUMN_PASSWORD + " TEXT," +
                USER_COLUMN_FIRST_NAME + " TEXT," +
                USER_COLUMN_LAST_NAME + " TEXT" + ")";
        db.execSQL(query);

        // Vendor Table
        query = "CREATE TABLE " + VENDOR_DB_TABLE +
                "(" + VENDOR_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VENDOR_COLUMN_VENDOR_NAME + " TEXT," +
                VENDOR_COLUMN_VENDOR_DESCRIPTION + " TEXT," +
                VENDOR_COLUMN_VENDOR_LONGITUDE + " REAL," +
                VENDOR_COLUMN_VENDOR_LATITUDE + " REAL," +
                VENDOR_COLUMN_VENDOR_CONTACT + " TEXT" + ")";
        db.execSQL(query);

        // Order Table
        query = "CREATE TABLE " + ORDER_DB_TABLE +
                "(" + ORDER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ORDER_COLUMN_USER_ID + " INTEGER," +
                ORDER_COLUMN_VENDOR_ID + " INTEGER," +
                ORDER_COLUMN_ORDER_DATE + " DATE," +
                ORDER_COLUMN_ORDER_STATUS + " TEXT," +
                ORDER_COLUMN_TOTAL_AMOUNT + " REAL," +
                "FOREIGN KEY (" + ORDER_COLUMN_USER_ID + ") REFERENCES Users(" + USER_COLUMN_ID + ")," +
                "FOREIGN KEY (" + ORDER_COLUMN_VENDOR_ID + ") REFERENCES Vendors(" + VENDOR_COLUMN_ID + "))";
        db.execSQL(query);

        // Menu Item
        query = "CREATE TABLE " + MENU_ITEM_DB_TABLE +
                "(" + MENU_ITEM_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MENU_ITEM_COLUMN_VENDOR_ID + " INTEGER," +
                MENU_ITEM_COLUMN_ITEM_NAME + " TEXT," +
                MENU_ITEM_COLUMN_FEATURED + " BOOLEAN," +
                MENU_ITEM_COLUMN_DESCRIPTION + " TEXT," +
                MENU_ITEM_COLUMN_CATEGORY + " TEXT," +
                MENU_ITEM_COLUMN_IMAGE + " TEXT," +
                MENU_ITEM_COLUMN_PRICE + " REAL," +
                "FOREIGN KEY (" + MENU_ITEM_COLUMN_VENDOR_ID + ") REFERENCES Vendors("+ VENDOR_COLUMN_ID + "))";
        db.execSQL(query);

        // Order Item
        query = "CREATE TABLE " + ORDER_ITEM_DB_TABLE +
                "(" + ORDER_ITEM_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ORDER_ITEM_COLUMN_ORDER_ID + " INTEGER," +
                ORDER_ITEM_COLUMN_ITEM_ID + " INTEGER," +
                ORDER_ITEM_COLUMN_QUANTITY + " INTEGER," +
                ORDER_ITEM_COLUMN_ITEM_PRICE + " REAL," +
                ORDER_ITEM_COLUMN_SUBTOTAL + " REAL," +
                "FOREIGN KEY (" + ORDER_ITEM_COLUMN_ORDER_ID + ") REFERENCES Orders("+ ORDER_COLUMN_ID + ")," +
                "FOREIGN KEY (" + ORDER_ITEM_COLUMN_ITEM_ID + ") REFERENCES MenuItems("+ MENU_ITEM_COLUMN_ID + "))";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + USER_DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + VENDOR_DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MENU_ITEM_DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_ITEM_DB_TABLE);
        onCreate(db);
    }

    // For testing purposes
    public void seedDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // add some user
        contentValues.put(USER_COLUMN_FIRST_NAME, "Student");
        contentValues.put(USER_COLUMN_LAST_NAME, "Smith");
        contentValues.put(USER_COLUMN_EMAIL, "student.smith@email.com");
        contentValues.put(USER_COLUMN_PASSWORD, "Password123");
        db.insert(USER_DB_TABLE, null, contentValues);

        // add some vendor
        contentValues = new ContentValues();
        contentValues.put(VENDOR_COLUMN_VENDOR_NAME, "Tim Hortons");
        contentValues.put(VENDOR_COLUMN_VENDOR_DESCRIPTION, "Tim Hortons serves coffee, donuts, sandwiches, and other fast-food items");
        contentValues.put(VENDOR_COLUMN_VENDOR_LONGITUDE, 43.9862672);
        contentValues.put(VENDOR_COLUMN_VENDOR_LATITUDE, -78.8445777);
        contentValues.put(VENDOR_COLUMN_VENDOR_CONTACT, "Phone: (905) 436-8080");
        db.insert(VENDOR_DB_TABLE, null, contentValues);

        // add some vendor
        contentValues = new ContentValues();
        contentValues.put(VENDOR_COLUMN_VENDOR_NAME, "Pizza Restaurant");
        contentValues.put(VENDOR_COLUMN_VENDOR_DESCRIPTION, "This is a pizza place");
        contentValues.put(VENDOR_COLUMN_VENDOR_LONGITUDE, 23.9862672);
        contentValues.put(VENDOR_COLUMN_VENDOR_LATITUDE, 98.8445777);
        contentValues.put(VENDOR_COLUMN_VENDOR_CONTACT, "Phone: (123) 456-7890");
        db.insert(VENDOR_DB_TABLE, null, contentValues);

        // add some order
        contentValues = new ContentValues();
        contentValues.put(ORDER_COLUMN_USER_ID, 1);
        contentValues.put(ORDER_COLUMN_VENDOR_ID, 1);
        contentValues.put(ORDER_COLUMN_ORDER_DATE, "2023-11-07");
        contentValues.put(ORDER_COLUMN_ORDER_STATUS, "Pending");
        contentValues.put(ORDER_COLUMN_TOTAL_AMOUNT, 7.49);
        db.insert(ORDER_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 1);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Avocado Toast");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "For all you hippies");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Food");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 1);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://cdn.sanity.io/images/czqk28jt/prod_th_ca/a1449a14843559badacede42c780a4b320d9f863-1024x1024.png?w=320&q=40&fit=max&auto=format");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 7.49);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 1);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Donuts");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "6 assorted donuts");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Donuts");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 1);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://cdn.sanity.io/images/czqk28jt/prod_th_ca/a1449a14843559badacede42c780a4b320d9f863-1024x1024.png?w=320&q=40&fit=max&auto=format");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 7.49);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 2);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Coke");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "this is cocaine");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "coldDrink");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 1);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://138794804.cdn6.editmysite.com/uploads/1/3/8/7/138794804/s132994155277906853_p18_i3_w1200.jpeg");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 2.59);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some order item
        contentValues = new ContentValues();
        contentValues.put(ORDER_ITEM_COLUMN_ORDER_ID, 1);
        contentValues.put(ORDER_ITEM_COLUMN_ITEM_ID, 1);
        contentValues.put(ORDER_ITEM_COLUMN_QUANTITY, 1);
        contentValues.put(ORDER_ITEM_COLUMN_ITEM_PRICE, 7.49);
        contentValues.put(ORDER_ITEM_COLUMN_SUBTOTAL, 7.49);
        db.insert(ORDER_ITEM_DB_TABLE, null, contentValues);
    }


    /****************** MENU TABLE ******************/
    public List<ModelMenuItem> getFeaturedItems(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelMenuItem> menuItems = new ArrayList<>();
        String queryStatement = "SELECT * FROM " + MENU_ITEM_DB_TABLE;
        Cursor cursor = db.rawQuery(queryStatement, null);

        while (cursor.moveToNext()) {
            ModelMenuItem modelMenuItem = new ModelMenuItem();
            modelMenuItem.setId(cursor.getInt(0));
            modelMenuItem.setVendorID(cursor.getInt(1));
            modelMenuItem.setItemName(cursor.getString(2));
            modelMenuItem.setItemFeatured(cursor.getString(3));
            modelMenuItem.setItemDescription(cursor.getString(4));
            modelMenuItem.setItemCategory(cursor.getString(5));
            modelMenuItem.setItemImage(cursor.getString(6));
            modelMenuItem.setItemPrice(cursor.getString(7));

            menuItems.add(modelMenuItem);
        }
        cursor.close();
        return menuItems;

    }
    // for reference
//    public List<ModelVendor> getVendors() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        List<ModelVendor> allVendor = new ArrayList<>();
//        String queryStatement = "SELECT * FROM " + VENDOR_DB_TABLE;
//        Cursor cursor = db.rawQuery(queryStatement, null);
//
//        while (cursor.moveToNext()) {
//            ModelVendor modelVendor = new ModelVendor();
//            modelVendor.setId(cursor.getInt(0));
//            modelVendor.setName(cursor.getString(1));
//            modelVendor.setDescription(cursor.getString(2));
//            modelVendor.setLongitude(cursor.getDouble(3));
//            modelVendor.setLatitude(cursor.getDouble(4));
//            modelVendor.setContact(cursor.getString(5));
//
//            allVendor.add(modelVendor);  // Add the created object to the list
//        }
//
//        cursor.close();  // Close the cursor to avoid memory leaks
//        return allVendor;
//    }



    public List<ModelMenuItem> getMenuItemsByCategory(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelMenuItem> menuItems = new ArrayList<>();

        String[] query = new String[]{
                MENU_ITEM_COLUMN_ID,
                MENU_ITEM_COLUMN_VENDOR_ID,
                MENU_ITEM_COLUMN_ITEM_NAME,
                MENU_ITEM_COLUMN_FEATURED,
                MENU_ITEM_COLUMN_DESCRIPTION,
                MENU_ITEM_COLUMN_CATEGORY,
                MENU_ITEM_COLUMN_IMAGE,
                MENU_ITEM_COLUMN_PRICE
        };

        Cursor cursor = db.query(
                MENU_ITEM_DB_TABLE,
                query,
                MENU_ITEM_COLUMN_CATEGORY + "=?",
                new String[]{category},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                ModelMenuItem menuItem = new ModelMenuItem();
                menuItem.setId(cursor.getInt(0));
                menuItem.setVendorID(cursor.getInt(1));
                menuItem.setItemImage(cursor.getString(2));
                menuItem.setItemFeatured(cursor.getString(3));



                menuItems.add(menuItem);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return menuItems;
    }


    public ModelMenuItem getMenuItem(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] query = new String[]{
                MENU_ITEM_COLUMN_ID,
                MENU_ITEM_COLUMN_VENDOR_ID,
                MENU_ITEM_COLUMN_ITEM_NAME,
                MENU_ITEM_COLUMN_FEATURED,
                MENU_ITEM_COLUMN_DESCRIPTION,
                MENU_ITEM_COLUMN_CATEGORY,
                MENU_ITEM_COLUMN_IMAGE,
                MENU_ITEM_COLUMN_PRICE};
        Cursor cursor = db.query(MENU_ITEM_DB_TABLE, query, MENU_ITEM_COLUMN_ID + "=?", new String[]{String.valueOf(ID)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            return new ModelMenuItem(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
        }
        else {
            return null;
        }

    }




    /****************** USER TABLE ******************/

    // checks if a given user's credentials exist in the database upon login
    public boolean userExists(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USER_DB_TABLE +
                " WHERE " + USER_COLUMN_EMAIL + " = ?" +
                " AND " + USER_COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    // adds a registered user's credentials to the database
    public long addUser(ModelUser modelUser){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_FIRST_NAME, modelUser.getFirstName());
        contentValues.put(USER_COLUMN_LAST_NAME, modelUser.getLastName());
        contentValues.put(USER_COLUMN_EMAIL, modelUser.getEmail());
        contentValues.put(USER_COLUMN_PASSWORD, modelUser.getPassword());
        long ID = db.insert(USER_DB_TABLE, null, contentValues);
        Log.d("Inserted", "id ->" + ID);
        return ID;
    }

    // finds gets the user's id based on their email and password login
    public int getUserId(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        int userId = -1;
        String query = "SELECT " + USER_COLUMN_ID + " FROM " + USER_DB_TABLE +
                " WHERE " + USER_COLUMN_EMAIL + " = ?" +
                " AND " + USER_COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(USER_COLUMN_ID);
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
        String[] query = new String[]{USER_COLUMN_ID, USER_COLUMN_FIRST_NAME, USER_COLUMN_LAST_NAME, USER_COLUMN_EMAIL, USER_COLUMN_PASSWORD};
        Cursor cursor = db.query(USER_DB_TABLE, query, USER_COLUMN_ID + "=?", new String[]{String.valueOf(ID)}, null, null, null, null);

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

    // Retrieve total orders
    public int getTotalOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + ORDER_DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int totalOrders = cursor.getInt(0);
        cursor.close();
        return totalOrders;
    }


    /****************** VENDOR TABLE ******************/
    // returns a list of vendors to be listed on the home page
    public List<ModelVendor> getVendors() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelVendor> allVendor = new ArrayList<>();
        String queryStatement = "SELECT * FROM " + VENDOR_DB_TABLE;
        Cursor cursor = db.rawQuery(queryStatement, null);

        while (cursor.moveToNext()) {
            ModelVendor modelVendor = new ModelVendor();
            modelVendor.setId(cursor.getInt(0));
            modelVendor.setName(cursor.getString(1));
            modelVendor.setDescription(cursor.getString(2));
            modelVendor.setLongitude(cursor.getDouble(3));
            modelVendor.setLatitude(cursor.getDouble(4));
            modelVendor.setContact(cursor.getString(5));

            allVendor.add(modelVendor);  // Add the created object to the list
        }

        cursor.close();  // Close the cursor to avoid memory leaks
        return allVendor;
    }


    public ModelVendor getVendorFromId(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] query = new String[]{VENDOR_COLUMN_ID, VENDOR_COLUMN_VENDOR_NAME, VENDOR_COLUMN_VENDOR_DESCRIPTION, VENDOR_COLUMN_VENDOR_LONGITUDE, VENDOR_COLUMN_VENDOR_LATITUDE, VENDOR_COLUMN_VENDOR_DESCRIPTION};
        Cursor cursor = db.query(VENDOR_DB_TABLE, query, VENDOR_COLUMN_ID + "=?", new String[]{String.valueOf(ID)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()){
            return new ModelVendor(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4),
                    cursor.getString(5)
            );
        }
        else{
            return null;
        }
    }

    public String getVendorName(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        String vendorName = null;

        String queryStatement = "SELECT " + VENDOR_COLUMN_VENDOR_NAME +
                " FROM " + VENDOR_DB_TABLE +
                " WHERE " + VENDOR_COLUMN_ID + " = ?";

        Cursor cursor = db.rawQuery(queryStatement, new String[]{String.valueOf(ID)});

        if (cursor != null && cursor.moveToFirst()) {
            vendorName = cursor.getString(0);
            cursor.close();
        }

        db.close();

        return vendorName;
    }






    /*
    * public List<MenuItem> getMenuItembyTag(string tag)
    *
    *
    * */



























}
