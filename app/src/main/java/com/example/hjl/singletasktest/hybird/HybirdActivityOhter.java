package com.example.hjl.singletasktest.hybird;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hjl.singletasktest.R;
import com.example.hjl.singletasktest.base.BaseActivity;
import com.example.hjl.singletasktest.config.Config;
import com.example.hjl.singletasktest.hybird.myjs.JsInteration;
import com.example.hjl.singletasktest.hybird.webc.MyChromeWebClient;
import com.example.hjl.singletasktest.hybird.webc.MyWebViewClient;
import com.example.hjl.singletasktest.util.FileUrlUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HybirdActivityOhter extends BaseActivity {
    private static String TAG = "TAG_HybirdActivityOhter";

    @BindView(R.id.webview_other)
    WebView webviewOther;

    private JsInteration jsInteration;

    /**
     * 获取布局ID
     *
     * @return 布局id
     */
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_ather;
    }

    /**
     * 初始化布局以及View控件
     *
     * @param savedInstanceState
     */
    @Override
    protected void initView(Bundle savedInstanceState) {
        loadWeb();
    }

    @SuppressLint("JavascriptInterface")
    public void loadWeb() {
        jsInteration = new JsInteration(this, webviewOther);
        WebSettings settings = webviewOther.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        settings.setAllowFileAccess(true);    // 是否可访问本地文件，默认值 true
        // 是否允许通过file url加载的Javascript读取本地文件，默认值 false
        settings.setAllowFileAccessFromFileURLs(false);
        // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
        settings.setAllowUniversalAccessFromFileURLs(false);
        //开启JavaScript支持
        settings.setJavaScriptEnabled(true);
        // 支持缩放
        settings.setSupportZoom(true);

        //辅助WebView设置处理关于页面跳转，页面请求等操作
        webviewOther.setWebViewClient(new MyWebViewClient(false, this));
        //辅助WebView处理图片上传操作
        webviewOther.setWebChromeClient(new MyChromeWebClient(false, this));

        //支持弹窗，也就是支持html网页弹框
        //此方法可以在webview中打开链接而不会跳转到外部浏览器
//        webviewOther.setWebViewClient(new WebViewClient());
//        webviewOther.setWebChromeClient(new WebChromeClient() {
//            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                return super.onJsAlert(view, url, message, result);
//            }
//        });
        //支持html中javascript解析，不管是不是js和android交互，只要网页中含有js都要
        webviewOther.getSettings().setJavaScriptEnabled(true);
         /*
          打开js接口，参数1为本地类名；参数2为别名
         */
        webviewOther.addJavascriptInterface(jsInteration, "android");


        String url = "file:///android_asset/index.html";

        webviewOther.loadUrl(url);
    }

    @OnClick(R.id.webview_other)
    public void onViewClicked() {
    }

    //重载onKeyDown的函数，使其在页面内回退,而不是直接退出程序
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webviewOther.canGoBack()) {
            webviewOther.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private final static int PHOTO_REQUEST = 100;
    private final static int VIDEO_REQUEST = 120;
    public static final int TAKE_PHOTO = 1000;
    private final static String url = "your_url";
    private boolean videoFlag = false;
    private Uri imageUri;

    private void showNetPhoto(WebView webView,Uri uri){
        Log.e(TAG,"showNetPhoto");
        String path = FileUrlUtil.getRealFilePath(uri,this);
        /*将图片显示在网页上*/
        String method = "javascript:displayImg(\""+path+"\")";
        webView.loadUrl(method);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                Log.e(TAG,"onActivityResult__other_"+requestCode);
        if (requestCode == PHOTO_REQUEST) {
            if (null == mUploadMessage && null == mUploadCallbackAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        } else if (requestCode == VIDEO_REQUEST) {
            if (null == mUploadMessage && null == mUploadCallbackAboveL) return;

            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (mUploadCallbackAboveL != null) {
                if (resultCode == RESULT_OK) {
                    mUploadCallbackAboveL.onReceiveValue(new Uri[]{result});
                    mUploadCallbackAboveL = null;
                } else {
                    mUploadCallbackAboveL.onReceiveValue(new Uri[]{});
                    mUploadCallbackAboveL = null;
                }

            } else if (mUploadMessage != null) {
                if (resultCode == RESULT_OK) {
                    mUploadMessage.onReceiveValue(result);
                    mUploadMessage = null;
                } else {
                    mUploadMessage.onReceiveValue(Uri.EMPTY);
                    mUploadMessage = null;
                }

            }
        } else if (requestCode == TAKE_PHOTO) {
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            showNetPhoto(webviewOther,result);

        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != PHOTO_REQUEST || mUploadCallbackAboveL == null) {
            return;
        }
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                results = new Uri[]{imageUri};
            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }

                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        mUploadCallbackAboveL.onReceiveValue(results);
        mUploadCallbackAboveL = null;
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    private int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            Log.d("内存溢出", "");
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    @Override
    protected void onDestroy() {
        Log.e(Config.LOGTAG, "onDestroy");
        if (webviewOther != null) {

//             如果先调用destroy()方法，则会命中if (isDestroyed())
//            return;这一行代码，需要先onDetachedFromWindow()，再
//             destory()
            ViewParent parent = webviewOther.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webviewOther);
            }

            webviewOther.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            webviewOther.getSettings().setJavaScriptEnabled(false);
            webviewOther.clearHistory();
            webviewOther.clearView();
            webviewOther.removeAllViews();
            webviewOther.destroy();
        }
        super.onDestroy();
        System.exit(0);
    }
}
