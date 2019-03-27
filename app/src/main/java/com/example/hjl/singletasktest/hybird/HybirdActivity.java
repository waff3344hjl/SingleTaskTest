package com.example.hjl.singletasktest.hybird;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.hjl.singletasktest.R;
import com.example.hjl.singletasktest.base.BaseActivity;
import com.example.hjl.singletasktest.config.Config;
import com.example.hjl.singletasktest.hybird.myjs.JsInteration;
import com.example.hjl.singletasktest.test.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HybirdActivity extends BaseActivity {

    @BindView(R.id.wv_webview)
    WebView webView;
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt3)
    Button bt3;
    @BindView(R.id.bt4)
    Button bt4;

    /**
     * 获取布局ID
     *
     * @return 布局id
     */
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_mainh5;
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
        //String url = "https://www.baidu.com/";
        String url = "file:///android_asset/index2.html";
        //此方法可以在webview中打开链接而不会跳转到外部浏览器
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        //支持弹窗，也就是支持html网页弹框
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        //支持html中javascript解析，不管是不是js和android交互，只要网页中含有js都要
        webView.getSettings().setJavaScriptEnabled(true);
         /*
          打开js接口，参数1为本地类名；参数2为别名
         */
        webView.addJavascriptInterface(new JsInteration(this,webView), "android");
    }

    //重载onKeyDown的函数，使其在页面内回退,而不是直接退出程序
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1://无参无返回值的方法
                //调用H5无参无返回值方法
                webView.loadUrl("javascript:show()");
                break;
            case R.id.bt2://有返回值的方法
                //调用H5有返回值方法
                webView.evaluateJavascript("sum(1,2)",new ValueCallback() {
                    @Override
                    public void onReceiveValue(Object value) {
                        Toast.makeText(HybirdActivity.this,"js返回结果为="+value,Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.bt3://有参无返回值的方法
                //调用H5有参方法
                webView.loadUrl("javascript:alertMsg('哈哈')");
                String content = "9880";
                webView.loadUrl("javascript:alertMsg(\""+content+"\")");
                break;
            case R.id.bt4://绘制
                Log.e(Config.LOGTAG,"");
                webView.loadUrl("javascript:show()");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Log.e(Config.LOGTAG,"onDestroy");
        if( webView!=null) {

//             如果先调用destroy()方法，则会命中if (isDestroyed())
//            return;这一行代码，需要先onDetachedFromWindow()，再
//             destory()
            ViewParent parent = webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webView);
            }

            webView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            webView.getSettings().setJavaScriptEnabled(false);
            webView.clearHistory();
            webView.clearView();
            webView.removeAllViews();
            webView.destroy();
        }
        super.onDestroy();
        System.exit(0);
    }
}
