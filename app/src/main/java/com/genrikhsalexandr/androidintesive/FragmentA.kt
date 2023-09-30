package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.genrikhsalexandr.androidintesive.databinding.FragmentABinding

class FragmentA : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding: FragmentABinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        binding.btFragmentB.setOnClickListener {
            fragmentB()
        }
        return binding.root
    }

    private fun fragmentB() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, FragmentB.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}