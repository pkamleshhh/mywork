package com.example.studentrecords.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.studentrecords.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var task= Runnable {
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        var handler= Handler()
        handler.postDelayed(task,1800)
    }
}