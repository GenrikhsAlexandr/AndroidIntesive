package com.genrikhsalexandr.androidintesive.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.genrikhsalexandr.androidintesive.R
import com.genrikhsalexandr.androidintesive.databinding.FragmentContactDetailBinding
import com.genrikhsalexandr.androidintesive.domain.Contact

class DetailContactFragment() : Fragment() {

    companion object {

        private const val BUNDLE_KEY_CONTACT = "contact"

        fun createInstance(contact: Contact): DetailContactFragment {
            return DetailContactFragment().apply {
                arguments = bundleOf(
                    BUNDLE_KEY_CONTACT to contact
                )
            }
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
                val args = requireArguments()
                val contact = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    args.getParcelable(BUNDLE_KEY_CONTACT, Contact::class.java)!!
                } else {
                    args.getParcelable(BUNDLE_KEY_CONTACT)!!
                }
                iconContact.setImageResource(contact.image)
                nameContact.text = contact.name
                surNameContact.text = contact.surName
                numberContact.text = contact.number
                btEdit.setOnClickListener {
                    val editFragment = EditContactFragment.createInstance(contact)
                    val fragmentManager = requireActivity().supportFragmentManager
                    fragmentManager.commit {
                        replace(R.id.containerFragment, editFragment)
                        addToBackStack(null)
                    }
                }
            }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}