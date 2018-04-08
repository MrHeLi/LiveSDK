package com.superli.livesdk;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.superli.livesdk.camera.CameraHelper;
import com.superli.livesdk.camera.CameraSurfaceView;

public class SurfaceViewActivity extends CommonActivity {
    private String TAG = this.getClass().getSimpleName();
    private static Camera mCamera;
    private CameraSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        mCamera = CameraHelper.getCameraInstance();
        FrameLayout container = findViewById(R.id.preview_container);
        surfaceView = new CameraSurfaceView(this, mCamera);
        container.addView(surfaceView, -1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }
}
