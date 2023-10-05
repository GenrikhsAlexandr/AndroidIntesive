package com.genrikhsalexandr.androidintesive.presentation

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.genrikhsalexandr.androidintesive.R
import com.genrikhsalexandr.androidintesive.databinding.FragmentContactEditBinding
import com.genrikhsalexandr.androidintesive.domain.Contact

class EditContactFragment : Fragment() {
    companion object {
        private val PICK_IMAGE_REQUEST = 1

        private const val BUNDLE_KEY_CONTACT = "contact"

        fun createInstance(contact: Contact): EditContactFragment {
            return EditContactFragment().apply {
                arguments = bundleOf(
                    BUNDLE_KEY_CONTACT to contact
                )
            }
        }
    }

    private var _binding: FragmentContactEditBinding? = null
    private val binding: FragmentContactEditBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactEditBinding.inflate(inflater, container, false)
            .apply {
                val args = requireArguments()
                val contact = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    args.getParcelable(BUNDLE_KEY_CONTACT, Contact::class.java)!!
                } else {
                    args.getParcelable(BUNDLE_KEY_CONTACT)!!
                }
                iconContact.setImageResource(contact.image)
                nameContact.setText(contact.name)
                surNameContact.setText(contact.surName)
                numberContact.setText(contact.number)
                btSave.setOnClickListener {
                    showEdit(contact)
                }
                iconContact.setOnClickListener {
                    onImageViewClick()
                }
            }
        return binding.root
    }
    private fun onImageViewClick() {
        openImagePickerDialog()
    }

    private fun openImagePickerDialog() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            if (selectedImageUri != null) {
                binding.iconContact.setImageURI(selectedImageUri)
            }
        }
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

private fun showEdit(contact: Contact) {
    val detailContactFragment = DetailContactFragment.createInstance(contact)
    val fragmentManager = requireActivity().supportFragmentManager
    fragmentManager.commit {
        replace(R.id.containerFragment, detailContactFragment)
        addToBackStack(null)
    }
}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
