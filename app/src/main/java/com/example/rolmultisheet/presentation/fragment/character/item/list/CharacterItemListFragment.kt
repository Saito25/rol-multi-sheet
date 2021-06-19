package com.example.rolmultisheet.presentation.fragment.character.item.list

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

class CharacterItemListFragment : PageFragment(R.layout.common_list_fragment) {

    private val binding: CommonListFragmentBinding by viewBinding {
        CommonListFragmentBinding.bind(it)
    }

    private val viewModel: CharacterItemListViewModel by viewModels {
        CharacterItemListViewModelFactory(
            RoomRepository(AppDatabase.getInstance(requireContext()).appDao),
            (requireParentFragment() as ArgumentsOwner).characterId
        )
    }

    private val listAdapter by lazy {
        ItemMainListAdapterNoEditable()
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
        observeCharacterItemList()
    }

    private fun setupRecyclerView() {
        binding.listCommonRoot.run {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            doOnSwiped(swipeDirs = ItemTouchHelper.RIGHT) { viewHolder, _ ->
                viewModel
                    .deleteItemFromCharacter(listAdapter.currentList[viewHolder.adapterPosition])
            }
        }
    }

    private fun observeCharacterItemList() {
        viewModel.characterItemsList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it.itemLists)
        }
    }

    override fun onFabClick() {
        val itemsId = viewModel.characterItemIdList
        val action = CharacterTabHostFragmentDirections.showCharacterItemAddDirection(
            itemsId,
            (requireParentFragment() as ArgumentsOwner).characterId,
        )
        navController.navigate(action)
    }
}
