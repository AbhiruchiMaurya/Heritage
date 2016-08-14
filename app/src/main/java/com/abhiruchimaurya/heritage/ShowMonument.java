package com.abhiruchimaurya.heritage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.Locale;

public class ShowMonument extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextToSpeech tts;

    ImageView iv;
    TextView tvname,tvcreator,tvdes,tvcity;
    Button read,map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_monuments);

        tts = new TextToSpeech(this, this);
        Intent i=getIntent();
        Heritage h= (Heritage) i.getSerializableExtra("info");


        byte[] img=h.getMonu_img();
        ByteArrayInputStream bis=new ByteArrayInputStream(img);
        Bitmap image= BitmapFactory.decodeStream(bis);


        tvname=(TextView)findViewById(R.id.tvname);
        tvcreator=(TextView)findViewById(R.id.tvcreator);
        tvdes=(TextView)findViewById(R.id.tvdes);
        tvcity=(TextView)findViewById(R.id.tvcity);

        iv=(ImageView)findViewById(R.id.iv);

        read=(Button)findViewById(R.id.read);
        map=(Button)findViewById(R.id.map);

        tvname.setText(h.getMonument_name());
        tvcity.setText("City: "+h.getCity_name());
        tvdes.setText( "Description:\n"+h.getDescription());
        tvcreator.setText("Creator's Name:\n"+h.getCreator_name());

        iv.setImageBitmap(image);
    }
    @SuppressLint("NewApi")
    public void listen(View v){
       String combined=""+tvname.getText()+"Its in\n"+tvcity.getText()+"Created by \n"+tvcreator.getText()+"\n"+tvdes.getText();


        if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(combined, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak(combined, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.LANG_NOT_SUPPORTED || status == TextToSpeech.LANG_MISSING_DATA)

            Toast.makeText(this, "notsupported" + status, Toast.LENGTH_SHORT).show();

        else {
            tts.setLanguage(Locale.ENGLISH);
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        tts.shutdown();
    }

    public void map(View v){

        Intent i=new Intent(this,MyMaps.class);
        i.putExtra("mapinfo",tvname.getText());
        startActivity(i);
    }

}