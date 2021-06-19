package com.example.rolmultisheet.presentation.fragment.character.spell.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.CommonListFragmentBinding
import com.example.rolmultisheet.presentation.fragment.character.host.CharacterTabHostFragmentDirections
import com.example.rolmultisheet.presentation.util.fragment.ArgumentsOwner
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.recycler.doOnSwiped
import com.example.rolmultisheet.presentation.util.tab.PageFragment

class CharacterSpellListFragment : PageFragment(R.layout.common_list_fragment) {

    private val binding: CommonListFragmentBinding by viewBinding {
        CommonListFragmentBinding.bind(it)
    }

    private val viewModel: CharacterSpellListViewModel by viewModels {
        CharacterSpellListViewModelFactory(
            RoomRepository(AppDatabase.getInstance(requireContext()).appDao),
            (requireParentFragment() as ArgumentsOwner).characterId
        )
    }

    private val listAdapter by lazy {
        SpellMainListAdapterNoEditable()
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

    private fun observeViewModel() {
        observeCharacterSpellList()
    }

    private fun setupRecyclerView() {
        binding.listCommonRoot.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            doOnSwiped(swipeDirs = ItemTouchHelper.RIGHT) { viewHolder, _ ->
                viewModel.deleteSpellFromCharacter(listAdapter.currentList[viewHolder.adapterPosition])
            }
        }
    }

    private fun observeCharacterSpellList() {
        viewModel.characterSpellsList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it.spellLists)
        }
    }

    override fun onFabClick() {
        val spellsId = viewModel.characterSpellIdList
        val action = CharacterTabHostFragmentDirections.showCharacterSpellAddDirection(
            spellsId,
            (requireParentFragment() as ArgumentsOwner).characterId,
        )
        navController.navigate(action)
    }
}
