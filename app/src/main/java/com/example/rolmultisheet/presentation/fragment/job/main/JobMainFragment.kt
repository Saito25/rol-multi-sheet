package com.example.rolmultisheet.presentation.fragment.job.main

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
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.presentation.fragment.game.GameTabHostFragmentDirections
import com.example.rolmultisheet.presentation.util.event.observeEvent
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.recycler.doOnSwiped
import com.example.rolmultisheet.presentation.util.tab.PageFragment
import com.google.android.material.snackbar.Snackbar

class JobMainFragment : PageFragment(R.layout.common_list_fragment) {

    private val binding: CommonListFragmentBinding by viewBinding {
        CommonListFragmentBinding.bind(it)
    }

    private val viewModel: JobMainViewModel by viewModels {
        JobMainViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            )
        )
    }

    private val navController: NavController by lazy { findNavController() }

    private val listAdapter: JobMainListAdapter by lazy {
        JobMainListAdapter().apply {
            setOnItemClickListener { itemPosition ->
                onItemClick(currentList[itemPosition])
            }
        }
    }

    private fun onItemClick(job: Job) {
        navigateToJobEditionFragment(job.jobId)
    }

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
                viewModel.deleteJob(listAdapter.currentList[viewHolder.adapterPosition])
            }
        }
    }

    private fun observeViewModel() {
        viewModel.jobList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }

    private fun observeViewModelEvent() {
        viewModel.onDeleteJobEvent.observeEvent(viewLifecycleOwner) { job ->
            Snackbar.make(
                binding.root,
                getString(R.string.race_main_snackbar_title),
                Snackbar.LENGTH_LONG
            ).setAction(R.string.snackbar_action) {
                viewModel.recoveryJob(job)
            }.show()
        }
    }

    override fun onFabClick() {
        navigateToJobEditionFragment()
    }

    private fun navigateToJobEditionFragment(raceId: Long = 0) {
        val action = GameTabHostFragmentDirections.showRaceEditionFragment().also {
            it.raceId = raceId
        }
        navController.navigate(action)
    }
}