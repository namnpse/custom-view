package com.namnp.customview.user_info

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.namnp.customview.R

class UserInfoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
): LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var logoView: ImageView
    private var usernameView: TextView
    private var levelView: TextView
    var username: String = ""
        set(value) {
            field = value
            usernameView.text = value
        }

    var level: String = ""
        set(value) {
            field = value
            levelView.text = value
        }

    var logo: Drawable? = null
        set(value) {
            field = value
            value?.let {
                logoView.setImageDrawable(it)
            }
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.user_custom_view, this, true)
        orientation = VERTICAL
        logoView = findViewById(R.id.logo)
        levelView = findViewById(R.id.level)
        usernameView = findViewById(R.id.username)

        // fill views with custom attributes
        attrs?.let {
            context.theme.obtainStyledAttributes(it, R.styleable.UserInfoView, 0,0).apply {
                try {
                    username = getString(R.styleable.UserInfoView_username) ?: context.getString(R.string.default_username)
                    level = getString(R.styleable.UserInfoView_level) ?: context.getString(R.string.default_level)
                    val logoRes = getResourceId(R.styleable.UserInfoView_logoRef, -1)
                    println("username $username level $level logoRes $logoRes")
                    if(logoRes != -1) {
                        logo = AppCompatResources.getDrawable(context, logoRes)
                    }
                }finally {
                   recycle()
                }
            }

        }
    }
}