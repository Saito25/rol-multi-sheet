package com.example.rolmultisheet.presentation.fragment.character.spell.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.SharedListAddFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class CharacterSpellAddFragment : Fragment(R.layout.shared_list_add_fragment) {

    private val binding: SharedListAddFragmentBinding by viewBinding {
        SharedListAddFragmentBinding.bind(it)
    }

    private val args: CharacterSpellAddFragmentArgs by navArgs()

    private val viewModel: CharacterSpellAddViewModel by viewModels {
        CharacterSpellAddViewModelFactory(
            RoomRepository(AppDatabase.getInstance(requireContext()).appDao),
            args.spellsId
        )
    }

    private val listAdapter by lazy {
        SpellMainListAddAdapterNoEditable()
    }

    private val navController: NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        setupToolBar()
        setupRecyclerView()
    }

    private fun observeViewModel() {
        observeCharacterSpellList()
    }

    private fun setupToolBar() {
        binding.toolbarSharedAdd.run {
            setupWithNavController(navController)
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
        }
    }

    private fun observeCharacterSpellList() {
        viewModel.characterSpellsList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }
}