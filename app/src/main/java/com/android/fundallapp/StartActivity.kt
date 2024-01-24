package com.android.fundallapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.fundallapp.auth.data.localdata.Authpreference
import com.android.fundallapp.auth.data.localdata.Authpreference.Companion.AUTH_KEY
import com.android.fundallapp.auth.data.model.UserData
import com.android.fundallapp.databinding.ActivityAuthBinding
import com.android.fundallapp.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Authpreference.initSharedPreference(this)
        val userData = Authpreference.get<UserData>(AUTH_KEY)

        val intent = if (userData == null){
            Intent(this, AuthActivity::class.java)
        }else{
            Intent(this, MainActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}