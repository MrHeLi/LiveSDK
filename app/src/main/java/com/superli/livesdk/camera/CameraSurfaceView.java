package com.superli.livesdk.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by Kiven on 2018/3/20.
 * Details:
 */

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private String TAG = "CameraSurfaceView";
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraSurfaceView(Context context) {
        super(context);
    }

    public CameraSurfaceView(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        mCamera.setDisplayOrientation(90);
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if (mHolder.getSurface() == null) {
            return;
        }
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
        }
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stopPreviewDisplay();
    }

    private void stopPreviewDisplay(){
        checkCamera();
        try {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
        } catch (Exception e){
            Log.i(TAG, "Error while STOP preview for camera", e);
        }
    }

    private void checkCamera(){
        if(mCamera == null) {
            throw new IllegalStateException("Camera must be set when start/stop preview, call <setCamera(Camera)> to set");
        }
    }

}
