package com.example.rolmultisheet.presentation.fragment.creation


import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.CreationFragmentBinding
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.presentation.util.adapter.NoFilteringAdapter
import com.example.rolmultisheet.presentation.util.event.observeEvent
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class CreationFragment : Fragment(R.layout.creation_fragment) {

    private val binding: CreationFragmentBinding by viewBinding {
        CreationFragmentBinding.bind(it)
    }

    private val viewModel: CreationViewModel by viewModels {
        CreationViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            ),
            this
        )
    }

    private val navController: NavController by lazy { findNavController() }

    private val raceAdapter by lazy {
        NoFilteringAdapter<Race>(
            requireContext(),
            mutableListOf(),
        )
    }

    private val jobAdapter by lazy {
        NoFilteringAdapter<Job>(
            requireContext(),
            mutableListOf(),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModelData()
        observeViewModelEvent()
    }

    private fun setupViews() {
        setupToolBar()
        setupDropDownMenus()
        setupFab()
    }

    private fun setupFab() {
        binding.fabCreationDice.setOnClickListener { viewModel.throwDices() }
    }

    private fun observeViewModelData() {
        observeCurrentRace()
        observeCurrentJob()
        observeCurrentDice()
    }

    private fun observeViewModelEvent() {
        observeOnInvalidNameEvent()
        observeOnInvalidStrength()
        observeOnInvalidDexterity()
        observeOnInvalidConstitution()
        observeOnInvalidIntelligence()
        observeOnInvalidWisdom()
        observeOnInvalidCharisma()
        observeOnCloseEvent()
    }

    private fun setupToolBar() {
        binding.tbCreation.run {
            setupWithNavController(navController)
            inflateMenu(R.menu.save_manu)
            setOnMenuItemClickListener {
                onMenuItemClick(it)
            }
        }
    }

    private fun setupDropDownMenus() {
        setupDropDownRaceMenu()
        setupDropDownJobMenu()
    }

    private fun observeCurrentRace() {
        viewModel.currentRace.observe(viewLifecycleOwner) { race ->
            binding.labelCreationVelocity.text =
                getString(R.string.creation_velocity_label, race.raceVelocity)

        }
    }

    private fun observeCurrentJob() {
        viewModel.currentJob.observe(viewLifecycleOwner) { job ->
            binding.labelCreationHitDice.text =
                getString(R.string.creation_hit_dice_label, job.jobHit)
            binding.labelCreationMainFeatures.text =
                getString(R.string.creation_main_feature_label, job.jobFeature)
            binding.labelCreationSaveThrows.text =
                getString(R.string.creation_save_throw_label, job.jobSaveThrow)
        }
    }

    private fun observeCurrentDice() {
        viewModel.currentDices.observe(viewLifecycleOwner) {
            binding.labelCreationCurrentThrow.text =
                getString(R.string.creation_save_throw_dice, it)
        }
    }

    private fun observeOnInvalidNameEvent() {
        viewModel.onInvalidName.observeEvent(viewLifecycleOwner) {
            binding.inputCreationName.error = getString(it.string)
        }
    }

    private fun observeOnInvalidStrength() {
        viewModel.onInvalidStrength.observeEvent(viewLifecycleOwner) {
            binding.inputCreationStrength.error = getString(it.string)
        }
    }

    private fun observeOnInvalidDexterity() {
        viewModel.onInvalidDexterity.observeEvent(viewLifecycleOwner) {
            binding.inputCreationDexterity.error = getString(it.string)
        }
    }

    private fun observeOnInvalidConstitution() {
        viewModel.onInvalidConstitution.observeEvent(viewLifecycleOwner) {
            binding.inputCreationConstitution.error = getString(it.string)
        }
    }

    private fun observeOnInvalidIntelligence() {
        viewModel.onInvalidIntelligence.observeEvent(viewLifecycleOwner) {
            binding.inputCreationIntelligence.error = getString(it.string)
        }
    }

    private fun observeOnInvalidWisdom() {
        viewModel.onInvalidWisdom.observeEvent(viewLifecycleOwner) {
            binding.inputCreationWisdom.error = getString(it.string)
        }
    }

    private fun observeOnInvalidCharisma() {
        viewModel.onInvalidCharisma.observeEvent(viewLifecycleOwner) {
            binding.inputCreationCharisma.error = getString(it.string)
        }
    }

    private fun observeOnCloseEvent() {
        viewModel.onClose.observeEvent(viewLifecycleOwner) {
            requireActivity().onBackPressed()
        }
    }

    private fun onMenuItemClick(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.menuSave -> onSave()
            else -> return false
        }
        return true
    }

    private fun setupDropDownRaceMenu() {
        binding.autoCompletedCreationRace.run {
            setAdapter(raceAdapter)
            setOnItemClickListener { _, _, position, _ ->
                viewModel.raceIndex.value = position
                println(viewModel.raceIndex.value)
            }
        }

        viewModel.raceList.observe(viewLifecycleOwner) {
            raceAdapter.clear()
            raceAdapter.addAll(it)
            binding.autoCompletedCreationRace.setText(
                viewModel.raceList.value!![viewModel.raceIndex.value!!].toString(),
                false
            )
        }
    }

    private fun setupDropDownJobMenu() {
        binding.autoCompletedCreationJob.run {
            setAdapter(jobAdapter)
            setOnItemClickListener { _, _, position, _ ->
                viewModel.jobIndex.value = position
            }
        }

        viewModel.jobList.observe(viewLifecycleOwner) {
            jobAdapter.clear()
            jobAdapter.addAll(it)
            binding.autoCompletedCreationJob.setText(
                viewModel.jobList.value!![viewModel.jobIndex.value!!].toString(), false
            )
        }
    }

    private fun onSave() {
        val name = getNullOrString(binding.inputCreationName.text)
        val strength = getNullOrString(binding.inputCreationStrength.text)
        val dexterity = getNullOrString(binding.inputCreationDexterity.text)
        val constitution = getNullOrString(binding.inputCreationConstitution.text)
        val intelligence = getNullOrString(binding.inputCreationIntelligence.text)
        val wisdom = getNullOrString(binding.inputCreationWisdom.text)
        val charisma = getNullOrString(binding.inputCreationCharisma.text)

        if (viewModel.validateFields(
                name,
                strength,
                dexterity,
                constitution,
                intelligence,
                wisdom,
                charisma,
            )
        ) {
            viewModel.save(
                name!!,
                strength!!,
                dexterity!!,
                constitution!!,
                intelligence!!,
                wisdom!!,
                charisma!!,
            )
        }
    }

    private fun getNullOrString(editable: Editable?): String? =
        if (editable.isNullOrBlank()) null else editable.toString()
}