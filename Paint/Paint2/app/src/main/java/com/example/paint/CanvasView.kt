package com.example.paint

import android.content.Context
import android.content.res.Configuration
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CanvasView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), View.OnTouchListener {
    private var paint = Paint()
    private var path = Path()
    private val strokes: MutableList<Path> = mutableListOf()
    private val colors: MutableMap<String, Color> = hashMapOf()
    private var defaultBackgroundColor = Color.TRANSPARENT
    private lateinit var gestureDetector: GestureDetector

    init {
        setOnTouchListener(this)
        setBackgroundColor(defaultBackgroundColor)
        initPaint()
    }

    override fun onDraw(canvas: Canvas?) {
        strokes.forEach { stroke ->
            val col = colors[stroke.toString()]
            val tempPaint = Paint()
            tempPaint.isAntiAlias = true
            tempPaint.strokeWidth = 20f
            tempPaint.color = Color.WHITE
            tempPaint.style = Paint.Style.STROKE
            tempPaint.strokeJoin = Paint.Join.ROUND
            canvas?.drawPath(stroke, tempPaint)
        }



    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
                strokes.add(path)
                colors[path.toString()] = Color.valueOf(paint.color)
                path = Path()
                path.moveTo(x, y)
            }
            MotionEvent.ACTION_UP   -> performClick()    // when you lift your finger
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
        val nightFlags = context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        if (nightFlags == Configuration.UI_MODE_NIGHT_YES){
            paint.color = Color.WHITE
        }else {
            paint.color = Color.BLACK
        }
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

    fun saveDrawing(name: String){
        val database = Firebase.database("https://paint-app-55c39-default-rtdb.europe-west1.firebasedatabase.app/").reference
        val drawing = Drawing(name, strokes/*, colors*/)
        database.child("Drawing").child(name).setValue(drawing)
    }


}