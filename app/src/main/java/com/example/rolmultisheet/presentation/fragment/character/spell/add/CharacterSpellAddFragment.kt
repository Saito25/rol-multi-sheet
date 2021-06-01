package com.example.rolmultisheet.presentation.fragment.character.spell.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.CommonListFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class CharacterSpellAddFragment : Fragment(R.layout.common_list_fragment) {

    private val binding: CommonListFragmentBinding by viewBinding {
        CommonListFragmentBinding.bind(it)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        setupRecyclerView()
    }

    private fun observeViewModel() {
        observeCharacterSpellList()
    }

    private fun setupRecyclerView() {
        binding.listCommonRoot.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun observeCharacterSpellList() {
        viewModel.characterSpellsList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it.spellLists)
        }
    }
}