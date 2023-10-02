package com.genrikhsalexandr.androidintesive.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.genrikhsalexandr.androidintesive.R
import com.genrikhsalexandr.androidintesive.databinding.FragmentUserDetailBinding

class ContactDetailFragment : Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding: FragmentUserDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        binding.btEdit.setOnClickListener {
            editFragment()

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onBackInvokeCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backlUserListFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackInvokeCallBack)
    }

        private fun backlUserListFragment() {
        requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun editFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, EditContactFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {

        const val NAME = "EditFragment"

        fun newInstance(): ContactDetailFragment {
            return ContactDetailFragment()
        }
    }

}