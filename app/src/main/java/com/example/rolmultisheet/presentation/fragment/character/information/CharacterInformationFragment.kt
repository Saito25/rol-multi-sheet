package com.example.rolmultisheet.presentation.fragment.character.information

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.CharacterInformationFragmentBinding
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.presentation.fragment.character.information.modal.HealthDialogFragment
import com.example.rolmultisheet.presentation.util.fragment.findParentArgument
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import kotlin.math.floor

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListeners()
    }

    private fun setFragmentResultListeners() {
        setFragmentResultListener(HealthDialogFragment.requestKey) { _, bundle ->
            val currentHealth = bundle.getInt(HealthDialogFragment.requestCurrentHealth)
            val maxHealth = bundle.getInt(HealthDialogFragment.requestMaxHealth)
            viewModel.updateHealth(currentHealth, maxHealth)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModelData()
    }

    private fun setupViews() {
        setupGold()
        setupHealthImage()
        setupLessHealthButton()
        setupAddHealthButton()
        setupIncrementArmourClassButton()
        setupDecrementArmourClassButton()
        setupInputTexts()
    }

    private fun observeViewModelData() {
        observeCharacter()
        observeRace()
        observeJob()
    }

    private fun setupGold() {
        binding.editCharacterInformationGold.setOnFocusChangeListener { editText, hasFocus ->
            if (hasFocus.not()) {
                editText as EditText
                if (!editText.text.isNullOrBlank()) {
                    viewModel.updateGold(editText.text.toString().toInt())
                } else {
                    viewModel.updateGold(0)
                }
            }
        }
    }

    private fun setupHealthImage() {
        binding.imageCharacterInformationHearth.setOnClickListener {
            val currentHealth = viewModel.character.value!!.characterCurrentLife
            val maxHealth = viewModel.character.value!!.characterMaxLife
            HealthDialogFragment.newInstance(currentHealth, maxHealth)
                .show(parentFragmentManager, HealthDialogFragment.path)
        }
    }

    private fun setupLessHealthButton() {
        binding.imageCharacterInformationSubtract.setOnClickListener {
            viewModel.decrementHealth()
        }
    }

    private fun setupAddHealthButton() {
        binding.imageCharacterInformationSum.setOnClickListener {
            viewModel.incrementHealth()
        }
    }

    private fun setupDecrementArmourClassButton() {
        binding.imageCharacterInformationSubtractArmour.setOnClickListener {
            viewModel.decrementArmourClass()
        }
    }

    private fun setupIncrementArmourClassButton() {
        binding.imageCharacterInformationSumArmour.setOnClickListener {
            viewModel.incrementArmourClass()
        }
    }

    private fun setupInputTexts() {
        setupStrengthText()
        setupDexterityText()
        setupConstitutionText()
        setupIntelligenceText()
        setupWisdomText()
        setupCharismaText()
    }

    private fun setupStrengthText() {
        binding.inputTextCharacterInformationStrength.run {
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val strengthValue = getValueOrZero(text)
                    viewModel.updateStrength(strengthValue)
                }
            }
        }
    }

    private fun setupDexterityText() {
        binding.inputTextCharacterInformationDexterity.run {
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val dexterityValue = getValueOrZero(text)
                    viewModel.updateDexterity(dexterityValue)
                }
            }
        }
    }

    private fun setupConstitutionText() {
        binding.inputTextCharacterInformationConstitution.run {
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val constitutionValue = getValueOrZero(text)
                    viewModel.updateConstitution(constitutionValue)
                }
            }
        }
    }

    private fun setupIntelligenceText() {
        binding.inputTextCharacterInformationIntelligence.run {
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val intelligenceValue = getValueOrZero(text)
                    viewModel.updateIntelligence(intelligenceValue)
                }
            }
        }
    }

    private fun setupWisdomText() {
        binding.inputTextCharacterInformationWisdom.run {
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val wisdomValue = getValueOrZero(text)
                    viewModel.updateWisdom(wisdomValue)
                }
            }
        }
    }

    private fun setupCharismaText() {
        binding.inputTextCharacterInformationCharisma.run {
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val charismaValue = getValueOrZero(text)
                    viewModel.updateCharisma(charismaValue)
                }
            }
        }
    }

    private fun getValueOrZero(editable: Editable?): Int =
        if (editable.isNullOrBlank()) 0 else editable.toString().toInt()

    private fun observeCharacter() {
        viewModel.character.observe(viewLifecycleOwner) {
            if (it != null) {
                updateGold(it.characterGold)
                updateHealth(it.characterCurrentLife, it.characterMaxLife)
                updateArmourClass(it.characterArmourClass)
                updateAttributes(it)
                updateAttributesBonus(it)
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
        if (binding.editCharacterInformationGold.text.toString().toIntOrNull() != characterGold) {
            binding.editCharacterInformationGold.setText(characterGold.toString())
        }
    }

    private fun updateHealth(characterCurrentLife: Int, characterMaxLife: Int) {
        binding.run {
            labelCharacterInformationCurrentLife.text = characterCurrentLife.toString()
            labelCharacterInformationMaxLife.text = characterMaxLife.toString()
        }
    }

    private fun updateArmourClass(characterArmourClass: Int) {
        binding.run { labelCharacterInformationArmour.text = characterArmourClass.toString() }
    }

    private fun updateAttributes(character: Character) {
        binding.run {
            if (inputTextCharacterInformationStrength.text.toString()
                .toIntOrNull() != character.characterStrength
            ) {
                inputTextCharacterInformationStrength.setText(character.characterStrength.toString()) // ktlint-disable max-line-length
            }

            if (inputTextCharacterInformationDexterity.text.toString()
                .toIntOrNull() != character.characterDexterity
            ) {
                inputTextCharacterInformationDexterity.setText(character.characterDexterity.toString()) // ktlint-disable max-line-length
            }

            if (inputTextCharacterInformationConstitution.toString()
                .toIntOrNull() != character.characterConstitution
            ) {
                inputTextCharacterInformationConstitution.setText(character.characterConstitution.toString()) // ktlint-disable max-line-length
            }

            if (inputTextCharacterInformationIntelligence.text.toString()
                .toIntOrNull() != character.characterIntelligence
            ) {
                inputTextCharacterInformationIntelligence.setText(character.characterIntelligence.toString()) // ktlint-disable max-line-length
            }

            if (inputTextCharacterInformationWisdom.text.toString()
                .toIntOrNull() != character.characterWisdom
            ) {
                inputTextCharacterInformationWisdom.setText(character.characterWisdom.toString())
            }

            if (inputTextCharacterInformationCharisma.toString()
                .toIntOrNull() != character.characterCharisma
            ) {
                inputTextCharacterInformationCharisma.setText(character.characterCharisma.toString()) // ktlint-disable max-line-length
            }
        }
    }

    private fun updateAttributesBonus(character: Character) {
        binding.run {
            labelCharacterInformationStrengthBonus.text =
                getAttributeBonus(character.characterStrength)
            labelCharacterInformationDexterityBonus.text =
                getAttributeBonus(character.characterDexterity)
            labelCharacterInformationConstitutionBonus.text =
                getAttributeBonus(character.characterConstitution)
            labelCharacterInformationIntelligenceBonus.text =
                getAttributeBonus(character.characterIntelligence)
            labelCharacterInformationWisdomBonus.text =
                getAttributeBonus(character.characterWisdom)
            labelCharacterInformationCharismaBonus.text =
                getAttributeBonus(character.characterCharisma)
        }
    }

    private fun getAttributeBonus(attribute: Int): CharSequence {
        val value = floor(((attribute - 10.0) / 2)).toInt()
        return when {
            value > 0 -> getString(R.string.character_information_bonus, value)
            value < 0 -> value.toString()
            else -> "0"
        }
    }
}
