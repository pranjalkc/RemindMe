package com.example.studentapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class PictureAdapter extends BaseAdapter {

    private List<PictureClass> allPics;

    public PictureAdapter(List<PictureClass> allPics) {
        this.allPics = allPics;
    }

    @Override
    public int getCount() {
        return allPics.size();
    }

    @Override
    public Object getItem(int i) {
        return allPics.get(i);
    }

    @Override
    public long getItemId(int i) {
        return allPics.get(i).gid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        Contact_Holder contactHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            contactHolder=new PictureAdapter.Contact_Holder();

            //Fetch File rowpicture Step - 3
            row=layoutInflater.inflate(R.layout.activity_rowpicture,parent,false);
            contactHolder.tx_name=(TextView)row.findViewById(R.id.text1);
            contactHolder.tx_image=(ImageView)row.findViewById(R.id.pic1);
            ViewGroup.LayoutParams layoutParams=contactHolder.tx_image.getLayoutParams();
            layoutParams.height=600;
            layoutParams.width=WRAP_CONTENT;
            //GridView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
            contactHolder.tx_image.setLayoutParams(layoutParams);
            row.setTag(contactHolder);
        }
        else
        {
            contactHolder=(PictureAdapter.Contact_Holder)row.getTag();
        }

        //Fetch File PictureClass Step - 4
        PictureClass pictureClass=(PictureClass)this.getItem(position);
        contactHolder.tx_name.setText(pictureClass.getGname());
        byte[]gimage=pictureClass.getGimage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(gimage,0,gimage.length);
        contactHolder.tx_image.setImageBitmap(bitmap);
        return row;
    }

    //Step - 2
    static  class Contact_Holder
    {
        TextView tx_name;
        ImageView tx_image;
    }
}
