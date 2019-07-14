package com.example.qr_scanner.fragments


import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Bundle
import android.os.Vibrator
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qr_scanner.R
import com.example.qr_scanner.adapter.ScannerAdapter
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.confirmation_dialog.*
import kotlinx.android.synthetic.main.fragment_scanner.*
import java.io.IOException


@SuppressLint("RestrictedApi")
class ScannerFragment : BaseFragment() {

    private lateinit var barcodeDetector: BarcodeDetector
    private lateinit var cameraSource: CameraSource
    internal val RequestCameraPermissionID = 1001

    private val displayRectangle = Rect()
    private var width = 0
    private lateinit var metrics: DisplayMetrics
    private lateinit var dialog: Dialog

    private lateinit var adapter: ScannerAdapter
    private val list: ArrayList<String> = ArrayList()

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RequestCameraPermissionID -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(
                            baseActivity,
                            Manifest.permission.CAMERA
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        return
                    }
                    try {
                        cameraSource.start(cameraPreview.holder)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scanner, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addDisplayMatricsForDialog()
        insertAdapter()
        setQRcode()
        setCameraSurface()
        floatingActionButton.setOnClickListener { mDialog() }
        floatingActionButton.isEnabled = false
        floatingActionButton.isClickable = false
        floatingActionButton.alpha = 0.3f


    }

    private fun insertAdapter() {
        mRecyclerView.layoutManager = LinearLayoutManager(baseActivity)
        adapter = ScannerAdapter(list)
        mRecyclerView.adapter = adapter
        //  mRecyclerView.removeViewAt(2);

    }

    private fun clearRecyclerview() {
        list.clear()
        adapter.notifyDataSetChanged()
        mRecyclerView.smoothScrollToPosition(0)
        mTextview.visibility = View.VISIBLE
        floatingActionButton.isEnabled = false
        floatingActionButton.isClickable = false
        floatingActionButton.alpha = 0.3f
        dialog.dismiss()
    }

    private fun mDialog() {
        dialog = Dialog(baseActivity)
        val layout = LayoutInflater.from(baseActivity).inflate(R.layout.confirmation_dialog, null, false)
        dialog.setContentView(layout)
        dialog.mYes.setOnClickListener { clearRecyclerview() }
        dialog.mDiscard.setOnClickListener { dialog.dismiss() }
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

    }

    private fun addDisplayMatricsForDialog() {
        metrics = DisplayMetrics()
        baseActivity.windowManager.defaultDisplay.getMetrics(metrics)
        baseActivity.window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        width = (displayRectangle.width() * 0.9f).toInt()

    }

    private fun setQRcode() {
        barcodeDetector = BarcodeDetector.Builder(baseActivity).setBarcodeFormats(Barcode.QR_CODE).build()
        cameraSource = CameraSource.Builder(baseActivity, barcodeDetector)
            .setRequestedPreviewSize(640, 480)
            .setAutoFocusEnabled(true)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .build()
    }


    private fun setCameraSurface() {

        cameraPreview.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
                if (ActivityCompat.checkSelfPermission(
                        baseActivity, Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        baseActivity,
                        arrayOf(Manifest.permission.CAMERA),
                        RequestCameraPermissionID
                    )
                    return
                }
                try {
                    cameraSource.start(cameraPreview.holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {

            }

            override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
                cameraSource.stop()

            }
        })

        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {

            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {

                val qrcodes = detections.detectedItems
                if (qrcodes.size() != 0) {
                    val code = qrcodes.valueAt(0).displayValue.toString()
                    val vibrator = baseActivity.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    mTextview.visibility = View.GONE
                    floatingActionButton.isEnabled = true
                    floatingActionButton.isClickable = true
                    floatingActionButton.alpha = 0.9f
                    list.add(code)
                    adapter.notifyDataSetChanged()
                    mRecyclerView.smoothScrollToPosition(0)
                    vibrator.vibrate(1000)

                    try {
                        Thread.sleep(4000)
                    } catch (e: InterruptedException) {
                        return
                    }
                }

            }
        })
    }

}

