package com.superli.livesdk.camera;

import android.annotation.TargetApi;
import android.hardware.Camera;

/**
 * Created by Kiven on 2018/3/20.
 * Details:
 */

public class CameraHelper {
    private static Camera mCamera;
    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        Camera c = null;
        try {
            int CammeraIndex = FindBackCamera();
            if (CammeraIndex == -1) {
                CammeraIndex = FindFrontCamera();
            }
            c = Camera.open(CammeraIndex); // attempt to get a Camera instance
            if (c == null) {
                c = Camera.open();
            }
            Camera.Parameters params = c.getParameters();
            // set the focus mode
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            // set Camera parameters
            c.setParameters(params);
        } catch (Exception e) {
            e.printStackTrace();
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    public static int FindFrontCamera() {
        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras(); // get cameras number

        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo); // get camerainfo
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                return camIdx;
            }
        }
        return -1;
    }

    @TargetApi(9)
    public static int FindBackCamera() {
        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras(); // get cameras number

        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo); // get camerainfo
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                return camIdx;
            }
        }
        return -1;
    }
}
