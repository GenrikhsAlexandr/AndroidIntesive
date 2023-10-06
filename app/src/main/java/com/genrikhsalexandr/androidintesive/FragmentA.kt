package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.androidintesive.databinding.FragmentABinding
import com.github.terrakok.cicerone.androidx.FragmentScreen

class FragmentA : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding: FragmentABinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        binding.btFragmentB.setOnClickListener {
            FragmentApplication.INSTANCE.router.navigateTo(FragmentScreen { FragmentB.newInstance() })
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}

