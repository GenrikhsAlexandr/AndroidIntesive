package com.genrikhsalexandr.androidintesive.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsalexandr.androidintesive.databinding.FragmentContactsBinding
import kotlinx.coroutines.launch


class ContactsFragment : Fragment() {

    private val viewModel: ContactViewModel by viewModels()

    private var _binding: FragmentContactsBinding? = null
    private val binding: FragmentContactsBinding get() = _binding!!

    private val contactAdapter: ContactsAdapter = ContactsAdapter(
        onContactItemClickListener = { clickedContactItem ->
            viewModel.onItemClicked(clickedContactItem)
        },
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        binding.listItemContacts.adapter = contactAdapter

        lifecycleScope.launch {
            viewModel.listItems.collect {
                contactAdapter.submitList(it)
            }
            binding.btContactItem.setOnClickListener {

            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSwipeListener(binding.listItemContacts)
    }

    private fun setupSwipeListener(rvContactsList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = contactAdapter.currentList[viewHolder.adapterPosition]
                viewModel.delContactItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvContactsList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}