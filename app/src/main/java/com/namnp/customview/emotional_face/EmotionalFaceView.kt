package com.namnp.customview.emotional_face

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.namnp.customview.R
import kotlin.math.min

//class EmotionalFaceView: View {
//
//    constructor(context: Context)
//    To create a new View instance from Kotlin code, it needs the Activity context.
//    constructor(context: Context, attrs: AttributeSet)
//    To create a new View instance from XML.
//    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
//    To create a new view instance from XML with a style from theme attribute.
//    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
//    To create a new view instance from XML with a style from theme attribute and/or style resource.
//
//    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
//}

// OR
// can use JvmOverloads constructor()
class EmotionalFaceView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    // 1
    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }

    // 2
    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    // Face border width in pixels
    private var borderWidth = DEFAULT_BORDER_WIDTH

    // Paint object for coloring and styling
    private val paint = Paint()
    private val mouthPath = Path()
    // View size in pixels
    private var size = 0

    // 3
    var happinessState = HAPPY
        set(state) {
            field = state
            // 4
            // invalidate() method makes Android redraw the view by calling onDraw()
            invalidate()
        }

    // 5
    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        // 6
        // Get a typed array of attributes
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.EmotionalFaceView,
            0, 0)

        // 7
        // Extract custom attributes into member variables
        happinessState = typedArray.getInt(R.styleable.EmotionalFaceView_state, HAPPY.toInt()).toLong()
        faceColor = typedArray.getColor(R.styleable.EmotionalFaceView_faceColor, DEFAULT_FACE_COLOR)
        eyesColor = typedArray.getColor(R.styleable.EmotionalFaceView_eyesColor, DEFAULT_EYES_COLOR)
        mouthColor = typedArray.getColor(R.styleable.EmotionalFaceView_mouthColor, DEFAULT_MOUTH_COLOR)
        borderColor = typedArray.getColor(R.styleable.EmotionalFaceView_borderColor,
            DEFAULT_BORDER_COLOR)
        borderWidth = typedArray.getDimension(R.styleable.EmotionalFaceView_borderWidth,
            DEFAULT_BORDER_WIDTH)

        // 8
        // TypedArray objects are shared and must be recycled.
        // Recycle the typedArray to make the data associated with it ready for garbage collection
        typedArray.recycle()
    }


    override fun onDraw(canvas: Canvas?) {
        // call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)

        canvas?.let {
            drawFaceBackground(it)
            drawEyes(it)
            drawMouth(it)
        }
    }

    private fun drawFaceBackground(canvas: Canvas) {

        // 1
        paint.color = faceColor
        paint.style = Paint.Style.FILL

        // 2
        val radius = size / 2f

        // 3
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        // 4
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        // 5
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)

    }

    private fun drawEyes(canvas: Canvas) {

        // 1
        paint.color = eyesColor
        paint.style = Paint.Style.FILL

        // 2
        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)
        canvas.drawOval(leftEyeRect, paint)

        // 3
        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)

        canvas.drawOval(rightEyeRect, paint)

    }

    private fun drawMouth(canvas: Canvas) {
        // 0
        // reset the path and remove any old path before drawing a new path,
        // to avoid drawing the mouth more than one time while Android calls the onDraw() method again and again
        mouthPath.reset()


        // 1
        mouthPath.moveTo(size * 0.22f, size * 0.7f)

        if (happinessState == HAPPY) {
            // 2
            mouthPath.quadTo(size * 0.5f, size * 0.80f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.90f, size * 0.22f, size * 0.7f)
        } else {
            // 3
            mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f)
        }

        // 4
        paint.color = mouthColor
        paint.style = Paint.Style.FILL

        // 5
        canvas.drawPath(mouthPath, paint)
    }


    // custom view has fixed size -> make it to be responsive, fill its parent and circle (width = height)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 1
        size = min(measuredWidth, measuredHeight)

        // 2
        setMeasuredDimension(size, size)

        println("widthMeasureSpec: $widthMeasureSpec heightMeasureSpec: $heightMeasureSpec measuredWidth: $measuredWidth measuredHeight: $measuredHeight")

    }

}