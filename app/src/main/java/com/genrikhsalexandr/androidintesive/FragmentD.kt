package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.androidintesive.databinding.FragmentDBinding
import com.github.terrakok.cicerone.androidx.FragmentScreen

class FragmentD : Fragment() {

    private var _binding: FragmentDBinding? = null
    private val binding: FragmentDBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDBinding.inflate(inflater, container, false)
        binding.btBackB.setOnClickListener {
            FragmentApplication.INSTANCE.router.backTo(FragmentScreen { FragmentA() })
        }
        return binding.root
    }

    companion object {
        fun newInstance(): FragmentD {
            return FragmentD()
        }
    }
}