package com.abhiruchimaurya.heritage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Abhiruchi Maurya on 22-07-2016.
 */
public class HeritageDbManager {
    HeritageHelper heritageHelper = null;
    SQLiteDatabase sqLiteDatabase = null;

    public HeritageDbManager(Context context) {
        heritageHelper = new HeritageHelper(context, HeritageConstant.DATABASE_NAME, null, 1);
    }

    public void openDb() {
        sqLiteDatabase = heritageHelper.getWritableDatabase();
    }

    public void closeDb() {
        if (sqLiteDatabase != null)

            sqLiteDatabase.close();
    }

    public long addCity(String city_name) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(HeritageConstant.COLUMN_CITY_NAME, city_name);
        long l = sqLiteDatabase.insert(HeritageConstant.TABLE_NAME_CITY, null, contentValues);
        return l;
    }

    public Cursor fetch() {
        Cursor cursor = sqLiteDatabase.query(HeritageConstant.TABLE_NAME_CITY, null, null, null, null, null, null);
        return cursor;

    }

    public long insertData(String name, String city, String creator, String description, byte[] img) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(HeritageConstant.COLUMN_MONU_NAME, name);
        contentValues.put(HeritageConstant.COLUMN_CITY_NAME, city);
        contentValues.put(HeritageConstant.COLUMN_MONU_CNAME, creator);
        contentValues.put(HeritageConstant.COLUMN_MONU_DESCRIPTION, description);
        contentValues.put(HeritageConstant.COLUMN_MONU_PIC, img);
        long l = sqLiteDatabase.insert(HeritageConstant.TABLE_NAME_MONUMENT, null, contentValues);

        return l;
    }

    public Cursor fetchmonu() {
        Cursor cursor = sqLiteDatabase.query(HeritageConstant.TABLE_NAME_MONUMENT, null, null, null, null, null, null);
        return cursor;
    }





         public Cursor fetchname (String name){

             String[] args = {name};
             Cursor c = sqLiteDatabase.query(HeritageConstant.TABLE_NAME_MONUMENT, null, HeritageConstant.COLUMN_CITY_NAME + " =? ", args, null, null, null);

             return c;


         }

    public int deleteData(String name)
    {
        String[] args={name};
        int rw=sqLiteDatabase.delete(HeritageConstant.TABLE_NAME_MONUMENT,HeritageConstant.COLUMN_MONU_NAME+" =?",args);
        return rw;
    }

    public int updateData(String mname, String discription, byte[] img)
    {
        ContentValues cv=new ContentValues();
        cv.put(HeritageConstant.COLUMN_MONU_NAME,mname);


                cv.put(HeritageConstant.COLUMN_MONU_DESCRIPTION,discription);
                cv.put(HeritageConstant.COLUMN_MONU_PIC,img);
        String[] args={mname};
        int rw=sqLiteDatabase.update(HeritageConstant.TABLE_NAME_MONUMENT,cv,HeritageConstant.COLUMN_MONU_NAME+" =?",args);
                return rw;
    }



     }