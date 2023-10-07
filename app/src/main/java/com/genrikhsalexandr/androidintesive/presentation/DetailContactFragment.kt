package com.genrikhsalexandr.androidintesive.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.genrikhsalexandr.androidintesive.ContactRepository
import com.genrikhsalexandr.androidintesive.R
import com.genrikhsalexandr.androidintesive.databinding.FragmentContactDetailBinding
import com.genrikhsalexandr.androidintesive.domain.Contact

class DetailContactFragment() : Fragment() {

    companion object {

        private const val BUNDLE_KEY_CONTACT = "contact"

        fun createInstance(contact: Contact): DetailContactFragment {
            return DetailContactFragment().apply {
                arguments = Bundle().apply {
                    putInt(BUNDLE_KEY_CONTACT, contact.id)
                }
            }
        }
    }

    private var _binding: FragmentContactDetailBinding? = null
    private val binding: FragmentContactDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactDetailBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onBackInvokeCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backContactListFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackInvokeCallBack)
    }

    private fun backContactListFragment() {
        requireActivity().supportFragmentManager.popBackStack(
            null, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }


    override fun onStart() {
        super.onStart()
        val contactId = requireArguments().getInt(BUNDLE_KEY_CONTACT)
        val contact = ContactRepository.getContact(contactId)
        with(binding) {
            iconContact.setImageResource(contact.image)
            nameContact.text = contact.name
            surNameContact.text = contact.surName
            numberContact.text = contact.number
            Log.d("xxx", "detailonStart${contact.image}")
        }
        binding.btEdit.setOnClickListener {
            val editFragment = EditContactFragment.createInstance(contact)
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.commit {
                replace(R.id.containerFragment, editFragment)
                addToBackStack(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}