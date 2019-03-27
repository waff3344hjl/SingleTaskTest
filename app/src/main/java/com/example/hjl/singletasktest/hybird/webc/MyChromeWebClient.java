package com.example.hjl.singletasktest.hybird.webc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.hjl.singletasktest.hybird.HybirdActivity;
import com.example.hjl.singletasktest.util.PhotoUtil;

import java.io.File;

//自定义 WebChromeClient 辅助WebView处理图片上传操作【<input type=file> 文件上传标签】
public class MyChromeWebClient extends WebChromeClient {
    public static String TAG = "TAG_MyChromeWebClient";
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private Uri imageUri;
    private boolean videoFlag = false;
    private Activity activity;
    private final static int PHOTO_REQUEST = 100;
    private final static int VIDEO_REQUEST = 120;

    public MyChromeWebClient(  boolean videoFlag, Activity activity) {
        this.videoFlag = videoFlag;
        this.activity = activity;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    // For Android 3.0-
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        Log.e(TAG, "openFileChoose(ValueCallback<Uri> uploadMsg)");
        mUploadMessage = uploadMsg;
        if (videoFlag) {
            recordVideo();
        } else {
            takePhoto();
        }

    }

    // For Android 3.0+
    public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
        Log.e(TAG, "openFileChoose( ValueCallback uploadMsg, String acceptType )");
        mUploadMessage = uploadMsg;
        if (videoFlag) {
            recordVideo();
        } else {
            takePhoto();
        }
    }

    //For Android 4.1
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        Log.e(TAG, "openFileChoose(ValueCallback<Uri> uploadMsg, String acceptType, String capture)");
        mUploadMessage = uploadMsg;
        if (videoFlag) {
            recordVideo();
        } else {
            takePhoto();
        }
    }

    // For Android 5.0+
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        Log.e(TAG, "onShowFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)");
        mUploadCallbackAboveL = filePathCallback;
        if (videoFlag) {
            recordVideo();
        } else {
            takePhoto();
        }
        return true;
    }


    /**
     * 拍照
     */
    private void takePhoto() {
        File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/" + SystemClock.currentThreadTimeMillis() + ".jpg");
        imageUri = Uri.fromFile(fileUri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(activity.getBaseContext(), activity.getPackageName() + ".fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
        }
//        PhotoUtil.takePicture(activity, imageUri, PHOTO_REQUEST);
        PhotoUtil.openPic(activity,PHOTO_REQUEST);
    }

    /**
     * 录像
     */
    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        //限制时长
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
        //开启摄像机
        activity.startActivityForResult(intent, VIDEO_REQUEST);
    }

}

