package com.blankdev.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Blank Dev on 25/12/16.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    //constants for db name and version
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    //constants for table and columns
    public static final String TABLE_CONTACTS = "contacts";
    public static final String CONTACT_ID = "_id";
    public static final String CONTACT_NAME = "contactName";
    public static final String CONTACT_PHONE = "contactPhone";
    public static final String CONTACT_CREATED_ON = "contactCreationTimeStamp";

    public static final String[] ALL_COLUMNS = {CONTACT_ID,CONTACT_NAME,CONTACT_PHONE,CONTACT_CREATED_ON};

    private static final String CREATE_TABLE = "create table " + TABLE_CONTACTS + " ( " +
                                               CONTACT_ID + " integer primary key autoincrement , " +
                                               CONTACT_NAME + " text , " +
                                               CONTACT_PHONE + " text , " +
                                               CONTACT_CREATED_ON + " text default current_timestamp )";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }
}
