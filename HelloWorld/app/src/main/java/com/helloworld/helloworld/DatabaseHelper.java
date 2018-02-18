package com.helloworld.helloworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by VijayVijay on 14-01-2018.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "EMP";

    public static String TABLE_NAME_PRODUCT = "product";

    public static String COLUMN_NAME = "name";
    public static String COLUMN_ID = "id";
    public static String COLUMN_PRICE = "price";


    public static String createEmpTable = "CREATE TABLE " +
            TABLE_NAME_PRODUCT + "("
            + COLUMN_ID + " TEXT ,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PRICE + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, Environment.getExternalStorageDirectory() + "/" + DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createEmpTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addEmp(String id, String name, String salary) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PRICE, salary);

        db.insert(TABLE_NAME_PRODUCT, null, values);
        Log.i(TAG, "addEmp: record inserted....");
        db.close();
    }

    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_PRODUCT, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String price = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE));
                Product product = new Product();
                product.setName(name);
                product.setPrice(price);
                productList.add(product);

                cursor.moveToNext();
            }
        }
        return productList;
    }

    public boolean deleteTransaction(String name) {
        getWritableDatabase().delete(TABLE_NAME_PRODUCT, COLUMN_NAME + "=?", new String[]{String.valueOf(name)});
        return false;
    }

    public void updateTable(String name, String price) {
        ContentValues cv = new ContentValues();
        //cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_PRICE, price);

        getWritableDatabase().update(TABLE_NAME_PRODUCT, cv, COLUMN_NAME + name, null);
    }
}
