package com.example.hjl.singletasktest.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hjl.singletasktest.R;
import com.example.hjl.singletasktest.config.Config;
import com.example.hjl.singletasktest.hybird.Hy2;
import com.example.hjl.singletasktest.hybird.HybirdActivity;
import com.example.hjl.singletasktest.hybird.HybirdActivityOhter;
import com.example.hjl.singletasktest.util.Start;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TwoActivity extends AppCompatActivity {
    @BindView(R.id.two)
    Button two;
    @BindView(R.id.two1)
    Button two1;
    @BindView(R.id.two2)
    Button two2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        Log.e(Config.LOGTAG,"TwoActivity..onCreate.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.two, R.id.two1, R.id.two2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.two:
                Start.startMyActivity(this,HybirdActivity.class);
                break;
            case R.id.two1:
                Start.startMyActivity(this,HybirdActivityOhter.class);
                break;
            case R.id.two2:
                Start.startMyActivity(this,Hy2.class);
                break;
            default:
        }
    }
}
