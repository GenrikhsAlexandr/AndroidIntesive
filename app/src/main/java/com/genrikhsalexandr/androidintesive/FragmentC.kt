package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
                val fragmentD = FragmentD()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.containerFragment, fragmentD)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            btBackB.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
}
