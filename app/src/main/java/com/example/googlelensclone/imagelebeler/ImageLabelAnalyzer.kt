package com.example.googlelensclone.imagelebeler

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class ImageLabelAnalyzer : ImageAnalysis.Analyzer {

    private val labeler = ImageLabeling.getClient(ImageLabelerOptions.Builder()
        .setConfidenceThreshold(0.7F)
        .build()
    )

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {


        Log.d("LABEL","image amalyzed")

        imageProxy.image?.let {
            val inputImage = InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )

            labeler.process(inputImage)
                .addOnSuccessListener { labels ->
                    Log.d("LABEL", "labels = ${labels.size}")



                    labels.forEach { label ->
                        Log.d(
                            "LABEL", """
                               ${label.text}
                               ${label.confidence}
                        """.trimIndent()

                        )
                    }
                }

                .addOnFailureListener { ex ->
                    Log.e("LABEL", "Detection failed", ex)
                }
                .addOnCompleteListener {
                    imageProxy.close();
                }

//        }
        }?: imageProxy.close() // Close if image not found either


    }
}