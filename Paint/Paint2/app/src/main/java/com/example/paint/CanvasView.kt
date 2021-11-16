package com.example.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class CanvasView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), View.OnTouchListener {
    private var paint = Paint()
    private var path = Path()
    private val strokes: MutableList<Path> = mutableListOf()
    private val colors: MutableMap<Path, Paint> = hashMapOf()
    private var defaultBackgroundColor = Color.TRANSPARENT
    private lateinit var gestureDetector: GestureDetector

    init {
        setOnTouchListener(this)
        setBackgroundColor(defaultBackgroundColor)
        initPaint()
    }

    override fun onDraw(canvas: Canvas?) {
        strokes.forEach { stroke ->
            colors.get(stroke)?.let { canvas?.drawPath(stroke, it) }
        }

    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x = event!!.x
        var y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {                 // updates the path initial point
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y) // makes a line to the point each time this event is fired


            }
            MotionEvent.ACTION_UP   -> {
                strokes.add(path)
                colors[path] = paint
                path = Path()
                path.moveTo(x, y)
                performClick()
            }    // when you lift your finger
            else -> return false
        }
        invalidate()
        return true
    }

    fun changeBackground() {
        val rand = kotlin.random.Random(0)
        val backgroundColor = Color.rgb(rand.nextInt(), rand.nextInt(), rand.nextInt())
        setBackgroundColor(backgroundColor)
    }

    fun erase() {
        strokes.forEach { stroke ->
            stroke.reset()
        }
        invalidate()
    }

    fun undo () {
        strokes.last().reset()
        invalidate()
    }

    private fun initPaint() {
        paint.isAntiAlias = true
        paint.strokeWidth = 20f
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
    }

    fun setGestureDetector(gd: GestureDetector) {
        gestureDetector = gd
    }

    fun changePaintColor(newColor: Int) {
        paint = Paint()
        initPaint()
        paint.color = newColor
    }

    fun changeBackgroundColor(newColor: Int) {
        setBackgroundColor(newColor)
    }


}