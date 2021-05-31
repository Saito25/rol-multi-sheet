package com.example.rolmultisheet.presentation.fragment.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.*
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.HomeFragmentBinding
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.presentation.util.fragment.AppBarConfigurationOwner
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.recycler.doOnSwiped

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val binding: HomeFragmentBinding by viewBinding {
        HomeFragmentBinding.bind(it)
    }

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(
            RoomRepository(AppDatabase.getInstance(requireContext()).appDao)
        )
    }

    private val listAdapter: HomeListAdapter by lazy {
        HomeListAdapter().apply {
            setOnItemClickListener { itemPosition ->
                navigateToCharacterInformationFragment(currentList[itemPosition])
            }
        }
    }

    private fun navigateToCharacterInformationFragment(character: Character) {
        val action =
            HomeFragmentDirections.showCharacterTabHostFragmentAction(character.characterId)
        navController.navigate(action)
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
        setupFab()
    }

    private fun observeViewModel() {
        viewModel.characters.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }

    private fun setupToolBar() {
        binding.tbHome.run {
            setupWithNavController(
                navController,
                (requireActivity() as AppBarConfigurationOwner).appBarConfiguration
            )
            inflateMenu(R.menu.main_nav_menu)
            setOnMenuItemClickListener {
                onMenuItemClick(it)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.listHomeCharacters.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            doOnSwiped(swipeDirs = ItemTouchHelper.RIGHT) { viewHolder, _ ->
                viewModel.deleteCharacter(listAdapter.currentList[viewHolder.adapterPosition])
            }
        }
    }

    private fun setupFab() {
        binding.fabHomeAddCharacter.setOnClickListener {
            navigateToCreateCharacter()
        }
    }

    private fun navigateToCreateCharacter() {
        val action = HomeFragmentDirections.showCreationAction()
        navController.navigate(action)
    }

    private fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuGame -> navigateToEditGame()
            R.id.menuGameInfo -> navigateToInfo()
            else -> return false
        }
        return true
    }

    private fun navigateToEditGame() {
        val action = HomeFragmentDirections.showGameTabHostFragmentDirection()
        navController.navigate(action)
    }

    private fun navigateToInfo() {
        val action = HomeFragmentDirections.showInfoFragmentDirection()
        navController.navigate(action)
    }

}