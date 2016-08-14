package com.abhiruchimaurya.heritage;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CityList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<Heritage> citylist=null;

    ListView listView;
    HeritageDbManager heritageDbManager=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        listView = (ListView) findViewById(R.id.listView);
        citylist = new ArrayList<>();

        listView.setOnItemClickListener(this);
        heritageDbManager = new HeritageDbManager(this);
        heritageDbManager.openDb();

        Cursor c = heritageDbManager.fetch();    //citylist
        if (c != null && c.moveToFirst()) {
            do {

                String cityname = c.getString(c.getColumnIndex(HeritageConstant.COLUMN_CITY_NAME));



                    Heritage heritage = new Heritage();
                    heritage.setCity_name(cityname);

                    citylist.add(heritage);


                }
                while (c.moveToNext()) ;
                ArrayAdapter<Heritage> arrayAdapter = new ArrayAdapter<Heritage>(this, android.R.layout.simple_list_item_multiple_choice, citylist);
                listView.setAdapter(arrayAdapter);


            }
        }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Heritage heritage=citylist.get(position);

            String cityname=heritage.getCity_name();
        Intent intent=new Intent(this,MonumentDisplay.class);
        intent.putExtra("cname",cityname);




        startActivity(intent);

    }
}
