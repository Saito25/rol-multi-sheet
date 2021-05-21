package com.example.rolmultisheet.presentation.fragment.weapon.main

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
import com.example.rolmultisheet.domain.model.Weapon
import com.example.rolmultisheet.presentation.util.event.observeEvent
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.recycler.doOnSwiped
import com.example.rolmultisheet.presentation.util.tab.PageFragment
import com.google.android.material.snackbar.Snackbar

class WeaponMainFragment : PageFragment(R.layout.common_list_fragment) {

    private val binding: CommonListFragmentBinding by viewBinding {
        CommonListFragmentBinding.bind(it)
    }

    private val viewModel: WeaponMainViewModel by viewModels {
        WeaponMainViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            )
        )
    }

    private val listAdapter: WeaponMainListAdapter by lazy {
        WeaponMainListAdapter().apply {
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
                viewModel.deleteWeapon(listAdapter.currentList[viewHolder.adapterPosition])
            }
        }
    }

    private fun observeViewModel() {
        observeItemList()
    }

    private fun observeItemList() {
        viewModel.weaponList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }

    private fun observeViewModelEvent() {
        viewModel.onDeleteWeaponEvent.observeEvent(viewLifecycleOwner) { weapon ->
            Snackbar.make(
                binding.root,
                getString(R.string.item_main_snackbar_title),
                Snackbar.LENGTH_LONG
            ).setAction(R.string.snackbar_action) {
                viewModel.recoveryWeapon(weapon)
            }.show()
        }
    }

    override fun onFabClick() {
        navigateToItemEditionFragment()
    }

    private fun onItemClick(weapon: Weapon) {
        navigateToItemEditionFragment(weapon.weaponId)
    }

    private fun navigateToItemEditionFragment(weaponId: Long = 0) {
//        val action = GameTabHostFragmentDirections.showWeaponEditionFragment().also {
//            it.weaponId = weaponId
//        }
//        navController.navigate(action)
    }
}