package com.abhiruchimaurya.heritage;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class updatemonument extends AppCompatActivity {
    EditText etdescription;
    TextView etcity,etcreator,etname;
    Button update;
    ImageView iv,cam,gallery;
    final static int cameraData =0;

    private int PICK_IMAGE_REQUEST = 1;
    byte[] imgarr;
    HeritageDbManager hdb=null;
    SQLiteDatabase sqLiteDatabase=null;
    Bitmap image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatemonument);

        Intent i =getIntent();
        Monument m= (Monument) i.getSerializableExtra("details");

        hdb=new HeritageDbManager(this);


        etname=(TextView) findViewById(R.id.etname);
        etdescription=(EditText)findViewById(R.id.etdescription);
        etcreator=(TextView) findViewById(R.id.etcreator);
        update=(Button)findViewById(R.id.update);
        etcity=(TextView)findViewById(R.id.etcity);

        iv=(ImageView)findViewById(R.id.iv);
        cam=(ImageView)findViewById(R.id.imageView);
        gallery=(ImageView)findViewById(R.id.imageView2);

        String st=""+m.getMonument_name();
        String st1=""+m.getDescription();
        String st2=""+m.getCreator_name();
        String st3 =""+m.getCity_name();

        etname.setText(st);
        etcity.setText(st3);
        etdescription.setText(st1);
        etcreator.setText(st2);
        byte[] image=m.getMonu_img();
        ByteArrayInputStream bis=new ByteArrayInputStream(image);
        Bitmap img= BitmapFactory.decodeStream(bis);
        iv.setImageBitmap(img);


    }

    public void cam(View v){
        Intent i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,cameraData);
    }

    public void gallery(View v){

        Intent i = new Intent();

        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), PICK_IMAGE_REQUEST);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ImageView imageview= (ImageView)findViewById(R.id.iv);
                imageview.setImageBitmap(image);



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

    public void update(View v) {


        String name=etname.getText().toString();

        String description=etdescription.getText().toString();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        hdb.openDb();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        imgarr = stream.toByteArray();
        hdb.updateData(name,description,imgarr);
        Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        hdb.closeDb();
    }







}