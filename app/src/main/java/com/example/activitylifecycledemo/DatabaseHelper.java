package com.example.activitylifecycledemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "dbContactManager";
    private static String TABLE_NAME ="tblContact";
    private static String KEY_ID = "ID";
    private static String KEY_NAME = "Name";
    private static String KEY_PHONENo = "PhoneNo";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME + " TEXT," + KEY_PHONENo + " TEXT" + ")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //Add Contacts
    public int AddContacts(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, contact.get_Name());
        cv.put(KEY_PHONENo, contact.get_PhoneNo());

        int ID = (int)db.insert(TABLE_NAME,null,cv);
        return ID;
    }

    public ArrayList<Contact> GetAllContacts()
    {
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor c= db.rawQuery(query,null);
        if(c.moveToFirst())
        {
            do {
               // Contact contact = new Contact();
               // contact.set_Name(c.getString(1));
                contactList.add(new Contact(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2)));
                //contactList.add(c.getString(0) + "," + c.getString(1)  + "," +
                       // c.getString(2));
            }while(c.moveToNext());
        }
        return contactList;
    }

    public void DeleteContact(Contact c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID + "=?",new String[]{String.valueOf(c.get_ID())});
    }

    public int UpdateContact(Contact c)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,c.get_Name());
        cv.put(KEY_PHONENo,c.get_PhoneNo());

        int ID = db.update(TABLE_NAME,cv,KEY_ID + "=?",new String[]{String.valueOf(c.get_ID())});
        return ID;
    }
    Contact GetContactDetails(int ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TABLE_NAME,new String[]{KEY_ID,KEY_NAME,KEY_PHONENo},KEY_ID + "=?",
                new String[]{String.valueOf(ID)},null,null,null);
        if(c!=null)
            c.moveToFirst();
        Contact contact = new Contact(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2));
        return contact;
    }


}
