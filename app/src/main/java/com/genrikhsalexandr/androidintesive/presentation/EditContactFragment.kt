package com.genrikhsalexandr.androidintesive.presentation

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.androidintesive.ContactRepository
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
                    val updatedContact = Contact(
                        id = contact.id,
                        name = nameContact.text.toString(),
                        surName = surNameContact.text.toString(),
                        number = numberContact.text.toString(),
                        image = contact.image)
                    ContactRepository.updateContact(updatedContact)
                    requireActivity().supportFragmentManager.popBackStack()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
