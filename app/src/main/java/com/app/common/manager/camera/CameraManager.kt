package com.app.common.manager.camera

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.Settings
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.app.common.base.BaseActivity
import java.util.concurrent.Executors

class CameraManager {
    companion object {
        @Volatile
        private var instance: CameraManager? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: CameraManager().also { instance = it }
        }

        private val REQUEST_CODE_PERMISSION = 123
        private val REQUIRED_PERMISSION = arrayOf(android.Manifest.permission.CAMERA)
        private var imageCapture: ImageCapture? = null
    }

    var completionerror: (String?) -> Unit = {}

    fun openCamera(context: BaseActivity, preview: PreviewView) {
//        outputDirectory = getOutputDirectory()
        if(allPermissionGranted(context)) {
            startCamera(context, preview)
        } else {
            ActivityCompat.requestPermissions(context, REQUIRED_PERMISSION, REQUEST_CODE_PERMISSION)
        }
    }

    fun startCamera(context: BaseActivity, preview: PreviewView) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also { mpreview ->
                    mpreview.setSurfaceProvider(preview.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(context, cameraSelector, preview,imageCapture)
            } catch (e: Exception) {
//                Log.d(1, "error: ",e)
            }
        }, ContextCompat.getMainExecutor(context))
    }

    fun takePhoto(context: BaseActivity, completion: (image: Bitmap)-> Unit) {
        if (imageCapture == null) {
            showAlert(context, "Please allow camera permission") {
                context.startActivity(Intent(Settings.ACTION_PRIVACY_SETTINGS))
            }
            return
        }
//        val photoFile = File(outputDirectory, SimpleDateFormat(FILE_NAME_FORMAT, Locale.getDefault()).format(System.currentTimeMillis()) + ".jpg")
//        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture!!.takePicture(
            Executors.newSingleThreadExecutor(),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(imageProxy: ImageProxy) {
                    val bitmap = imageProxy.toBitmap() // Convert ImageProxy to Bitmap
                    completion(bitmap)
                    imageProxy.close()
                }

                override fun onError(exception: ImageCaptureException) {
                    showAlert(context, exception.message.toString()) { completionerror("") }
                }
            }
        )
    }

    private fun showAlert(context: Context, error: String, completion: ()-> Unit) {
        AlertDialog.Builder(context)
            .setTitle("Camera Error")
            .setMessage(error)
            .setPositiveButton("OK") { _, _ ->
                completion()
            }
            .create()
            .show()
    }

    fun allPermissionGranted(context: Context) =
        REQUIRED_PERMISSION.all { ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED }
}
