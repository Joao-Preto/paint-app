package com.example.paint

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import kotlin.math.sqrt

const val EXTRA_COLOR = "com.example.paint.color"
const val Extra_PAINT = "com.example.paint.paint.color"
const val Extra_SHAKE = "com.example.paint.shake"
class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private var currAcc: Float = SensorManager.GRAVITY_EARTH
    private var oldAcc: Float = SensorManager.GRAVITY_EARTH
    private var acceleration: Float = 0.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var backgroundColor = intent.getIntExtra(EXTRA_COLOR, Color.WHITE)
        val view = this.window.decorView
        //val paintZoneFrag = supportFragmentManager.findFragmentById(R.id.canvasContainer)
        val paletteFrag   = supportFragmentManager.findFragmentById(R.id.paletteContainer)
        val settingsFragment = supportFragmentManager.findFragmentById(R.id.settingsContainer)
        if (settingsFragment != null) {
            supportFragmentManager.beginTransaction().detach(settingsFragment).commit()
        }
        view.setBackgroundColor(backgroundColor)
        if (paletteFrag != null) {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.slide_out).detach(paletteFrag).commit()
        }
        view.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeUp() {
                if (paletteFrag != null) {
                    supportFragmentManager.beginTransaction().attach(paletteFrag).commit()
                }
            }

            override fun onSwipeDown() {
                if (paletteFrag != null) {
                    supportFragmentManager.beginTransaction().detach(paletteFrag).commit()
                }
            }
        })
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        supportFragmentManager.setFragmentResultListener(
            EXTRA_COLOR,
            this,
            { _, result ->
                backgroundColor = result.getInt(EXTRA_COLOR)
                view.setBackgroundColor(backgroundColor)
            }
        )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.settings_item -> {
                settings()
                return true
            }
            R.id.about_item -> {
                about()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    fun settings() {
        val settingsFragment = supportFragmentManager.findFragmentById(R.id.settingsContainer)
        if (settingsFragment != null) {
            supportFragmentManager.beginTransaction().attach(settingsFragment).addToBackStack(null).commit()
        }
    }

    fun about () {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        when (event?.sensor?.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                oldAcc = currAcc
                currAcc = sqrt(x*x + y*y + z*z)
                val delta = currAcc - oldAcc
                acceleration = acceleration*0.9f + delta
                if (acceleration > 12) {
                    makeText(applicationContext, "shake", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.setFragmentResult(
                        Extra_SHAKE,
                        bundleOf()
                    )
                }

            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


}