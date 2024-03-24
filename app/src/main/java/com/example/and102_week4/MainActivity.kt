package com.example.and102_week4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.and102_week4.R.id

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val supportFragmentManager = supportFragmentManager
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(id.container,TopRatedFragment(), null).commit()

    }
}