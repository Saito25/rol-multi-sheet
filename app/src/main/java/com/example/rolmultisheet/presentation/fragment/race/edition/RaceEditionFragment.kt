package com.example.rolmultisheet.presentation.fragment.race.edition

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
import com.example.rolmultisheet.databinding.RaceEditionFragmentBinding
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.presentation.util.event.observeEvent
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class RaceEditionFragment : Fragment(R.layout.race_edition_fragment) {

    private val binding: RaceEditionFragmentBinding by viewBinding {
        RaceEditionFragmentBinding.bind(it)
    }

    private val args: RaceEditionFragmentArgs by navArgs()

    private val viewModel: RaceEditionViewModel by viewModels {
        RaceEditionViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            ),
            args.raceId
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
        binding.toolbarRaceEdition.run {
            setupWithNavController(navController)
            inflateMenu(R.menu.save_manu)
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
        if (args.raceId == 0L) {
            binding.toolbarRaceEdition.title = getString(R.string.race_edition_toolbar_title)
        }
    }

    private fun validateTextFields() {
        binding.run {
            viewModel.validateTextFields(
                inputRaceEditionName.text.toString(),
                inputRaceEditionVelocity.text.toString(),
                inputRaceEditionHeight.text.toString(),
                inputRaceEditionAge.text.toString(),
            )
        }

    }


    private fun observeViewModelEvent() {
        observeRaceEvent()
        observeOnInvalidNameEvent()
        observeOnSaveEvent()
        observeOnCloseEvent()
    }

    private fun observeRaceEvent() {
        viewModel.race.observeEvent(viewLifecycleOwner) { race ->
            if (race != null) {
                setupToolBarName(race)
                fillTextFieldsWithRaceInfo(race)
            }
        }
    }

    private fun fillTextFieldsWithRaceInfo(race: Race) {
        binding.run {
            inputRaceEditionName.setText(race.raceName)
            if (race.raceVelocity != null) {
                inputRaceEditionVelocity.setText(race.raceVelocity.toString())
            }
            if (race.raceAvgHeight != null) {
                inputRaceEditionHeight.setText(race.raceAvgHeight.toString())
            }
            if (race.raceAvgAge != null) {
                inputRaceEditionAge.setText(race.raceAvgAge.toString())
            }
        }
    }

    private fun observeOnInvalidNameEvent() {
        viewModel.onInvalidName.observeEvent(viewLifecycleOwner) {
            binding.inputRaceEditionName.error = getString(it.string)
        }
    }

    private fun observeOnSaveEvent() {
        viewModel.onSaveEvent.observeEvent(viewLifecycleOwner) {
            binding.run {
                viewModel.save(
                    inputRaceEditionName.text.toString(),
                    inputRaceEditionVelocity.text.toString(),
                    inputRaceEditionHeight.text.toString(),
                    inputRaceEditionAge.text.toString(),
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

    private fun setupToolBarName(race: Race) {
        binding.toolbarRaceEdition.title = race.raceName
    }
}