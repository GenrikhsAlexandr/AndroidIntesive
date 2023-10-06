package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.androidintesive.databinding.FragmentBBinding
import com.github.terrakok.cicerone.androidx.FragmentScreen

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
                FragmentApplication.INSTANCE.router.navigateTo(FragmentScreen { FragmentC.newInstance() })

            }
            btBackA.setOnClickListener {
                FragmentApplication.INSTANCE.router.backTo(FragmentScreen { FragmentA() })

            }
        }
        return binding.root
    }

    companion object {
        fun newInstance(): FragmentB {
            return FragmentB()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}