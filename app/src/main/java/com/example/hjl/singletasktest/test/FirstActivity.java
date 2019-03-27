package com.example.hjl.singletasktest.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hjl.singletasktest.R;
import com.example.hjl.singletasktest.hybird.Hy2;
import com.example.hjl.singletasktest.hybird.HybirdActivity;
import com.example.hjl.singletasktest.hybird.HybirdActivityOhter;
import com.example.hjl.singletasktest.util.Start;
import com.example.hjl.singletasktest.config.Config;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends AppCompatActivity {
    @BindView(R.id.one)
    Button one;
    @BindView(R.id.one1)
    Button one1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        Log.e(Config.LOGTAG,"FirstActivity..onCreate.");
    }

    @OnClick({R.id.one, R.id.one1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                Start.startMyActivity(this,TwoActivity.class);
                break;
            case R.id.one1:
                Start.startMyActivity(this,HybirdActivityOhter.class);
                break;
            default:
        }
    }
}
