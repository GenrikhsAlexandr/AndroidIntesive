package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.genrikhsalexandr.androidintesive.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drumView = binding.drum
        drumView.listener = object : CustomDrumView.Listener {
            override fun onDrumSpinned(position: Int) {
                when (position) {
                    1,2,4,6 -> {
                        binding.customTV.isVisible = true
                    }

                    else -> {
                        binding.image.isVisible = true
                        Picasso.get().load("https://picsum.photos/id/12/200")
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.not_supported)
                            .into(binding.image)
                    }
                }

                binding.reset.setOnClickListener {
                    binding.customTV.isVisible = false
                    Picasso.get().cancelRequest(binding.image)
                    binding.image.isVisible = false
                }
            }
        }
    }
}