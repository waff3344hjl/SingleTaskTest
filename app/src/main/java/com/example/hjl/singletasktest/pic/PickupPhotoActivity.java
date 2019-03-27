package com.example.hjl.singletasktest.pic;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.hjl.singletasktest.R;

import java.io.File;
import java.io.FileNotFoundException;

public class PickupPhotoActivity extends AppCompatActivity {
    public static final int PICK_PHOTO_FROM_CAMERA=1;
    public static final int PICK_PHOTO_FROM_GALLERY=0;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_photo);
        iv = (ImageView) findViewById(R.id.iv);
    }
    public void gallery(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
//        Intent intent = new Intent(
//                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_PHOTO_FROM_GALLERY);
    }
    public void photo(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_PHOTO_FROM_CAMERA);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        // 选取图片
        Bitmap bitmap = null;
        Bitmap bitmap1 =null;
        if (requestCode == PICK_PHOTO_FROM_CAMERA && resultCode == Activity.RESULT_OK) {
            bitmap = data.getParcelableExtra("data");// 没做图片压缩处理
            bitmap1 = bitmap;
        }
        if (requestCode == PICK_PHOTO_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            ContentResolver resolver = getContentResolver();
            Uri uri = data.getData();

            Log.d("uri", uri.toString());
            try {
                bitmap = ImageHelper.decodeSampledBitmapFromResolver(resolver, uri, 100, 100);
                 bitmap1 =  ImageHelper. getRoundedCornerBitmap(bitmap,100);
            } catch (FileNotFoundException e) {
                Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        // 保存图片
        if (bitmap != null) {
           iv.setImageBitmap(bitmap1);
        }

    }
}
