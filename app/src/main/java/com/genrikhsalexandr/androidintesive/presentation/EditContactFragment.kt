package com.genrikhsalexandr.androidintesive.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.androidintesive.R
import com.genrikhsalexandr.androidintesive.databinding.FragmentEditUserBinding

class EditContactFragment : Fragment() {

    private var _binding: FragmentEditUserBinding? = null
    private val binding: FragmentEditUserBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditUserBinding.inflate(inflater, container, false)
        binding.btSave.setOnClickListener {
            detailFragment()
        }
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
    private fun detailFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, ContactDetailFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance(): EditContactFragment {
            return EditContactFragment()
        }
    }

}
