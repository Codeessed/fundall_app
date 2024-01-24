package com.android.fundallapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.fundallapp.databinding.ActivityAuthBinding
import com.android.fundallapp.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}