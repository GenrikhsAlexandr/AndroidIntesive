package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.androidintesive.databinding.FragmentBBinding

class FragmentB : Fragment() {

        private var _binding: FragmentBBinding? = null
        private val binding: FragmentBBinding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentBBinding.inflate(inflater, container, false)

            with(binding) {
                btFragmentC.setOnClickListener {
                    fragmentC()
                }
                btBackA.setOnClickListener {
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
            return binding.root
            }


    private fun fragmentC() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, FragmentC.newInstance())
            .addToBackStack(null)
            .commit()
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    companion object {
        fun newInstance(): FragmentB {
            return FragmentB()
        }
    }
}