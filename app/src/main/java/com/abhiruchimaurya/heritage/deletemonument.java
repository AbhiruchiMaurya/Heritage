package com.abhiruchimaurya.heritage;

import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class deletemonument extends AppCompatActivity implements AdapterView.OnItemClickListener,DialogInterface.OnClickListener{
    HeritageDbManager heritageDbManager=null;
    ListView dellist;
    Monument setget=null;
    ArrayList<Monument> sglist=null;
    Bitmap image;
    AlertDialog ad;
    AlertDialog.Builder adb;
    String nm=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletemonument);
        dellist=(ListView)findViewById(R.id.dellist);


        heritageDbManager=new HeritageDbManager(this);
        heritageDbManager.openDb();
        sglist=new ArrayList<Monument>();

        populateList();
        dellist=(ListView)findViewById(R.id.dellist);
        ArrayAdapter<Monument> ad=new ArrayAdapter<Monument>(this,android.R.layout.simple_list_item_1,sglist);

        dellist.setAdapter(ad);
        ad.notifyDataSetChanged();
        dellist.refreshDrawableState();
        dellist.setOnItemClickListener(this);




    }

    void populateList() {
        Cursor c = heritageDbManager.fetchmonu();
        if (c != null && c.moveToFirst()) {

            do{
                String name = c.getString(c.getColumnIndex(HeritageConstant.COLUMN_MONU_NAME));
                String city = c.getString(c.getColumnIndex(HeritageConstant.COLUMN_CITY_NAME));
                String description = c.getString(c.getColumnIndex(HeritageConstant.COLUMN_MONU_DESCRIPTION));
                String creator = c.getString(c.getColumnIndex(HeritageConstant.COLUMN_MONU_CNAME));
                byte[] img = c.getBlob(c.getColumnIndex(HeritageConstant.COLUMN_MONU_PIC));
                ByteArrayInputStream bis= new ByteArrayInputStream(img);
                image= BitmapFactory.decodeStream(bis);


                setget = new Monument();
                setget.setMonument_name(name);
                setget.setCity_name(city);
                setget.setDescription(description);
                setget.setCreator_name(creator);
                setget.setMonu_img(img);


                sglist.add(setget);


            }
            while (c.moveToNext());

        }}


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        showDialog();

        Monument setget=sglist.get(position);
        nm=  setget.getMonument_name();



    }

    public  void showDialog(){
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle("Inforamtion");
        adb.setMessage("Are you sure you want to delete this monument?");
        adb.setPositiveButton("yes", this);
        adb.setNegativeButton("no",this);
        ad=adb.create();
        ad.show();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which)
        {
            case DialogInterface.BUTTON_POSITIVE:

                heritageDbManager.deleteData(nm);
                Toast.makeText(this,"Monument deleted",Toast.LENGTH_SHORT).show();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                ad.dismiss();
                break;

        }

    }


}

