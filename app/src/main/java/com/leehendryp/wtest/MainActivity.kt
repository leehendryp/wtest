package com.leehendryp.wtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leehendryp.wtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityMainBinding.inflate(layoutInflater).root
        setContentView(view)
    }
}
