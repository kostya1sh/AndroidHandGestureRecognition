package sh.kostya.handgesturedetector

import android.hardware.Camera
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by Kostya S. on 21.11.2017.
 */
class MainActivity : AppCompatActivity(), Camera.PreviewCallback {

    private val cameraHeight: Int = 1280
    private val cameraWidth: Int = 720

    private val mGestureDetector by lazy { GestureDetectorNative() }
    private lateinit var mPreview: CameraPreview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        mPreview = CameraPreview(this, this)
        vCameraPreview.addView(mPreview)

        mGestureDetector.init()
    }

    override fun onPreviewFrame(p0: ByteArray?, p1: Camera?) {
        val numberOfFingers = mGestureDetector.recognizeGesture(p0, cameraWidth, cameraHeight)
        vFingersNumber.text = numberOfFingers.toString()
    }
}