package com.yeho.holamundo.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yeho.holamundo.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private lateinit var  context: Context

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
             super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
                val view = binding.root
                        setContentView(view)
        context = this
        binding.btnMain.setOnClickListener { irAPantalla2() }
    }

    private fun irAPantalla2() {
        try {
            val intent = Intent(this, DetalleActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.wtf("YEHO", "msg:"+ e.message)
        }
    }
}