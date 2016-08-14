package com.abhiruchimaurya.heritage;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MonumentList extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ArrayList<Monument> monumentList;
    ListView lv1;
    HeritageDbManager heritageDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monument_list);
        lv1=(ListView)findViewById(R.id.lv1);


        monumentList=new ArrayList<>();
        lv1.setOnItemClickListener(this);

        heritageDbManager=new HeritageDbManager(this);
        heritageDbManager.openDb();


        Cursor c=heritageDbManager.fetchmonu();
        if(c!=null && c.moveToFirst())
        {
            do {
                String name=c.getString(c.getColumnIndex(HeritageConstant.COLUMN_MONU_NAME));
                String cname=c.getString(c.getColumnIndex(HeritageConstant.COLUMN_MONU_CNAME));
                String discip=c.getString(c.getColumnIndex(HeritageConstant.COLUMN_MONU_DESCRIPTION));
                String city_name=c.getString(c.getColumnIndex(HeritageConstant.COLUMN_CITY_NAME));
                byte[] img=c.getBlob(c.getColumnIndex(HeritageConstant.COLUMN_MONU_PIC));
                Monument m=new Monument();
                m.setMonument_name(name);
                m.setCreator_name(cname);
                m.setCity_name(city_name);
                m.setDescription(discip);
                m.setMonu_img(img);
                monumentList.add(m);

            }while(c.moveToNext());

            c.close();
            ArrayAdapter<Monument> arrayAdapter = new ArrayAdapter<Monument>(this, android.R.layout.simple_list_item_multiple_choice, monumentList);
            lv1.setAdapter(arrayAdapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Monument m=monumentList.get(position);
        Intent i=new Intent(this,updatemonument.class);
        i.putExtra("details",m);
        startActivity(i);

    }
}
