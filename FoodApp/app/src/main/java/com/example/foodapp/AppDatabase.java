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
import com.example.foodapp.models.ModelOrder;
import com.example.foodapp.models.ModelOrderItem;
import com.example.foodapp.models.ModelVendorImage;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase extends SQLiteOpenHelper {

    // USER TABLE
    private static final int DB_VERSION = 13;
    private static String DB_NAME = "FoodApp.db";
    private static String USER_DB_TABLE = "users";
    private static String USER_COLUMN_ID = "user_id";
    private static String USER_COLUMN_EMAIL = "email";
    private static String USER_COLUMN_PASSWORD = "password";
    private static String USER_COLUMN_FIRST_NAME = "first_name";
    private static String USER_COLUMN_LAST_NAME = "last_name";
    private static String USER_COLUMN_USER_IMAGE = "user_image";

    // VENDOR TABLE
    private static String VENDOR_DB_TABLE = "vendors";
    private static String VENDOR_COLUMN_ID = "vendor_id";
    private static String VENDOR_COLUMN_VENDOR_NAME = "vendor_name";
    private static String VENDOR_COLUMN_VENDOR_DESCRIPTION = "vendor_description";
    private static String VENDOR_COLUMN_VENDOR_LONGITUDE = "vendor_longitude";
    private static String VENDOR_COLUMN_VENDOR_LATITUDE  = "vendor_latitude";
    private static String VENDOR_COLUMN_VENDOR_CONTACT = "vendor_contact";
    private static String VENDOR_COLUMN_VENDOR_VIDEO = "vendor_video";

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

    // ORDER TABLE
    private static String ORDER_DB_TABLE = "orders";
    private static String ORDER_COLUMN_ID = "order_id";
    private static String ORDER_COLUMN_USER_ID = "user_id";
    private static String ORDER_COLUMN_VENDOR_ID = "vendor_id";
    private static String ORDER_COLUMN_ORDER_DATE = "order_date";
    private static String ORDER_COLUMN_ORDER_STATUS = "order_status";
    private static String ORDER_COLUMN_TOTAL_AMOUNT = "total_amount";

    // ORDER ITEM TABLE
    private static String ORDER_ITEM_DB_TABLE = "order_items";
    private static String ORDER_ITEM_COLUMN_ID = "order_item_id";
    private static String ORDER_ITEM_COLUMN_ORDER_ID = "order_id";
    private static String ORDER_ITEM_COLUMN_ITEM_ID = "item_id";
    private static String ORDER_ITEM_COLUMN_QUANTITY = "quantity";
    private static String ORDER_ITEM_COLUMN_ITEM_PRICE = "item_price";
    private static String ORDER_ITEM_COLUMN_SUBTOTAL = "subtotal";

    // VENDOR IMAGES TABLE
    private static String VENDOR_IMAGES_DB_TABLE = "vendor_images";
    private static String VENDOR_IMAGES_COLUMN_ID = "vendor_image_id";
    private static String VENDOR_IMAGES_COLUMN_VENDOR_ID = "vendor_image_vendor_id"; // the id of the vendor this image belongs to
    private static String VENDOR_IMAGES_COLUMN_IMAGE = "vendor_image_image";


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
                USER_COLUMN_LAST_NAME + " TEXT," +
                USER_COLUMN_USER_IMAGE + " TEXT" + ")";
        db.execSQL(query);

        // Vendor Table
        query = "CREATE TABLE " + VENDOR_DB_TABLE +
                "(" + VENDOR_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VENDOR_COLUMN_VENDOR_NAME + " TEXT," +
                VENDOR_COLUMN_VENDOR_DESCRIPTION + " TEXT," +
                VENDOR_COLUMN_VENDOR_LONGITUDE + " REAL," +
                VENDOR_COLUMN_VENDOR_LATITUDE + " REAL," +
                VENDOR_COLUMN_VENDOR_VIDEO + " TEXT," +
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

        // Vendor images
        query = "CREATE TABLE " + VENDOR_IMAGES_DB_TABLE +
                "(" + VENDOR_IMAGES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VENDOR_IMAGES_COLUMN_VENDOR_ID  + " INTEGER," +
                VENDOR_IMAGES_COLUMN_IMAGE + " TEXT," +
                "FOREIGN KEY (" + VENDOR_IMAGES_COLUMN_VENDOR_ID + ") REFERENCES Vendors(" + VENDOR_COLUMN_ID + "))";
        db.execSQL(query);



    }


    // table upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;

        db.execSQL("DROP TABLE IF EXISTS " + USER_DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + VENDOR_DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MENU_ITEM_DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_ITEM_DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + VENDOR_IMAGES_DB_TABLE);
        onCreate(db);
    }


    // For testing purposes, adds entries into the database for debugging and testing
    public void seedDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // add some user
        contentValues.put(USER_COLUMN_FIRST_NAME, "Student");
        contentValues.put(USER_COLUMN_LAST_NAME, "Smith");
        contentValues.put(USER_COLUMN_EMAIL, "student.smith@email.com");
        contentValues.put(USER_COLUMN_PASSWORD, "Password123");
        contentValues.put(USER_COLUMN_USER_IMAGE, "android.resource://com.example.foodapp/drawable/default_avatar");
        db.insert(USER_DB_TABLE, null, contentValues);

        // add some vendor
        contentValues = new ContentValues();
        contentValues.put(VENDOR_COLUMN_VENDOR_NAME, "Tim Hortons");
        contentValues.put(VENDOR_COLUMN_VENDOR_DESCRIPTION, "Tim Hortons serves coffee, donuts, sandwiches, and other fast-food items");
        contentValues.put(VENDOR_COLUMN_VENDOR_LONGITUDE, -78.8934687);
        contentValues.put(VENDOR_COLUMN_VENDOR_LATITUDE, 43.9449244);
        contentValues.put(VENDOR_COLUMN_VENDOR_CONTACT, "Phone: (905) 436-8080");
        contentValues.put(VENDOR_COLUMN_VENDOR_VIDEO, "tim_hortons_vendor");
        db.insert(VENDOR_DB_TABLE, null, contentValues);

        // add some vendor
        contentValues = new ContentValues();
        contentValues.put(VENDOR_COLUMN_VENDOR_NAME, "Pizza Hut");
        contentValues.put(VENDOR_COLUMN_VENDOR_DESCRIPTION, "Our pizza uses local farm sourced ingredients to provide fresh pizza ");
        contentValues.put(VENDOR_COLUMN_VENDOR_LONGITUDE, 98.8445777);
        contentValues.put(VENDOR_COLUMN_VENDOR_LATITUDE, 23.9862672);
        contentValues.put(VENDOR_COLUMN_VENDOR_CONTACT, "Phone: (123) 456-7890");
        contentValues.put(VENDOR_COLUMN_VENDOR_VIDEO, "pizza_vendor");
        db.insert(VENDOR_DB_TABLE, null, contentValues);

        // add some vendor
        contentValues = new ContentValues();
        contentValues.put(VENDOR_COLUMN_VENDOR_NAME, "Outback Steakhouse");
        contentValues.put(VENDOR_COLUMN_VENDOR_DESCRIPTION, "Australian themed casual dining restaurants, serving American cuisine, based in Tampa, Florida.");
        contentValues.put(VENDOR_COLUMN_VENDOR_LONGITUDE, 38.883333);
        contentValues.put(VENDOR_COLUMN_VENDOR_LATITUDE, -77);
        contentValues.put(VENDOR_COLUMN_VENDOR_CONTACT, "Phone: (123) 456-7890");
        contentValues.put(VENDOR_COLUMN_VENDOR_VIDEO, "outback_steakhouse_vendor");
        db.insert(VENDOR_DB_TABLE, null, contentValues);

        // add some vendor
        contentValues = new ContentValues();
        contentValues.put(VENDOR_COLUMN_VENDOR_NAME, "A&W");
        contentValues.put(VENDOR_COLUMN_VENDOR_DESCRIPTION, "We have burgers");
        contentValues.put(VENDOR_COLUMN_VENDOR_LONGITUDE, 38.2388516);
        contentValues.put(VENDOR_COLUMN_VENDOR_LATITUDE, -76.523293);
        contentValues.put(VENDOR_COLUMN_VENDOR_CONTACT, "Phone: (098) 765-4321");
        contentValues.put(VENDOR_COLUMN_VENDOR_VIDEO, "a_w_vendor");
        db.insert(VENDOR_DB_TABLE, null, contentValues);

        // add some vendor
        contentValues = new ContentValues();
        contentValues.put(VENDOR_COLUMN_VENDOR_NAME, "Wendy's");
        contentValues.put(VENDOR_COLUMN_VENDOR_DESCRIPTION, "We have burgers");
        contentValues.put(VENDOR_COLUMN_VENDOR_LONGITUDE, 38.2388516);
        contentValues.put(VENDOR_COLUMN_VENDOR_LATITUDE, -76.523293);
        contentValues.put(VENDOR_COLUMN_VENDOR_CONTACT, "Phone: (098) 765-4321");
        contentValues.put(VENDOR_COLUMN_VENDOR_VIDEO, "a_w_vendor");
        db.insert(VENDOR_DB_TABLE, null, contentValues);



        // add some vendor image
        contentValues = new ContentValues();
        contentValues.put(VENDOR_IMAGES_COLUMN_VENDOR_ID, 1);
        contentValues.put(VENDOR_IMAGES_COLUMN_IMAGE, "https://www.bpmcdn.com/f/files/interiornews/import/2022-06/29317788_web1_220601-CPW-Tim-Hortons-privacy-watchdogs-privacy_1.jpg");
        db.insert(VENDOR_IMAGES_DB_TABLE, null, contentValues);

        // add some vendor image
        contentValues = new ContentValues();
        contentValues.put(VENDOR_IMAGES_COLUMN_VENDOR_ID, 1);
        contentValues.put(VENDOR_IMAGES_COLUMN_IMAGE, "https://www.indystar.com/gcdn/-mm-/2425a0470766b558967df12696ca47cade450c8d/c=149-99-2888-1647/local/-/media/2018/01/20/INGroup/Indianapolis/636520357268068995--us-images-Tim-Hortons-Exterior-2.jpg?width=2739&height=1548&fit=crop&format=pjpg&auto=webp");
        db.insert(VENDOR_IMAGES_DB_TABLE, null, contentValues);

        // add some vendor image
        contentValues = new ContentValues();
        contentValues.put(VENDOR_IMAGES_COLUMN_VENDOR_ID, 1);
        contentValues.put(VENDOR_IMAGES_COLUMN_IMAGE, "https://img.cdn4dd.com/cdn-cgi/image/fit=contain,width=1200,height=672,format=auto/https://doordash-static.s3.amazonaws.com/media/store/header/5b0a4b4c-6b6f-4578-9a8e-1c62896c990a.png");
        db.insert(VENDOR_IMAGES_DB_TABLE, null, contentValues);

        // add some vendor image
        contentValues = new ContentValues();
        contentValues.put(VENDOR_IMAGES_COLUMN_VENDOR_ID, 2);
        contentValues.put(VENDOR_IMAGES_COLUMN_IMAGE, "https://dynl.mktgcdn.com/p/hxJgH_gPUGuHQPqGidqaJNMl9pbQqLO7esOuNzfyw8o/496x344.png");
        db.insert(VENDOR_IMAGES_DB_TABLE, null, contentValues);

        // add some vendor image
        contentValues = new ContentValues();
        contentValues.put(VENDOR_IMAGES_COLUMN_VENDOR_ID, 3);
        contentValues.put(VENDOR_IMAGES_COLUMN_IMAGE, "https://mocoshow.com/wp-content/uploads/2023/03/D9063D11-06B1-4EA7-8376-A6AE4893B153.jpeg");
        db.insert(VENDOR_IMAGES_DB_TABLE, null, contentValues);

        // add some vendor image
        contentValues = new ContentValues();
        contentValues.put(VENDOR_IMAGES_COLUMN_VENDOR_ID, 4);
        contentValues.put(VENDOR_IMAGES_COLUMN_IMAGE, "https://d1ralsognjng37.cloudfront.net/1228ff38-2e42-4238-9a85-c8b4ebec9cb5.webp");
        db.insert(VENDOR_IMAGES_DB_TABLE, null, contentValues);


                // add some vendor image
        contentValues = new ContentValues();
        contentValues.put(VENDOR_IMAGES_COLUMN_VENDOR_ID, 5);
        contentValues.put(VENDOR_IMAGES_COLUMN_IMAGE, "https://www.usatoday.com/gcdn/media/USATODAY/WiresImages/2012/10/11/820fbb8bab63a41c1d0f6a70670052a9-16_9.jpg?width=1200&disable=upscale&format=pjpg&auto=webp");
        db.insert(VENDOR_IMAGES_DB_TABLE, null, contentValues);

