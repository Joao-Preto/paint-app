package com.example.paint

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SettingsActivity : AppCompatActivity() {

    var color = Color.WHITE;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun changeColourWhite(view: View) {
        this.color = Color.WHITE
    }
    fun changeColourBlack(view: View) {
        this.color = Color.BLACK
    }
    fun changeColourRed(view: View) {
        this.color = Color.RED
    }
    fun changeColourOrange(view: View) {
        this.color = Color.argb(255,255,165,0);
    }
    fun changeColourYellow(view: View) {
        this.color = Color.YELLOW
    }
    fun changeColourGreen(view: View) {
        this.color = Color.GREEN
    }
    fun changeColourBlue(view: View) {
        this.color = Color.BLUE
    }
    fun changeColourPurple(view: View) {
        this.color = Color.argb(255,128,0,128);
    }
    fun changeColor(view: View) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(EXTRA_COLOR, color)}
        startActivity(intent)
    }
}