package com.genrikhsalexandr.androidintesive.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.genrikhsalexandr.androidintesive.R
import com.genrikhsalexandr.androidintesive.databinding.FragmentContactListBinding
import com.genrikhsalexandr.androidintesive.domain.Contact
import kotlinx.coroutines.launch

class ContactListFragment : Fragment() {

    private val viewModel: ContactViewModel by viewModels()

    private var _binding: FragmentContactListBinding? = null
    private val binding: FragmentContactListBinding get() = _binding!!

    private val contactAdapter: ContactAdapter = ContactAdapter(
        onContactItemClickListener = { contact ->
            showDetail(contact)
        },
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItemContacts.adapter = contactAdapter

        lifecycleScope.launch {
            viewModel.contactsList.collect() {
                contactAdapter.submitList(it)
            }
        }
    }

    private fun showDetail(contact: Contact) {
        val detailFragment = DetailContactFragment.createInstance(
            contact
        )
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.commit {
            replace(R.id.containerFragment, detailFragment)
            addToBackStack(null)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}