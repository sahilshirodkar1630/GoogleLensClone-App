package com.example.googlelensclone.imagelebeler

import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
import com.example.googlelensclone.BaseLensActivity

class ImageLabelActivity : BaseLensActivity() {

    override val imageAnalyzer = ImageLabelAnalyzer()


    override fun startScanner() {

        startImagelabeling()

    }
    private fun startImagelabeling(){
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }

}