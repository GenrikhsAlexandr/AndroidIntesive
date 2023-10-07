package com.genrikhsalexandr.androidintesive.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                arguments = Bundle().apply {
                    putInt(BUNDLE_KEY_CONTACT, contact.id)

                }
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
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        val contactId = requireArguments().getInt(BUNDLE_KEY_CONTACT)
        val contact = ContactRepository.getContact(contactId)
        with(binding) {
            iconContact.setImageResource(contact.image)
            nameContact.setText(contact.name)
            surNameContact.setText(contact.surName)
            numberContact.setText(contact.number)
        }
        binding.btSave.setOnClickListener {
            val updatedContact = Contact(
                id = contact.id,
                name = binding.nameContact.text.toString(),
                surName = binding.surNameContact.text.toString(),
                number = binding.numberContact.text.toString(),
                image = contact.image
            )
            ContactRepository.updateContact(updatedContact)
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.iconContact.setOnClickListener {
            onImageViewClick()
        }
    }

    private fun onImageViewClick() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
   startActivityForResult(intent, PICK_IMAGE_REQUEST)}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            binding.iconContact.setImageURI(data?.data)

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
