package com.example.yana.kinopoiskhome.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yana.kinopoiskhome.R
import com.example.yana.kinopoiskhome.databinding.ActivityKinoInfoBinding

class KinoInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKinoInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKinoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}