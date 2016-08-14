package com.abhiruchimaurya.heritage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addcity extends AppCompatActivity {
    HeritageDbManager heritageDbManager=null;
    EditText editText;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcity);
        heritageDbManager=new HeritageDbManager(this);
        editText=(EditText)findViewById(R.id.editText);
    }

    public void add(View v)
    {
        String city_name=editText.getText().toString().trim();
        heritageDbManager.openDb();
        long row=heritageDbManager.addCity(city_name);
        if(row>0)
        {
            Toast.makeText(this, row+" city added", Toast.LENGTH_SHORT).show();
        }
        heritageDbManager.closeDb();

    }
}
