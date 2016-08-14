package com.abhiruchimaurya.heritage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    SharedPreferences sp=null;
    SharedPreferences.Editor spe=null;
    EditText editText,editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp=getSharedPreferences("AdminReg",MODE_PRIVATE);
        spe=sp.edit();
        spe.putString("adminid","scott");
        spe.putString("adminpass","scott");
        spe.commit();

        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);

        
    }

    public void adminLogin(View v)
    {
        String id=editText.getText().toString();
        String pass=editText2.getText().toString();
        sp=getSharedPreferences("AdminReg",MODE_PRIVATE);
        String uid=sp.getString("adminid","");
        String upass=sp.getString("adminpass","");

        if(id.equals(uid) && pass.equals(upass))
        {
            Intent i=new Intent(this,Admin.class);
            startActivity(i);
        }


    }




}
