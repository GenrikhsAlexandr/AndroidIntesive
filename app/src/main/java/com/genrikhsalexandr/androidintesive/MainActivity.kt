package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.genrikhsalexandr.androidintesive.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {
    private val navigator = AppNavigator(this, R.id.containerFragment)

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FragmentApplication.INSTANCE.navigatorHolder.setNavigator(navigator)
    }
    override fun onDestroy() {
        super.onDestroy()
        FragmentApplication.INSTANCE.navigatorHolder.removeNavigator()
    }
}