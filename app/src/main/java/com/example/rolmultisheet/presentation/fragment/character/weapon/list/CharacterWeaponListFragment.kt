package com.example.rolmultisheet.presentation.fragment.character.weapon.list

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
import com.example.rolmultisheet.presentation.util.fragment.ArgumentsOwner
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.recycler.doOnSwiped
import com.example.rolmultisheet.presentation.util.tab.PageFragment

class CharacterWeaponListFragment : PageFragment(R.layout.common_list_fragment) {

    private val binding: CommonListFragmentBinding by viewBinding {
        CommonListFragmentBinding.bind(it)
    }

    private val viewModel: CharacterWeaponListViewModel by viewModels {
        CharacterWeaponListViewModelFactory(
            RoomRepository(AppDatabase.getInstance(requireContext()).appDao),
            (requireParentFragment() as ArgumentsOwner).characterId
        )
    }

    private val listAdapter by lazy {
        WeaponMainListAdapterNoEditable()
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
        observeCharacterWeaponList()
    }

    private fun setupRecyclerView() {
        binding.listCommonRoot.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            doOnSwiped(swipeDirs = ItemTouchHelper.RIGHT) { viewHolder, _ ->
                viewModel.deleteWeaponFromCharacter(listAdapter.currentList[viewHolder.adapterPosition])
            }
        }
    }

    private fun observeCharacterWeaponList() {
        viewModel.characterWeaponsList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it.weaponLists)
        }
    }

    override fun onFabClick() {
//        val weaponsId = viewModel.characterWeaponIdList
//        val action = CharacterTabHostFragmentDirections.showCharacterWeaponAddDirection(
//            weaponsId,
//            (requireParentFragment() as ArgumentsOwner).characterId,
//        )
//        navController.navigate(action)
    }
}