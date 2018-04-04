package com.superli.livesdk;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.superli.livesdk.camera.CameraHelper;
import com.superli.livesdk.camera.CameraSurfaceView;

public class MainActivity extends AppCompatActivity {

    private static Camera mCamera;
    private CameraSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCamera = CameraHelper.getCameraInstance();
        FrameLayout container = findViewById(R.id.preview_container);
        surfaceView = new CameraSurfaceView(this, mCamera);
        container.addView(surfaceView);
    }
}
