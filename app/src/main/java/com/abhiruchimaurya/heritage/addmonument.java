package com.abhiruchimaurya.heritage;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class addmonument extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText etname,etcreator,etdescription;
    Spinner etcity;
    Button add;
    ImageView iv,cam,gallery;
    final static int cameraData =0;

    private int PICK_IMAGE_REQUEST = 1;
    byte[] imgarr;
    HeritageDbManager hdb=null;
    SQLiteDatabase sqLiteDatabase=null;
    Bitmap image;
    List<Heritage> categories;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmonument);




        etname = (EditText) findViewById(R.id.etname);
        // etcity = (EditText) findViewById(R.id.etcity);
        etdescription = (EditText) findViewById(R.id.etdescription);
        etcreator = (EditText) findViewById(R.id.etcreator);
        add = (Button) findViewById(R.id.add);
        iv = (ImageView) findViewById(R.id.iv);
        cam = (ImageView) findViewById(R.id.imageView);
        gallery = (ImageView) findViewById(R.id.imageView2);
        etcity = (Spinner) findViewById(R.id.etcity);
        hdb = new HeritageDbManager(this);

        //spinner.setOnItemSelectedListener(this);
        etcity.setOnItemSelectedListener(this);
        categories = new ArrayList<>();

        hdb.openDb();

        Cursor c = hdb.fetch();    //citylist
        if (c != null && c.moveToFirst()) {
            do {

                String cityname = c.getString(c.getColumnIndex(HeritageConstant.COLUMN_CITY_NAME));



                Heritage heritage = new Heritage();
                heritage.setCity_name(cityname);

                categories.add(heritage);


            }
            while (c.moveToNext());

            ArrayAdapter<Heritage> dataAdapter = new ArrayAdapter<Heritage>(this, android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            etcity.setAdapter(dataAdapter);




        }
    }

    public void gallery(View v){

        Intent i = new Intent();

        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                ImageView imageView = (ImageView) findViewById(R.id.iv);
                imageView.setImageBitmap(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if( requestCode ==cameraData && resultCode== RESULT_OK) {
            Bundle extras = data.getExtras();
            image = (Bitmap) extras.get("data");

            iv.setImageBitmap(image);

        }

    }

    public void cam(View v){
        Intent i= new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,cameraData);



    }
    public void add(View v) {

        hdb.openDb();
        String name=etname.getText().toString();
        String city=etcity.getSelectedItem().toString();
        String creator=etcreator.getText().toString();
        String description=etdescription.getText().toString();


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        imgarr = stream.toByteArray();

        long row=hdb.insertData(name,city,creator,description,imgarr);
        if(row>0)
        {
            Toast.makeText(this, "Monument Added", Toast.LENGTH_SHORT).show();
        }

        hdb.closeDb();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Heritage h=categories.get(position);

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}