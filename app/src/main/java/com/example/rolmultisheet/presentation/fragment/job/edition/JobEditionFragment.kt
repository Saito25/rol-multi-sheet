package com.example.rolmultisheet.presentation.fragment.job.edition

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.JobEditionFragmentBinding
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.presentation.util.event.observeEvent
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class JobEditionFragment : Fragment(R.layout.job_edition_fragment) {

    private val binding: JobEditionFragmentBinding by viewBinding {
        JobEditionFragmentBinding.bind(it)
    }

    private val args: JobEditionFragmentArgs by navArgs()

    private val viewModel: JobEditionViewModel by viewModels {
        JobEditionViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            ),
            args.jobId
        )
    }

    private val navController: NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModelEvent()
    }

    private fun setupViews() {
        setupToolBar()
    }

    private fun setupToolBar() {
        binding.toolbarJobEdition.run {
            setupWithNavController(navController)
            inflateMenu(R.menu.save_menu)
            setOnMenuItemClickListener { onMenuItemClick(it) }
            setupToolbarNameIfNoRace()
        }
    }

    private fun onMenuItemClick(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.menuSave -> validateTextFields()
            else -> return false
        }
        return true
    }

    private fun setupToolbarNameIfNoRace() {
        if (args.jobId == 0L) {
            binding.toolbarJobEdition.title = getString(R.string.job_edition_toolbar_title)
        }
    }

    private fun validateTextFields() {
        binding.run {
            viewModel.validateTextFields(
                inputJobEditionName.text.toString(),
                inputJobEditionHitDice.text.toString(),
                inputJobEditionFeature.text.toString(),
                inputJobEditionSaveThrow.text.toString(),
            )
        }

    }


    private fun observeViewModelEvent() {
        observeRaceEvent()
        observeOnInvalidNameEvent()
        observeOnInvalidHidDiceEvent()
        observeOnSaveEvent()
        observeOnCloseEvent()
    }

    private fun observeRaceEvent() {
        viewModel.job.observeEvent(viewLifecycleOwner) { job ->
            if (job != null) {
                setupToolBarName(job)
                fillTextFieldsWithRaceInfo(job)
            }
        }
    }

    private fun fillTextFieldsWithRaceInfo(job: Job) {
        binding.run {
            inputJobEditionName.setText(job.jobName)
            inputJobEditionHitDice.setText(job.jobHit)
            if (job.jobFeature != null) {
                inputJobEditionFeature.setText(job.jobFeature)
            }
            if (job.jobSaveThrow != null) {
                inputJobEditionSaveThrow.setText(job.jobSaveThrow)
            }
        }
    }

    private fun observeOnInvalidNameEvent() {
        viewModel.onInvalidName.observeEvent(viewLifecycleOwner) {
            binding.inputJobEditionName.error = getString(it.string)
        }
    }

    private fun observeOnInvalidHidDiceEvent() {
        viewModel.onInvalidHitDice.observeEvent(viewLifecycleOwner) {
            binding.inputJobEditionHitDice.error = getString(it.string)
        }
    }

    private fun observeOnSaveEvent() {
        viewModel.onSaveEvent.observeEvent(viewLifecycleOwner) {
            binding.run {
                viewModel.save(
                    inputJobEditionName.text.toString(),
                    inputJobEditionHitDice.text.toString(),
                    inputJobEditionFeature.text.toString(),
                    inputJobEditionSaveThrow.text.toString(),
                )
            }
        }
    }

    private fun observeOnCloseEvent() {
        viewModel.onCloseEvent.observeEvent(viewLifecycleOwner) {
            if (it) {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun setupToolBarName(job: Job) {
        binding.toolbarJobEdition.title = job.jobName
    }
}