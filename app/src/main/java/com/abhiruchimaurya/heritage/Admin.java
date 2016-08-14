package com.abhiruchimaurya.heritage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
    public void addCity(View v)
    {
        Intent intent=new Intent(this,addcity.class);
        startActivity(intent);
    }

    public void addMonu(View v)
    {
        Intent intent=new Intent(this,addmonument.class);
        startActivity(intent);
    }

    public void updateMonu(View v)
    {
        Intent intent=new Intent(this,MonumentList.class);
        startActivity(intent);

    }

    public void deleteMonu(View v)
    {
        Intent intent=new Intent(this,deletemonument.class);
        startActivity(intent);
    }
}
