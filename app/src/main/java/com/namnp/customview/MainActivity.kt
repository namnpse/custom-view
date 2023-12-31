package com.namnp.customview

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.namnp.customview.emotional_face.EmotionalFaceActivity
import com.namnp.customview.ui.theme.CustomViewTheme
import com.namnp.customview.user_info.CustomUserInfoActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomViewTheme {
                // A surface container using the 'background' color from the theme
//                Intent(this, CustomUserInfoActivity::class.java).also {
//                    startActivity(it)
//                }
                Intent(this, EmotionalFaceActivity::class.java).also {
                    startActivity(it)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomViewTheme {
        Greeting("Android")
    }
}