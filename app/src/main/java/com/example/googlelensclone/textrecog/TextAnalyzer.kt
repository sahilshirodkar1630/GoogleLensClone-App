package com.example.googlelensclone.textrecog

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition

class TextAnalyzer : ImageAnalysis.Analyzer{

    val recognizer = TextRecognition.getClient()

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {

        Log.d("TEXT","image analyzed")

        imageProxy.image?.let {
            val inputImage = InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )

            recognizer.process(inputImage)
                .addOnSuccessListener { text ->

                    text.textBlocks.forEach { block ->
                        Log.d(
                            "TEXT", """
                            Lines = ${block.lines.joinToString ("\n" ){it.text}}
                            Language = ${block.recognizedLanguage}
                        """.trimIndent()

                        )

                    }
                }

                .addOnFailureListener { ex ->
                    Log.e("TEXT", "Detection failed", ex)
                }
//                .addOnCompleteListener {
//                    imageProxy.close();
//                }

        }
//        }?: imageProxy.close() // Close if image not found either

    }
}