package com.example.studentapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class GalleryActivity extends AppCompatActivity {
    EditText edtName1;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;
    private Db appDatabase;
    DbHelper obj;
    final int REQUEST_CODE_GALLERY=999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        init();
        //obj = new DbHelper(this);
        appDatabase = Db.getDb(GalleryActivity.this);

        //Choose
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Choose Image file Step 1
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
        });
        //Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //Add Image
                    String gname=edtName1.getText().toString();
                    RoomDAO roomDAO = appDatabase.getRoomDAO();
                    PictureClass picture = new PictureClass();
                    picture.setGname(gname);
                    picture.setGimage(imageviewtobyte(imageView));
                    //call method for byte[]
                    roomDAO.Insert(picture);
                    //obj.addgallery(gname,imageviewtobyte(imageView));
                    showmsg("Data Added");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        //List
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ShowGallery.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Check which request it is that we're responding to
        // Make sure the request was successful
        if(requestCode==REQUEST_CODE_GALLERY && resultCode==RESULT_OK && data!=null)
        {
            // Get the URI that points to the selected contact
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
                showmsg("Image Display");

            } catch (Exception ex) {
                showmsg(ex.getMessage());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public byte[] imageviewtobyte(ImageView imageView)
    {
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytearray=stream.toByteArray();
        return bytearray;
    }

    public void showmsg(String msg)
    {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
    private void init(){
        edtName1 = (EditText) findViewById(R.id.edtName1);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnList = (Button) findViewById(R.id.btnList);
        imageView = (ImageView) findViewById(R.id.imageView);
    }
}
