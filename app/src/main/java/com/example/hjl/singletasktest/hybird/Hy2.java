package com.example.hjl.singletasktest.hybird;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hjl.singletasktest.R;
import com.example.hjl.singletasktest.base.BaseActivity;
import com.example.hjl.singletasktest.hybird.fyutil.FileUtils;
import com.example.hjl.singletasktest.util.FileUrlUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Hy2 extends BaseActivity {
    @BindView(R.id.webview_other)
    WebView webview;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private final int RESULT_CODE_PICK_FROM_ALBUM_BELLOW_LOLLILOP = 1;
    private final int RESULT_CODE_PICK_FROM_ALBUM_ABOVE_LOLLILOP = 2;
    private String url = "";//这里添加含有图片上传功能的H5页面访问地址即可。
    String compressPath = "";

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
        initData();
    }

    public void initData() {
        initWebView();
        url = "file:///android_asset/index.html";
        webview.loadUrl(url);
    }
    @SuppressLint("WrongConstant")
    @SuppressWarnings("deprecation")
    private void initWebView(){
        webview.setScrollBarStyle(View.GONE);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setDomStorageEnabled(false);
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setAllowContentAccess(true);
        webview.requestFocus();
        webview.setWebViewClient(new WebClient());
        webview.setWebChromeClient(new MyWebChromeClient());

    }
    /**选择后，回传值*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mUploadMessage == null && mUploadCallbackAboveL == null) {
            return;
        }
        Uri uri = null;

        switch (requestCode) {
            case RESULT_CODE_PICK_FROM_ALBUM_BELLOW_LOLLILOP:
                uri = afterChosePic(data);
                if (mUploadMessage != null) {
                    mUploadMessage.onReceiveValue(uri);
                    mUploadMessage = null;
                }

                break;
            case RESULT_CODE_PICK_FROM_ALBUM_ABOVE_LOLLILOP:
                try {
                    uri = afterChosePic(data);
                    if (uri == null) {
                        mUploadCallbackAboveL.onReceiveValue(new Uri[] { });
                        mUploadCallbackAboveL = null;
                        break;
                    }
                    if (mUploadCallbackAboveL != null && uri != null) {
                        mUploadCallbackAboveL.onReceiveValue(new Uri[] { uri });
                        mUploadCallbackAboveL = null;

                    }
                } catch (Exception e) {
                    mUploadCallbackAboveL = null;
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 选择照片后结束
     * @param data
     */
    private Uri afterChosePic(Intent data) {
        if (data == null) {
            return null;
        }
        String path = FileUrlUtil.getRealFilePath(data.getData(),this);
        String[] names = path.split("\\.");
        String endName = null;
        if (names != null) {
            endName = names[names.length - 1];
        }
        if (endName != null) {
            compressPath = compressPath.split("\\.")[0] + "." + endName;
        }
        File newFile;
        try {
            newFile = FileUtils.compressFile(path, compressPath);
        } catch (Exception e) {
            newFile = null;
        }
        return Uri.fromFile(newFile);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(webview.canGoBack()){
            webview.goBack();
        }else{
            finish();
        }
    }
    private class WebClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**打开图库,同时处理图片（项目业务需要统一命名）*/
    private void selectImage(int resultCode) {
        compressPath = Environment.getExternalStorageDirectory().getPath() + "/QWB/temp";
        File file = new File(compressPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        compressPath = compressPath + File.separator + "compress.png";
        File image = new File(compressPath);
        if (image.exists()) {
            image.delete();
        }
        Intent intent = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, resultCode);

    }
    /**
     * 内部类
     */
    class MyWebChromeClient extends WebChromeClient {
        //openFileChooser（隐藏方法）仅适用android5.0以下的环境，android5.0及以上使用onShowFileChooser

        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                    String acceptType) {
            if (mUploadMessage != null)
                return;
            mUploadMessage = uploadMsg;
            selectImage(RESULT_CODE_PICK_FROM_ALBUM_BELLOW_LOLLILOP);
        }

        // For Android < 3.0
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, "");
        }

        // For Android > 4.1.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                    String acceptType, String capture) {
            openFileChooser(uploadMsg, acceptType);
        }

        // For Android 5.0+
        public boolean onShowFileChooser(WebView webView,
                                         ValueCallback<Uri[]> filePathCallback,
                                         FileChooserParams fileChooserParams) {
            mUploadCallbackAboveL = filePathCallback;
            selectImage(RESULT_CODE_PICK_FROM_ALBUM_ABOVE_LOLLILOP);
            return true;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
    }
}
