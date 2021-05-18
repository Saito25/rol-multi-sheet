package com.example.rolmultisheet.presentation.fragment.spell.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.CommonListFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.recycler.doOnSwiped

class SpellMainFragment : Fragment(R.layout.common_list_fragment) {

    private val binding: CommonListFragmentBinding by viewBinding {
        CommonListFragmentBinding.bind(it)
    }

    private val viewModel: SpellMainViewModel by viewModels {
        SpellMainViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            )
        )
    }

    private val listAdapter: SpellMainListAdapter by lazy {
        SpellMainListAdapter()
    }

    private val navController: NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.listCommonRoot.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            doOnSwiped(swipeDirs = ItemTouchHelper.RIGHT) { viewHolder, _ ->
                viewModel.deleteSpell(listAdapter.currentList[viewHolder.adapterPosition])
            }
        }
    }

    private fun observeViewModel() {
        observeSpellList()
    }

    private fun observeSpellList() {
        viewModel.spellList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }
}