package com.example.paint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SplashPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_page)
    }

    fun start_interaction(view: View) {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}