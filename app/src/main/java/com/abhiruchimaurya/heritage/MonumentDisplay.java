package com.abhiruchimaurya.heritage;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MonumentDisplay extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ArrayList<Heritage> monuList;
    ListView lv;
    Heritage heritage;
    HeritageDbManager heritageDbManager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monument_display);
        Intent intent = getIntent();
      String cn=  intent.getStringExtra("cname");

        lv = (ListView) findViewById(R.id.lv);

        monuList = new ArrayList<>();
        heritageDbManager = new HeritageDbManager(this);
        heritageDbManager.openDb();
        lv.setOnItemClickListener(this);


        Cursor c = heritageDbManager.fetchname(cn);

        if (c != null && c.moveToFirst()) {
            do {

                String monuname = c.getString(c.getColumnIndex(HeritageConstant.COLUMN_MONU_NAME));
                String cname=c.getString(c.getColumnIndex(HeritageConstant.COLUMN_MONU_CNAME));
                String cityname=c.getString(c.getColumnIndex(HeritageConstant.COLUMN_CITY_NAME));
                String description=c.getString(c.getColumnIndex(HeritageConstant.COLUMN_MONU_DESCRIPTION));
                byte[] img = c.getBlob(c.getColumnIndex(HeritageConstant.COLUMN_MONU_PIC));

                heritage = new Heritage();
                heritage.setMonument_name(monuname);
                heritage.setDescription(description);
                heritage.setCreator_name(cname);
                heritage.setCity_name(cityname);
                heritage.setMonu_img(img);


                monuList.add(heritage);


            }
            while (c.moveToNext());




        }
        c.close();

            MyAdapter ad = new MyAdapter(this, monuList);
            lv.setAdapter(ad);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        heritage=monuList.get(position);
        Intent i=new Intent(this,ShowMonument.class);
        i.putExtra("info",heritage);
        startActivity(i);



    }
}