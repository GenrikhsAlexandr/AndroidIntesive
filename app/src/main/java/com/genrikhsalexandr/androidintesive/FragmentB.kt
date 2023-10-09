package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.genrikhsalexandr.androidintesive.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding: FragmentBBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)

        with(binding) {
            btFragmentC.setOnClickListener {
                toFragmentC()
            }
            btBackA.setOnClickListener {
                toFragmentA()
            }
        }
        return binding.root
    }

    private fun toFragmentC() {
        val hello = "Hello Fragment C"
        val bundle = Bundle()
        bundle.putString("message", hello)
        findNavController().navigate(R.id.action_fragmentB_to_fragmentC, bundle)
    }

    private fun toFragmentA() {
        findNavController().navigate(R.id.action_fragmentB_to_fragmentA)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}