package com.halodoc.medical.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.halodoc.medical.ChatEspecifico;
import com.halodoc.medical.modal.Usuario;

/**
 * Created by Supriyanto on 8/18/2020.
 * Copyright (c) 2020 . All rights reserved.
 */
public class ServiceChat extends Service {

    Usuario usuario;
    private static final String TAG = "BroadcastService";
    public static final String BROADCAST_ACTION = "com.example.chat";
    private final Handler handler = new Handler();
    Intent intent;
    int counter = 0;
    ChatEspecifico ch;
    RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        intent = new Intent(BROADCAST_ACTION);
//        usuario = (Usuario) intent.getExtras().getSerializable("usuario");
        queue = Volley.newRequestQueue(this);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 1000);
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            ch = new ChatEspecifico();
            ch.chatRefresh();
            handler.postDelayed(this, 1000); // 5 seconds
        }
    };

    private void DisplayLoggingInfo() {
        Log.d(TAG, "entered DisplayLoggingInfo");
        intent.putExtra("usuario", usuario);
        intent.putExtra("counter", String.valueOf(++counter));
        sendBroadcast(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
