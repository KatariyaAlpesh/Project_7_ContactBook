package com.example.project_7_contactbook;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project_7_contactbook.Mainactivity_2.MainActivity_2;

public class DBHelper extends SQLiteOpenHelper
{
    public DBHelper(MainActivity mainActivity)
    {
        super(mainActivity , "ContactBook" , null , 1);
    }

    public DBHelper(MainActivity_2 mainActivity_2)
    {
        super(mainActivity_2 , "ContactBook" , null , 1);
    }

    public DBHelper(MainActivity_3 mainActivity_3)
    {
        super(mainActivity_3 , "ContactBook" , null , 1);
    }

    public DBHelper(MainActivity_Update_Image_Page mainActivity_update_image_page)
    {
        super(mainActivity_update_image_page , "ContactBook" , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

        sqLiteDatabase.execSQL("create table contact (id INTEGER primary key autoincrement , id TEXT , name TEXT , number TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

        sqLiteDatabase.execSQL("drop table if exists contact");

    }

    public Cursor getData()
    {

        String select = "Select * from contact";

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(select , null);

        return cursor;
    }

    public Boolean saveButton( String nameTEXT, String numberTEXT)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("name" , nameTEXT);
        contentValues.put("number" , numberTEXT);

        long result = sqLiteDatabase.insert("contact" , null , contentValues);

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public void UpdateData2(int id, String name , String number)
    {
        String update2 = "update contact set name = '"+name+"' , number = '"+number+"'  where id = '"+id+"' " ;

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.execSQL(update2);
    }

    public void deleteRecord(int id)
    {

        String delete = "delete from contact where id = '"+id+"' ";

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.execSQL(delete);

    }

//    public void UpdateData1(int id, String name, String number)
//    {
//        String update1 = "update contact where id = '"+id+"' , set name = '"+name+"' , number = '"+number+"' ";
//
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//
//        sqLiteDatabase.execSQL(update1);
//    }
}
