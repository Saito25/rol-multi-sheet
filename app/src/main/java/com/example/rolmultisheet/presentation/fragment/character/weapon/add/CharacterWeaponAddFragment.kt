package com.example.rolmultisheet.presentation.fragment.character.weapon.add

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.SharedListAddFragmentBinding
import com.example.rolmultisheet.presentation.util.event.observeEvent
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.recycler.newLongKeySelectionTrackerBuilder

private const val STATE_SELECTION: String = "STATE_SELECTION"

class CharacterWeaponAddFragment : Fragment(R.layout.shared_list_add_fragment) {

    private val binding: SharedListAddFragmentBinding by viewBinding {
        SharedListAddFragmentBinding.bind(it)
    }

    private val args: CharacterWeaponAddFragmentArgs by navArgs()

    private val viewModel: CharacterWeaponAddViewModel by viewModels {
        CharacterWeaponAddViewModelFactory(
            RoomRepository(AppDatabase.getInstance(requireContext()).appDao),
            args.weaponsId,
            args.characterId
        )
    }

    private val listAdapter by lazy {
        WeaponMainListAddAdapterNoEditable()
    }

    private val selectionTracker: SelectionTracker<Long> by lazy {
        newLongKeySelectionTrackerBuilder(
            STATE_SELECTION,
            binding.listSharedAdd,
            listAdapter
        ).build()
    }

    private val navController: NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
        observeViewModelEvent()
        if (savedInstanceState != null) {
            selectionTracker.onRestoreInstanceState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        selectionTracker.onSaveInstanceState(outState)
    }

    private fun setupViews() {
        setupToolBar()
        setupRecyclerView()
    }

    private fun observeViewModel() {
        observeCharacterWeaponList()
    }

    private fun observeViewModelEvent() {
        observerOnCloseEvent()
    }

    private fun setupToolBar() {
        binding.toolbarSharedAdd.run {
            setupWithNavController(navController)
            setOnMenuItemClickListener { onMenuItemClick(it) }
            inflateMenu(R.menu.save_menu)
        }
    }

    private fun setupRecyclerView() {
        binding.listSharedAdd.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            listAdapter.selectionTracker = selectionTracker
        }
    }

    private fun observeCharacterWeaponList() {
        viewModel.characterWeaponsList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }

    private fun observerOnCloseEvent() {
        viewModel.onCloseFragment.observeEvent(viewLifecycleOwner) {
            if (it) {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuSave -> viewModel.addWeaponsToCharacter(selectionTracker.selection)
            else -> return false
        }
        return true
    }
}