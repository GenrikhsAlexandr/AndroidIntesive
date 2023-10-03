package com.genrikhsalexandr.androidintesive.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.genrikhsalexandr.androidintesive.databinding.FragmentContactDetailBinding
import com.genrikhsalexandr.androidintesive.domain.Contact

class DetailContactFragment(private val contact: Contact) : DialogFragment() {
        companion object {
            private const val TAG = "DetailContactFragment"

            fun show(contact: Contact, fragmentManager: FragmentManager) {
                val detailFragment = DetailContactFragment(contact)
                detailFragment.show(
                    fragmentManager,
                    TAG
                )
            }
        }

    private var _binding: FragmentContactDetailBinding? = null
    private val binding: FragmentContactDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailBinding.inflate(inflater, container, false)
            .apply {
                iconContact.id = contact.image
                nameContact.text = contact.name
                surNameContact.text = contact.surName
                numberContact.text = contact.number
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

}