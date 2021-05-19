package com.example.rolmultisheet.presentation.fragment.spell.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.CommonListFragmentBinding
import com.example.rolmultisheet.domain.model.Spell
import com.example.rolmultisheet.presentation.fragment.game.GameTabHostFragmentDirections
import com.example.rolmultisheet.presentation.util.event.observeEvent
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.recycler.doOnSwiped
import com.example.rolmultisheet.presentation.util.tab.PageFragment
import com.google.android.material.snackbar.Snackbar

class SpellMainFragment : PageFragment(R.layout.common_list_fragment) {

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
        SpellMainListAdapter().apply {
            setOnItemClickListener { itemPosition ->
                onItemClick(currentList[itemPosition])
            }
        }
    }

    private val navController: NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
        observeViewModelEvent()
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

    private fun observeViewModelEvent() {
        viewModel.onDeleteSpellEvent.observeEvent(viewLifecycleOwner) { spell ->
            Snackbar.make(
                binding.root,
                getString(R.string.spell_main_snackbar_title),
                Snackbar.LENGTH_LONG
            ).setAction(R.string.snackbar_action) {
                viewModel.recoverySpell(spell)
            }.show()
        }
    }

    override fun onFabClick() {
        navigateToSpellEditionFragment()
    }

    private fun onItemClick(spell: Spell) {
        navigateToSpellEditionFragment(spell.spellId)
    }

    private fun navigateToSpellEditionFragment(spellId: Long = 0) {
        val action = GameTabHostFragmentDirections.showSpellEditionFragmentAction().also {
            it.spellId = spellId
        }
        navController.navigate(action)
    }
}