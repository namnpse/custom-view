package com.namnp.customview.clock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.namnp.customview.R

class ClockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)

        val customClock = findViewById<CustomClock>(R.id.customClock)
    }
}