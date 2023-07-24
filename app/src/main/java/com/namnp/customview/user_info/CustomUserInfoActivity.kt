package com.namnp.customview.user_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import com.namnp.customview.R

class CustomUserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_user_info)

        val meInfo = findViewById<UserInfoView>(R.id.meInfo)
//        meInfo.username = "Username 10"
        meInfo.level = "100"
        meInfo.logo = AppCompatResources.getDrawable(this, R.drawable.img_mario)
    }
}