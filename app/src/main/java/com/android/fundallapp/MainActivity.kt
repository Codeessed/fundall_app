package com.android.fundallapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.android.fundallapp.auth.data.localdata.Authpreference
import com.android.fundallapp.databinding.ActivityAuthBinding
import com.android.fundallapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Authpreference.initSharedPreference(this)

        val navHost = supportFragmentManager.findFragmentById(R.id.main_navHost_Container)
                as NavHostFragment
        val navController = navHost.findNavController()
        val navGraph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        navGraph.setStartDestination(R.id.homeFragment)
        navController.graph = navGraph
    }
}