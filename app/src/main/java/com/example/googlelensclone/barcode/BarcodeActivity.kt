package com.example.googlelensclone.barcode



import androidx.core.content.ContextCompat

import com.example.googlelensclone.BaseLensActivity
import kotlinx.android.synthetic.main.activity_lens.*


class BarcodeActivity : BaseLensActivity() {

 override  val imageAnalyzer = BarcodeAnalyzer()

    override fun startScanner() {
        scanBarcode()

    }


    private fun scanBarcode() {
        imageAnalysis.setAnalyzer(
             ContextCompat.getMainExecutor(this),
                imageAnalyzer
        )
    }

}