//        // add some vendor image
//        contentValues = new ContentValues();
//        contentValues.put(VENDOR_IMAGES_COLUMN_VENDOR_ID, 2);
//        contentValues.put(VENDOR_IMAGES_COLUMN_IMAGE, "");
//        db.insert(VENDOR_IMAGES_DB_TABLE, null, contentValues);


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
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "For all you youngsters");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Food");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 1);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://simplyfreshfoodie.com/wp-content/uploads/2021/08/DSC_0546.jpg");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 7.49);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 1);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Donuts");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "6 assorted donuts");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Food");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 1);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://cdn.sanity.io/images/czqk28jt/prod_th_ca/a1449a14843559badacede42c780a4b320d9f863-1024x1024.png?w=320&q=40&fit=max&auto=format");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 7.49);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 2);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Pizza");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "a nice yummy pizza");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Food");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://www.washingtonpost.com/graphics/2020/food/best-pizza-dc/img/TimberWholePizzaCutout.png");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 12.99);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 1);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Coffee");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "a cup of coffee");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Drink");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 1);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://www.starbucksathome.com/sites/default/files/styles/nutrition_instruction_image/public/2021-04/BlackCoffee_LongShadow_0_1_0%20%281%29.png?itok=wkv8XBvk");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 1.49);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 2);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Coca-Cola");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "Ice Cold Coca-Cola");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Drink");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 1);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://138794804.cdn6.editmysite.com/uploads/1/3/8/7/138794804/s132994155277906853_p18_i3_w1200.jpeg");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 2.59);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 2);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Sprite");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "Ice Cold Sprite");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Drink");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://m.media-amazon.com/images/I/71-29yqCPzL.jpg");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 2.59);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 3);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Sprite");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "Ice Cold Sprite");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Drink");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://m.media-amazon.com/images/I/71-29yqCPzL.jpg");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 2.59);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 4);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Sprite");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "Ice Cold Sprite");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Drink");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://m.media-amazon.com/images/I/71-29yqCPzL.jpg");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 2.59);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 3);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Beer");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "Beer");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Drink");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://hips.hearstapps.com/del.h-cdn.co/assets/cm/15/11/3200x3272/54f65d39ab05d_-_183341797.jpg?resize=980:*");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 3.99);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 1);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Iced-Cappuccino");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "Iced-Cap");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Drink");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://canuckeats.com/cdn/shop/articles/spLKatwVITPVyiwTgpnIF5IeszcCuf3XHHAKTIqRLlO4x0SQUL.jpg?v=1670892344&width=500");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 1.99);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 1);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Frozen Lemonade");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "Lemonade");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Drink");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://images.chickadvisor.com/item/29532/375/eb119a87a3c6bba5c0d255511f4889ec.jpg");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 1.99);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 1);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Real Fruit Smoothie");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "Smoothie");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Drink");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://lh6.googleusercontent.com/-TqvPTYLVz9k/TYQZoes4_1I/AAAAAAAAAjA/i0U7X22uMIw/s1600/thsmooth.jpg");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 1.99);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);


        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 1);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Hot Chocolate");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "hot chocolate");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Drink");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://timhortons.ph/upload/assets/waV0epb0ljWzndJrKL01AvPs1fkz8VkF3nGACHjr0PK3rYHjeB.jpg");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 1.99);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 1);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Steak & Cheese panini");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "steak and cheese manini");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Food");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://i.ytimg.com/vi/bzOnwe7RVjQ/maxresdefault.jpg");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 4.99);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);


        // add some menu item
        contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 1);
        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "Bacon Breakfast Wrap");
        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "bacon wrap");
        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Food");
        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "https://cdn.winsightmedia.com/platform/files/public/2019-05/background/tim-hornton_1556823574.jpg?VersionId=lGLVAFSP9o2SjPPUMn7HCD25f5BzA.C8");
        contentValues.put(MENU_ITEM_COLUMN_PRICE, 1.99);
        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);

