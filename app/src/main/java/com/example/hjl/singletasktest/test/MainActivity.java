package com.example.hjl.singletasktest.test;

import android.content.Intent;
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
import com.example.hjl.singletasktest.pic.PickupPhotoActivity;
import com.example.hjl.singletasktest.util.Start;
import com.example.hjl.singletasktest.config.Config;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tv)
    Button mainTv;
    @BindView(R.id.main_tv_jc)
    Button mainJC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.e(Config.LOGTAG,"MAIN..onCreate.");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(Config.LOGTAG,"MAIN..onNewIntent.");
    }

    @OnClick({R.id.main_tv, R.id.main_tv_jc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_tv:
                Start.startMyActivity(this,FirstActivity.class);
                break;
            case R.id.main_tv_jc:
                Start.startMyActivity(this,PickupPhotoActivity.class);
                break;
            default:
        }
    }




}
