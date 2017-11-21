package sh.kostya.handgesturedetector

import android.content.Context
import android.hardware.Camera
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.io.IOException

/**
 * Created by Kostya S. on 21.11.2017.
 */
class CameraPreview(val mContext: Context, val mPreviewCallback: Camera.PreviewCallback ): SurfaceView(mContext), SurfaceHolder.Callback  {

    private lateinit var mCamera: Camera

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
        // Now that the size is known, set up the camera parameters and begin
        // the preview.
        val parameters = mCamera.parameters
        val localSizes = mCamera.parameters.supportedPreviewSizes
        parameters.setPreviewSize(localSizes[4].width, localSizes[4].height)
        parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
        mCamera.parameters = parameters
        mCamera.setDisplayOrientation(90)

        // We also assign the preview display to this surface...
        try {
            mCamera.setPreviewDisplay(holder)
        } catch (e: IOException) {
            e.printStackTrace()
        }


        // Important: Call startPreview() to start updating the preview surface.
        // Preview must be started before you can take a picture.
        mCamera.startPreview()
        mCamera.setPreviewCallback(mPreviewCallback)
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        mCamera.stopPreview()
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
        mCamera = Camera.open(1)
    }
}