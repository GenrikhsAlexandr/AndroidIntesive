package com.genrikhsalexandr.androidintesive.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.genrikhsalexandr.androidintesive.R
import com.genrikhsalexandr.androidintesive.databinding.FragmentUserListBinding
import kotlinx.coroutines.launch

class ContactListFragment : Fragment() {

    private val viewModel: ContactViewModel by viewModels()

    private var _binding: FragmentUserListBinding? = null
    private val binding: FragmentUserListBinding get() = _binding!!

    private val contactAdapter: ContactAdapter = ContactAdapter(
        onContactItemClickListener = { clickedContactItem ->
            viewModel.onItemClicked(clickedContactItem)
        },
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        binding.listItemContacts.adapter = contactAdapter


        return binding.root

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
}