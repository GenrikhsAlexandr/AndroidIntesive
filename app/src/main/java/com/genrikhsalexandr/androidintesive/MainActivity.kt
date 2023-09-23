package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.genrikhsalexandr.androidintesive.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.reset.setOnClickListener {
            with(binding) {
                customTV.text = "Hello"
               Picasso.get().load("https://picsum.photos/id/12/200")
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.color.black)
                    .into(image)
            }
        }
    }
}