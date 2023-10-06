package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.androidintesive.databinding.FragmentCBinding
import com.github.terrakok.cicerone.androidx.FragmentScreen

class FragmentC : Fragment() {

    private var _binding: FragmentCBinding? = null
    private val binding: FragmentCBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        with(binding) {
            val message = arguments?.getString("message")
            with(binding) {
                tvHello.text = message
                btFragmentD.setOnClickListener {
                    FragmentApplication.INSTANCE.router.navigateTo(FragmentScreen { FragmentD.newInstance() })
                }
            }
            btBackB.setOnClickListener {
                FragmentApplication.INSTANCE.router.backTo(FragmentScreen { FragmentB() })
            }
        }
        return binding.root
    }

    companion object {
        fun newInstance(): FragmentC {
            return FragmentC().apply {
                arguments = Bundle().apply {
                    putString("message", "Hello Fragment C")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
