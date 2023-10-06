package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.genrikhsalexandr.androidintesive.databinding.FragmentCBinding

class FragmentC : Fragment() {

    private var _binding: FragmentCBinding? = null
    private val binding: FragmentCBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        val message = arguments?.getString("message")
        with(binding) {
            tvHello.text = message
            btFragmentD.setOnClickListener {
                toFragmentD()
            }
            btBackB.setOnClickListener {
                toFragmentB()
            }
        }
        return binding.root
    }

    private fun toFragmentD() {
        findNavController().navigate(R.id.action_fragmentC_to_fragmentD)
    }

    private fun toFragmentB() {
        findNavController().navigate(R.id.action_fragmentC_to_fragmentB)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
