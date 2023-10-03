package com.genrikhsalexandr.androidintesive.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.androidintesive.databinding.FragmentContactEditBinding

class EditContactFragment : Fragment() {

    private var _binding: FragmentContactEditBinding? = null
    private val binding: FragmentContactEditBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onBackInvokeCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backDetailUserFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackInvokeCallBack)
    }
private fun backDetailUserFragment() {
    requireActivity().supportFragmentManager.popBackStack()

}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
