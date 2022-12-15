package com.example.androidchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidchallenge.R
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {

        }

    }
}