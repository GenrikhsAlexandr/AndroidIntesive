package com.genrikhsalexandr.androidintesive

import FragmentB
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.genrikhsalexandr.androidintesive.databinding.FragmentABinding

class AFragment : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding: FragmentABinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        binding.btFragmentB.setOnClickListener {
            val fragmentB = FragmentB()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.containerFragment, fragmentB)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}