package com.namnp.customview.emotional_face

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.namnp.customview.R

class EmotionalFaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emotional_face)

        // 1
        val happyButton = findViewById<EmotionalFaceView>(R.id.happyButton)
        val sadButton = findViewById<EmotionalFaceView>(R.id.sadButton)
        val emotionalFaceView = findViewById<EmotionalFaceView>(R.id.emotionalFaceView)
        happyButton.setOnClickListener {
            emotionalFaceView.happinessState = EmotionalFaceView.HAPPY
        }
        // 2
        sadButton.setOnClickListener {
            emotionalFaceView.happinessState = EmotionalFaceView.SAD
        }
    }
}