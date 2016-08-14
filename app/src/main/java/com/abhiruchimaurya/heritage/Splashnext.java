package com.abhiruchimaurya.heritage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Splashnext extends AppCompatActivity {

    TextView explore;
    Button admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashnext);

        explore=(TextView)findViewById(R.id.explore);
        admin=(Button)findViewById(R.id.admin);

    }
    public void explore(View v){
        Intent intent=new Intent(this,CityList.class);
        startActivity(intent);
    }
    public void admin(View v){
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.mymenu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.aboutUs:
                Intent i = new Intent("com.abhiruchimaurya.heritage.aboutus");
                startActivity(i);
                break;
            case R.id.exit:
                finish();
                break;

        } return false;
    }
}
