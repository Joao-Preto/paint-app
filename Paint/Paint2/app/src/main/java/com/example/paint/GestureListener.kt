package com.example.paint

import android.view.GestureDetector
import android.view.MotionEvent
import androidx.fragment.app.FragmentManager
import kotlin.math.abs

class GestureListener: GestureDetector.SimpleOnGestureListener() {
    private lateinit var canvas: CanvasView
    private lateinit var fragManager: FragmentManager
    private val SWIPE_THRESHOLD: Int = 100
    private val SWIPE_VELOCITY_THRESHOLD: Int = 100

    fun setFragmentManager(fragMan: FragmentManager) {
        fragManager = fragMan
    }

    fun setCanvas(can: CanvasView) {
        canvas = can
    }

    override fun onLongPress(e: MotionEvent?) {
        canvas.changeBackground()
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        canvas.erase()
        return false
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        try {
            val diffY = e2!!.y - e1!!.y
            val diffX = e2.x - e1.x
            if (abs(diffX) > abs(diffY)) {
                if (abs(diffX) > SWIPE_THRESHOLD && abs(
                        velocityX
                    ) > SWIPE_VELOCITY_THRESHOLD
                ) {
                    if (diffX > 0) {
                        onSwipeRight()
                    }
                    else {
                        onSwipeLeft()
                    }
                }
            }
            else {
                if (abs(diffY) > SWIPE_THRESHOLD && abs(
                        velocityY
                    ) > SWIPE_VELOCITY_THRESHOLD
                ) {
                    if (diffY < 0) {
                        onSwipeUp()
                    }
                    else {
                        onSwipeDown()
                    }
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return false
    }

    open fun onSwipeRight() {}
    open fun onSwipeLeft() {}
    open fun onSwipeUp() {
        val paletteFragment = fragManager.findFragmentById(R.id.paletteContainer)
        if (paletteFragment != null) {
            fragManager.beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.slide_out).attach(paletteFragment).commit()
        }
    }
    open fun onSwipeDown() {
        val paletteFragment = fragManager.findFragmentById(R.id.paletteContainer)
        if (paletteFragment != null) {
            fragManager.beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.slide_out).detach(paletteFragment).commit()
        }

    }

}