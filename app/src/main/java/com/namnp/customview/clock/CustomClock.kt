package com.namnp.customview.clock

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.namnp.customview.R
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
class CustomClock(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var paint: Paint? = null
    private var formatDate: SimpleDateFormat? = null
    private var formatTime: SimpleDateFormat? = null
    private var colorStroke = 0
    private var widthStroke = 0f
    private var xTitle = 0f
    private var yTitle = 0f
    private var textSizeTitle = 0f

//    CustomLock(Context context) called when add view on runtime.
//    CustomLock(Context context, AttributeSet attrs) called when declare view in XML layout
//    CustomLock(Context context, AttributeSet attrs, int defStyleAttr) via XML but add attributes style
//    CustomLock(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) same as above, but have extra param to pass style via resource.

    init {
        val typedArray = context
            .obtainStyledAttributes(attrs, R.styleable.CustomClock,0,0)
        colorStroke = typedArray.getColor(
            R.styleable.CustomClock_color_stroke,
            Color.GREEN
        )
        widthStroke = typedArray.getDimension(
                R.styleable.CustomClock_width_stroke_circle,
                20f
            )
        xTitle = typedArray.getDimension(R.styleable.CustomClock_x_title, 200f)
        yTitle = typedArray.getDimension(R.styleable.CustomClock_y_title, 200f)
        textSizeTitle = typedArray.getDimension(R.styleable.CustomClock_text_size_title, 200f)
        typedArray.recycle()
        formatDate = SimpleDateFormat("dd/MM/yyyy")
        formatTime = SimpleDateFormat("hh:mm")
        paint = Paint()
        paint?.apply {
            isAntiAlias = true
            //set style for paint
            style = Paint.Style.STROKE
            //set size text
            textSize = textSizeTitle
        }
    }

    // when parent View call addView(view)
    // already know other views in the same layout
    // -> can get ID of those view and save as global variables
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    // calculate size of custom view in layout
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    // specify size and position of all children views inside the custom view
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    // used to draw layout using: Canvas(shape) and Paint(color)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val rectOval = RectF(
            (widthStroke / 2), (widthStroke / 2),
            (width - widthStroke / 2), (height - widthStroke / 2)
        )
        //set width for stroke
        paint?.apply {
            strokeWidth = widthStroke
            //set color stroke
            color = colorStroke
            // set text size
            textSize = textSizeTitle
            canvas.drawOval(rectOval, this)
            color = Color.parseColor("#8BC34A")
            canvas.drawText("Nam", xTitle, yTitle, this)
            drawDate(canvas)
        }

    }

    private fun drawDate(canvas: Canvas) {
        val date = Date()
        formatTime?.let { dateFormat ->
            val strDate = dateFormat.format(date)
            paint?.let { painter ->
                painter.textSize = 100f
                canvas.drawText(strDate, 250f, 450f, painter)
                val strTime = dateFormat.format(date)
                canvas.drawText(strTime, 250f, 600f, painter)
            }
        }

    }
}