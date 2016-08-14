package com.abhiruchimaurya.heritage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Abhiruchi Maurya on 22-07-2016.
 */
public class HeritageHelper extends SQLiteOpenHelper {
    Context context;



    public HeritageHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HeritageConstant.QUERY_CITY);
        db.execSQL(HeritageConstant.QUERY_MONUMENT);
        Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
