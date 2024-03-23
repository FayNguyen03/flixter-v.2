package com.example.and102_week4

import aedroidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val supportFragmentManager = supportFragmentManager
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(id.content, MoviesFragment(), null).commit()
        }
    }
}