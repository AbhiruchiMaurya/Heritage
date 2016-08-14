package com.abhiruchimaurya.heritage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Abhiruchi Maurya on 22-07-2016.
 */
public class MyAdapter extends BaseAdapter {
    private final ArrayList<Heritage> ml;

    private LayoutInflater lf = null;
    Context ctx = null;

    public MyAdapter(Activity activity, ArrayList<Heritage> l) {
        ctx = activity.getApplicationContext();
        ml = l;
        lf = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return ml.size();
    }

    @Override
    public Object getItem(int position) {
        return ml.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = lf.inflate(R.layout.customview, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        ImageView im = (ImageView) convertView.findViewById(R.id.im);
        Heritage heritage = ml.get(position);
        byte[] img1 = heritage.getMonu_img();
        ByteArrayInputStream bis = new ByteArrayInputStream(img1);
        Bitmap bimg = BitmapFactory.decodeStream(bis);

        tv.setText(heritage.getMonument_name());
        im.setImageBitmap(bimg);

        return convertView;
    }

}
