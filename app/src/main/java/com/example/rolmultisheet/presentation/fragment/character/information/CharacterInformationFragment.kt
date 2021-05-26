package com.example.rolmultisheet.presentation.fragment.character.information

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.CharacterInformationFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.findParentArgument
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class CharacterInformationFragment : Fragment(R.layout.character_information_fragment) {

    private val binding: CharacterInformationFragmentBinding by viewBinding {
        CharacterInformationFragmentBinding.bind(it)
    }

    private val characterId: Long by lazy {
        findParentArgument()
    }

    private val viewModel: CharacterInformationViewModel by viewModels {
        CharacterInformationViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            ),
            characterId
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModelData()
    }

    private fun setupViews() {
        binding.editCharacterInformationGold.setOnFocusChangeListener { editText, hasFocus ->
            if (hasFocus.not()) {
                editText as EditText
                if (!editText.text.isNullOrBlank()) {
                    viewModel.updateGold(editText.text.toString().toInt())
                }
            }
        }
    }

    private fun observeViewModelData() {
        observeCharacter()
        observeRace()
        observeJob()
    }

    private fun observeCharacter() {
        viewModel.character.observe(viewLifecycleOwner) {
            if (it != null) {
                updateGold(it.characterGold)
                updateHealth(it.characterCurrentLife, it.characterMaxLife)
            }
        }

    }

    private fun observeRace() {
        viewModel.race.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.labelCharacterInformationRace.text = it.raceName
            }
        }
    }

    private fun observeJob() {
        viewModel.job.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.labelCharacterInformationClass.text = it.jobName
            }
        }
    }

    private fun updateGold(characterGold: Int) {
        binding.editCharacterInformationGold.setText(characterGold.toString())
    }

    private fun updateHealth(characterCurrentLife: Int, characterMaxLife: Int) {
        binding.run {
            labelCharacterInformationCurrentLife.text = characterCurrentLife.toString()
            labelCharacterInformationMaxLife.text = characterMaxLife.toString()
        }
    }
}