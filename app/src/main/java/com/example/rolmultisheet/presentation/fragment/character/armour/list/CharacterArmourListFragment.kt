package com.example.rolmultisheet.presentation.fragment.character.armour.list

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

class CharacterArmourListFragment : PageFragment(R.layout.common_list_fragment) {

    private val binding: CommonListFragmentBinding by viewBinding {
        CommonListFragmentBinding.bind(it)
    }

    private val viewModel: CharacterArmourListViewModel by viewModels {
        CharacterArmourListViewModelFactory(
            RoomRepository(AppDatabase.getInstance(requireContext()).appDao),
            (requireParentFragment() as ArgumentsOwner).characterId
        )
    }

    private val listAdapter by lazy {
        ArmourMainListAdapterNoEditable()
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
        observeCharacterArmourList()
    }

    private fun setupRecyclerView() {
        binding.listCommonRoot.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            doOnSwiped(swipeDirs = ItemTouchHelper.RIGHT) { viewHolder, _ ->
                viewModel.deleteArmourFromCharacter(listAdapter.currentList[viewHolder.adapterPosition])
            }
        }
    }

    private fun observeCharacterArmourList() {
        viewModel.characterArmoursList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it.armourLists)
        }
    }

    override fun onFabClick() {
        val armoursId = viewModel.characterArmourIdList
        val action = CharacterTabHostFragmentDirections.showCharacterArmourAddDirectionAction(
            armoursId,
            (requireParentFragment() as ArgumentsOwner).characterId,
        )
        navController.navigate(action)
    }
}
