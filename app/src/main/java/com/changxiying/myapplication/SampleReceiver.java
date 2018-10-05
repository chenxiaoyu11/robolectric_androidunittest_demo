package com.changxiying.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SampleReceiver extends BroadcastReceiver{
//    标准广播
    private static final String TAG = "SampleReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("Msg");
        Log.e(TAG,msg);
    }
}
