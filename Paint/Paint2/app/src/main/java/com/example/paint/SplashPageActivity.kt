package com.example.paint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View

class SplashPageActivity : AppCompatActivity() {
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_page)
        timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                // not doing anything
            }

            override fun onFinish() {
                start_interaction()
            }

        }.start()
    }

    private fun start_interaction() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun start_interaction(view: View) {
        timer.cancel()
        start_interaction()
    }
}