package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.genrikhsalexandr.androidintesive.databinding.FragmentDBinding

class FragmentD : Fragment() {

    private var _binding: FragmentDBinding? = null
    private val binding: FragmentDBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDBinding.inflate(inflater, container, false)
        binding.btBackB.setOnClickListener {
            toFragmentA()
        }
        return binding.root
    }

    private fun toFragmentA() {
        findNavController().navigate(R.id.action_fragmentD_to_fragmentA)
    }
}