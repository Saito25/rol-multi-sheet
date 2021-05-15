package com.example.rolmultisheet.presentation.fragment.race.edition

import android.os.Bundle
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
        observeViewModel()
    }

    private fun setupViews() {
        setupToolBar()
    }

    private fun setupToolBar() {
        binding.toolbarRaceEdition.run {
            setupWithNavController(navController)
        }
    }

    private fun observeViewModel() {
        viewModel.race.observe(viewLifecycleOwner) { race ->
            if (race != null) {
                printRaceInfo(race)
            }
        }
    }

    private fun printRaceInfo(race: Race) {
        binding.run {
            inputRaceEditionName.setText(race.raceName)
            inputRaceEditionVelocity.setText(race.raceVelocity.toString())
            inputRaceEditionHeight.setText(race.raceAvgHeight.toString())
            inputRaceEditionAge.setText(race.raceAvgAge.toString())
        }
    }
}