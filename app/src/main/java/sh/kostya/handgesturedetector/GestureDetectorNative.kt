package sh.kostya.handgesturedetector

/**
 * Created by Kostya S. on 21.11.2017.
 */
class GestureDetectorNative {

    init {
        System.loadLibrary("native-lib")
    }

    external fun init()

    external fun recognizeGesture(bytes: ByteArray?, cameraWidth: Int, cameraHeight: Int): Int
}