//        // add some menu item
//        contentValues = new ContentValues();
//        contentValues.put(MENU_ITEM_COLUMN_VENDOR_ID, 1);
//        contentValues.put(MENU_ITEM_COLUMN_ITEM_NAME, "");
//        contentValues.put(MENU_ITEM_COLUMN_DESCRIPTION, "");
//        contentValues.put(MENU_ITEM_COLUMN_CATEGORY, "Food");
//        contentValues.put(MENU_ITEM_COLUMN_FEATURED, 0);
//        contentValues.put(MENU_ITEM_COLUMN_IMAGE, "");
//        contentValues.put(MENU_ITEM_COLUMN_PRICE, 1.99);
//        db.insert(MENU_ITEM_DB_TABLE, null, contentValues);


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

    // gets a list of featured menu items based in their FEATURED column
    public List<ModelMenuItem> getFeaturedItems(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelMenuItem> menuItems = new ArrayList<>();
        String queryStatement = "SELECT * FROM " + MENU_ITEM_DB_TABLE +
                " WHERE " + MENU_ITEM_COLUMN_FEATURED + " = 1";
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


    // gets a single menu item based on its id
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

    // gets a list of menu items that belong to a certain vendor based on the vendor id
    public List<ModelMenuItem> getMenuItemsByVendor(int vendorId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelMenuItem> menuItems = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + MENU_ITEM_DB_TABLE +
                " WHERE " + MENU_ITEM_COLUMN_VENDOR_ID + " = ?";

        Cursor cursor = db.rawQuery(queryStatement, new String[]{String.valueOf(vendorId)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                ModelMenuItem modelMenuItem = new ModelMenuItem();
                modelMenuItem.setId(cursor.getInt(0));
                modelMenuItem.setVendorID(cursor.getInt(1));
                modelMenuItem.setItemName(cursor.getString(2));
                modelMenuItem.setItemFeatured(cursor.getString(3));
                modelMenuItem.setItemDescription(cursor.getString(4));
                modelMenuItem.setItemCategory(cursor.getString(5));
                modelMenuItem.setItemImage(cursor.getString(6));
                modelMenuItem.setItemPrice(cursor.getString(7));
                // Add the menuItem to the list
                menuItems.add(modelMenuItem);
            } while (cursor.moveToNext());

            // Close the cursor
            cursor.close();
        }
        // Close the database
        db.close();

        return menuItems;
    }

    // gets a list of menu items under the drink or food category belonging to the certain vendor based on its vendor id
    public List<ModelMenuItem> getMenuItemsByVendorAndCategory(int vendorId, String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelMenuItem> menuItems = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + MENU_ITEM_DB_TABLE +
                " WHERE " + MENU_ITEM_COLUMN_VENDOR_ID + " = ?" +
                " AND " + MENU_ITEM_COLUMN_CATEGORY + " = ?";

        Cursor cursor = db.rawQuery(queryStatement, new String[]{String.valueOf(vendorId), category});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                ModelMenuItem modelMenuItem = new ModelMenuItem();
                modelMenuItem.setId(cursor.getInt(0));
                modelMenuItem.setVendorID(cursor.getInt(1));
                modelMenuItem.setItemName(cursor.getString(2));
                modelMenuItem.setItemFeatured(cursor.getString(3));
                modelMenuItem.setItemDescription(cursor.getString(4));
                modelMenuItem.setItemCategory(cursor.getString(5));
                modelMenuItem.setItemImage(cursor.getString(6));
                modelMenuItem.setItemPrice(cursor.getString(7));
                // Add the menuItem to the list
                menuItems.add(modelMenuItem);
            } while (cursor.moveToNext());

            // Close the cursor
            cursor.close();
        }

        // Close the database
        db.close();

        return menuItems;
    }






    /****************** ORDER ITEM TABLE ******************/

    // gets a list of order items based on the order id they belong to
    public List<ModelOrderItem> getOrderItems(int orderID) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelOrderItem> orderItems = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + ORDER_ITEM_DB_TABLE +
                " WHERE " + ORDER_ITEM_COLUMN_ORDER_ID + " = ?";

        Cursor cursor = db.rawQuery(queryStatement, new String[]{String.valueOf(orderID)});

        if (cursor.moveToFirst()) { // The null check is not needed; moveToFirst() handles it
            do {
                ModelOrderItem modelOrderItem = new ModelOrderItem();
                modelOrderItem.setId(cursor.getInt(0));
                modelOrderItem.setOrderId(cursor.getInt(1));
                modelOrderItem.setItemId(cursor.getInt(2));
                modelOrderItem.setQuantity(cursor.getInt(3));
                modelOrderItem.setItemPrice(cursor.getDouble(4));
                modelOrderItem.setSubtotal(cursor.getDouble(5));

                orderItems.add(modelOrderItem);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return orderItems;
    }

    // gets the current user's current order based on their id
    public ModelOrder getCurrentOrder(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        ModelOrder order = null;

        String queryStatement = "SELECT * FROM " + ORDER_DB_TABLE +
                " WHERE " + ORDER_COLUMN_ORDER_STATUS + " = ? AND " + ORDER_COLUMN_USER_ID + " = ?";

        Cursor cursor = db.rawQuery(queryStatement, new String[]{"Pending", String.valueOf(userID)});

        if (cursor != null && cursor.moveToFirst()) {
            order = new ModelOrder();
            order.setId(cursor.getInt(cursor.getColumnIndex(ORDER_COLUMN_ID)));
            order.setUserID(userID);
            order.setVendorID(cursor.getInt(cursor.getColumnIndex(ORDER_COLUMN_VENDOR_ID)));
            order.setDate(cursor.getString(cursor.getColumnIndex(ORDER_COLUMN_ORDER_DATE)));
            order.setStatus("Pending");
            order.setTotalAmount(cursor.getDouble(cursor.getColumnIndex(ORDER_COLUMN_TOTAL_AMOUNT)));
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return order;
    }

    // changes status of an order once its shipped
    public void setOrderStatus(int orderID, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDER_COLUMN_ORDER_STATUS, status);
        db.update(ORDER_DB_TABLE, contentValues, ORDER_COLUMN_ID + " = ?", new String[]{String.valueOf(orderID)});
    }

    // gets or creates a new order
    public ModelOrder getOrCreateOrder(Integer userID, Integer vendorID, String date, Double subTotal) {
        SQLiteDatabase db = this.getReadableDatabase();
        ModelOrder order = null;

        String queryStatement = "SELECT * FROM " + ORDER_DB_TABLE +
                " WHERE " + ORDER_COLUMN_ORDER_STATUS + " = ? AND " + ORDER_COLUMN_USER_ID + " = ?";

        Cursor cursor = db.rawQuery(queryStatement, new String[]{"Pending", userID.toString()});

        if (cursor != null && cursor.moveToFirst()) {
            order = new ModelOrder();
            order.setId(cursor.getInt(cursor.getColumnIndex(ORDER_COLUMN_ID)));
            order.setUserID(userID);
            order.setVendorID(cursor.getInt(cursor.getColumnIndex(ORDER_COLUMN_VENDOR_ID)));
            order.setDate(cursor.getString(cursor.getColumnIndex(ORDER_COLUMN_ORDER_DATE)));
            order.setStatus("Pending");
            order.setTotalAmount(cursor.getDouble(cursor.getColumnIndex(ORDER_COLUMN_TOTAL_AMOUNT)));
        } else {

            order = new ModelOrder();
            order.setUserID(userID);
            order.setVendorID(vendorID);
            order.setDate(date);  // Set the current date
            order.setTotalAmount(subTotal);
            order.setStatus("Pending");
            insertOrder(db, order);
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return order;
    }

    // adds a new order item to the database
    public void addOrderItem(int orderID, int itemID, int quantity, double subTotal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDER_ITEM_COLUMN_ORDER_ID, orderID);
        contentValues.put(ORDER_ITEM_COLUMN_ITEM_ID, itemID);
        contentValues.put(ORDER_ITEM_COLUMN_QUANTITY, quantity);
        contentValues.put(ORDER_ITEM_COLUMN_ITEM_PRICE, subTotal / quantity);
        contentValues.put(ORDER_ITEM_COLUMN_SUBTOTAL, subTotal);
        db.insert(ORDER_ITEM_DB_TABLE, null, contentValues);
    }

    // inserts a new order item into the order page
    private void insertOrder(SQLiteDatabase db, ModelOrder order) {
        ContentValues values = new ContentValues();
        values.put(ORDER_COLUMN_USER_ID, order.getUserID());
        values.put(ORDER_COLUMN_VENDOR_ID, order.getVendorID());
        values.put(ORDER_COLUMN_ORDER_DATE, order.getDate());  // Make sure the date is in correct format
        values.put(ORDER_COLUMN_ORDER_STATUS, order.getStatus());
        values.put(ORDER_COLUMN_TOTAL_AMOUNT, order.getTotalAmount());

        long id = db.insert(ORDER_DB_TABLE, null, values);
        order.setId((int) id);
    }

    // Delete an order item by ID
    public void deleteOrderItem(int orderItemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.beginTransaction();
            // Delete the order item
            db.delete(ORDER_ITEM_DB_TABLE, ORDER_ITEM_COLUMN_ID + " = ?",
                    new String[]{String.valueOf(orderItemId)});

            // Commit the transaction
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // End the transaction
            db.endTransaction();
        }
    }








    /****************** USER TABLE ******************/

    // updates a user with new info(mainly password)
    public int updateUser(int id, ModelUser updatedUser){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(USER_COLUMN_FIRST_NAME, updatedUser.getFirstName());
        updatedValues.put(USER_COLUMN_LAST_NAME, updatedUser.getLastName());
        updatedValues.put(USER_COLUMN_EMAIL, updatedUser.getEmail());
        updatedValues.put(USER_COLUMN_PASSWORD, updatedUser.getPassword());
        updatedValues.put(USER_COLUMN_USER_IMAGE, updatedUser.getUserImage());
        return db.update(
                USER_DB_TABLE,
                updatedValues,
                USER_COLUMN_ID + " =?",
                new String[]{String.valueOf(id)}
        );

    }

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
        contentValues.put(USER_COLUMN_USER_IMAGE, modelUser.getUserImage());
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



    // finds gets the user's id based on their email for restoring their password
    public int getUserIdFromEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        int userId = -1;
        String query = "SELECT " + USER_COLUMN_ID + " FROM " + USER_DB_TABLE +
                " WHERE " + USER_COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
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
        String[] query = new String[]{USER_COLUMN_ID, USER_COLUMN_FIRST_NAME, USER_COLUMN_LAST_NAME, USER_COLUMN_EMAIL, USER_COLUMN_PASSWORD, USER_COLUMN_USER_IMAGE};
        Cursor cursor = db.query(USER_DB_TABLE, query, USER_COLUMN_ID + "=?", new String[]{String.valueOf(ID)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            return new ModelUser(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
        }
        else {
            return null;
        }
    }



    /****************** ORDER TABLE ******************/
    // Retrieve total number of orders
    public int getTotalOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + ORDER_DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int totalOrders = cursor.getInt(0);
        cursor.close();
        return totalOrders;
    }


    // gets a list of order completed by the specified user
    public List<ModelOrder> getPastOrders(int userID){
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelOrder> modelOrderList = new ArrayList<>();
        String queryStatement = "SELECT * FROM " + ORDER_DB_TABLE;
        Cursor cursor = db.rawQuery(queryStatement, null);
        while (cursor.moveToNext()) {
            ModelOrder modelOrder = new ModelOrder();
            modelOrder.setId(cursor.getInt(0));
            modelOrder.setUserID(cursor.getInt(1));
            modelOrder.setVendorID(cursor.getInt(2));
            modelOrder.setDate(cursor.getString(3));
            modelOrder.setStatus(cursor.getString(4));
            modelOrder.setTotalAmount(cursor.getDouble(5));

            modelOrderList.add(modelOrder);  // Add the created object to the list
        }
        cursor.close();  // Close the cursor to avoid memory leaks
        return modelOrderList;
    }


    // gets a list of ordered items belonging to a specified order
    public List<ModelOrderItem> getPastOrderItems(int orderID){
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelOrderItem> modelOrderItemList = new ArrayList<>();
        String queryStatement = "SELECT * FROM " + ORDER_ITEM_DB_TABLE +
                " WHERE " + ORDER_ITEM_COLUMN_ORDER_ID + " = ?";
        Cursor cursor = db.rawQuery(queryStatement, null);
        while (cursor.moveToNext()) {
            ModelOrderItem modelOrderItem = new ModelOrderItem();
            modelOrderItem.setId(cursor.getInt(0));
            modelOrderItem.setOrderId(cursor.getInt(1));
            modelOrderItem.setItemId(cursor.getInt(2));
            modelOrderItem.setQuantity(cursor.getInt(3));
            modelOrderItem.setItemPrice(cursor.getDouble(4));
            modelOrderItem.setSubtotal(cursor.getDouble(5));

            modelOrderItemList.add(modelOrderItem);  // Add the created object to the list
        }
        cursor.close();  // Close the cursor to avoid memory leaks
        return modelOrderItemList;
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

    // gets the model vendor object based on their id
    public ModelVendor getVendorFromId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryStatement = "SELECT * FROM " + VENDOR_DB_TABLE + " WHERE " + VENDOR_COLUMN_ID + " = ?";
        Cursor cursor = db.rawQuery(queryStatement, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            ModelVendor modelVendor = new ModelVendor();
            modelVendor.setId(cursor.getInt(cursor.getColumnIndex(VENDOR_COLUMN_ID)));
            modelVendor.setName(cursor.getString(cursor.getColumnIndex(VENDOR_COLUMN_VENDOR_NAME)));
            modelVendor.setDescription(cursor.getString(cursor.getColumnIndex(VENDOR_COLUMN_VENDOR_DESCRIPTION)));
            modelVendor.setLongitude(cursor.getDouble(cursor.getColumnIndex(VENDOR_COLUMN_VENDOR_LONGITUDE)));
            modelVendor.setLatitude(cursor.getDouble(cursor.getColumnIndex(VENDOR_COLUMN_VENDOR_LATITUDE)));
            modelVendor.setContact(cursor.getString(cursor.getColumnIndex(VENDOR_COLUMN_VENDOR_CONTACT)));
            modelVendor.setVendorVideo(cursor.getString(cursor.getColumnIndex(VENDOR_COLUMN_VENDOR_VIDEO)));
            return modelVendor;
        } else {
            return null;
        }
    }

    // gets the vendor's name by their id
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
        // closes database avoiding memory leaks
        db.close();

        return vendorName;
    }




    /****************** IMAGES TABLE ******************/

    // gets a list of images belonging to a certain vendor by their id
    public List<ModelVendorImage> getVendorImages(int vendorID) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelVendorImage> allImages = new ArrayList<>();

        // Use placeholders (?) in the query and provide the actual values in the selectionArgs array
        String queryStatement = "SELECT * FROM " + VENDOR_IMAGES_DB_TABLE +
                " WHERE " + VENDOR_IMAGES_COLUMN_VENDOR_ID + " = ?";

        // Pass the selection arguments (vendorID) as an array
        String[] selectionArgs = { String.valueOf(vendorID) };

        Cursor cursor = db.rawQuery(queryStatement, selectionArgs);

        while (cursor.moveToNext()) {
            ModelVendorImage modelVendorImage = new ModelVendorImage();
            modelVendorImage.setId(cursor.getInt(0));
            modelVendorImage.setVendorID(cursor.getInt(1));
            modelVendorImage.setImage(cursor.getString(2));
            allImages.add(modelVendorImage);
        }

        cursor.close();
        return allImages;
    }



}
