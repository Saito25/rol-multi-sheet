package com.example.rolmultisheet.presentation.fragment.job.main

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
        observerOnDeleteJobEvent()
        observerOnDeleteError()
    }

    private fun observerOnDeleteJobEvent() {
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

    private fun observerOnDeleteError() {
        viewModel.onDeleteError.observeEvent(viewLifecycleOwner) {
            Snackbar.make(binding.root, getString(it.string), Snackbar.LENGTH_LONG).show()
            listAdapter.notifyDataSetChanged()
        }
    }

    override fun onFabClick() {
        navigateToJobEditionFragment()
    }

    private fun navigateToJobEditionFragment(jobId: Long = 0) {
        val action = GameTabHostFragmentDirections.showJobEditionFragment().also {
            it.jobId = jobId
        }
        navController.navigate(action)
    }
}
