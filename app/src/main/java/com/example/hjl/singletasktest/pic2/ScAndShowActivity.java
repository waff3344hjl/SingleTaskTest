package com.example.hjl.singletasktest.pic2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.hjl.singletasktest.R;
import com.example.hjl.singletasktest.base.BaseActivity;
import com.example.hjl.singletasktest.hybird.myjs.JsInteration;
import com.example.hjl.singletasktest.hybird.webc.MyChromeWebClient;
import com.example.hjl.singletasktest.hybird.webc.MyWebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScAndShowActivity extends BaseActivity {
    @BindView(R.id.wv_webview_hy2)
    WebView webviewOther;
    private JsInteration jsInteration;

    /**
     * 获取布局ID
     *
     * @return 布局id
     */
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.layout_hy2;
    }

    /**
     * 初始化布局以及View控件
     *
     * @param savedInstanceState
     */
    @Override
    protected void initView(Bundle savedInstanceState) {

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

    @OnClick(R.id.wv_webview_hy2)
    public void onViewClicked() {
    }
}
