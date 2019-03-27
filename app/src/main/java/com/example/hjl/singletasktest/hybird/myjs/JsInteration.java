package com.example.hjl.singletasktest.hybird.myjs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.hjl.singletasktest.config.Config;
import com.example.hjl.singletasktest.hybird.jsModle.User;
import com.example.hjl.singletasktest.util.PhotoUtil;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * JS调用android的接口类
 * 由于java与js跨平台，因此 除去基本数据类型外的其他数据都需要用Gson转化为JSON字符串
 */
public class JsInteration {
    public static String JSINTERFACE = "jsInterface";
    private Activity mActivity;
    private WebView mWebView;


    public JsInteration(Activity mActivity, WebView mWebView) {
        this.mActivity = mActivity;
        this.mWebView = mWebView;

    }

    @JavascriptInterface//一定要写，不然h5调不到这个方法
    public String back() {
        return "hello world";
    }

    @JavascriptInterface//一定要写，不然h5调不到这个方法
    public String getSTs() {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            data.add(i + 1);
        }
        Gson gson = new Gson();
        String d = gson.toJson(data);
        Log.e(Config.LOGTAG, "getData: dddd" + d);
        return d;
    }

    @JavascriptInterface
    public String getUser() {
        User data = new User();
        data.setName("小1");
        data.setAge("22");
        data.setSex("女");
        Gson gson = new Gson();
        String d = gson.toJson(data);
        Log.e(Config.LOGTAG, "getData: dddd" + d);
        return d;
    }

    public static final int TAKE_PHOTO = 1000;

    @JavascriptInterface
    public void takePhoto() {
//        调用系统摄像头
        Log.e(Config.LOGTAG, "调用系统摄像头");
        startCamera(TAKE_PHOTO, filePath);
    }

    @JavascriptInterface
    public void getPhotoFromTK() {
        //        调用图库
        PhotoUtil.openPic(mActivity, TAKE_PHOTO);
    }

    public String filePath = Environment.getExternalStorageDirectory() + File.separator + "myH5camera" + File.separator + "aaa.jpg";

    private void startCamera(int type, String imgName) {
        File filePath = new File(imgName);
        try {
            if (!filePath.exists()) {
                filePath.getParentFile().mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri imageUri;
        /**
         * android7.0及以上
         */
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(mActivity.getBaseContext(), mActivity.getPackageName() + ".provider", filePath);
        } else {
            imageUri = Uri.fromFile(filePath);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        mActivity.startActivityForResult(intent, type);
    }
}
