package com.example.hjl.singletasktest.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.hjl.singletasktest.config.Config;

public class Start {
    public static void startMyActivity(Context start,Class end){
        Intent intent = new Intent();
        intent.setClass(start,end );
        start.startActivity(intent);
    }
}
