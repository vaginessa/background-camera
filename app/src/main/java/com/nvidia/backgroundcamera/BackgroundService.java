package com.nvidia.backgroundcamera;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by gbae on 5/11/17. Refer to http://bitsoul.tistory.com/147
 */

public class BackgroundService extends Service {

    private Handler mHandler = null;
    private CameraController mController = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
        Log.d("backgroundcamera", "BackgroundService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("backgroundcamera", "BackgroundService onStartCommand");

        //mHandler.post(new ToastRunnable("Test"));

        mController = new CameraController(getApplicationContext(), 0, null, mHandler);
        mController.openCamera(1024, 768);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("backgroundcamera", "BackgroundService onDestroy");
        mController.closeCamera();
    }

    public class ToastRunnable implements Runnable {
        String mText;

        public ToastRunnable(String text) {
            mText = text;
        }

        @Override
        public void run() {
            Toast.makeText(getApplicationContext(), mText, Toast.LENGTH_SHORT).show();
        }
    }
}
