package com.genrikhsalexandr.androidintesive.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.genrikhsalexandr.androidintesive.data.contact.ContactRepositoryImpl
import com.genrikhsalexandr.androidintesive.databinding.ActivityMainBinding
import com.genrikhsalexandr.androidintesive.domain.contact.ContactItem

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}