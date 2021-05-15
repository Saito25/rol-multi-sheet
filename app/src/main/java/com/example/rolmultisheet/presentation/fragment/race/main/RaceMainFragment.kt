package com.example.rolmultisheet.presentation.fragment.race.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.CommonListFragmentBinding
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.presentation.fragment.game.GameTabHostFragmentDirections
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.tab.PageFragment

class RaceMainFragment : PageFragment(R.layout.common_list_fragment) {

    private val binding: CommonListFragmentBinding by viewBinding {
        CommonListFragmentBinding.bind(it)
    }

    private val viewModel: RaceMainViewModel by viewModels {
        RaceMainViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            )
        )
    }

    private val listAdapter: RaceMainListAdapter by lazy {
        RaceMainListAdapter().apply {
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
        }
    }

    private fun observeViewModel() {
        viewModel.raceList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }

    private fun onItemClick(race: Race) {
        navigateToRaceEditionFragment(race.raceId)
    }

    override fun onFabClick() {
        navigateToRaceEditionFragment()
    }

    private fun navigateToRaceEditionFragment(raceId: Long = 0) {
        val action = GameTabHostFragmentDirections.showRaceEditionFragment().also {
            it.raceId = raceId
        }
        navController.navigate(action)
    }
}