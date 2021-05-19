package com.example.rolmultisheet.presentation.fragment.item.edition

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
import com.example.rolmultisheet.databinding.SpellEditionFragmentBinding
import com.example.rolmultisheet.domain.model.Spell
import com.example.rolmultisheet.presentation.fragment.spell.edition.SpellEditionFragmentArgs
import com.example.rolmultisheet.presentation.util.event.observeEvent
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class ItemEditionFragment : Fragment(R.layout.spell_edition_fragment) {

    private val binding: SpellEditionFragmentBinding by viewBinding {
        SpellEditionFragmentBinding.bind(it)
    }

    private val args: SpellEditionFragmentArgs by navArgs()

    private val viewModel: ItemEditionViewModel by viewModels {
        ItemEditionViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            ),
            args.spellId
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
        binding.toolbarSpellEdition.run {
            setupWithNavController(navController)
            inflateMenu(R.menu.save_manu)
            setOnMenuItemClickListener { onMenuItemClick(it) }
            setupToolbarNameIfNoSpell()
        }
    }

    private fun onMenuItemClick(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.menuSave -> validateTextFields()
            else -> return false
        }
        return true
    }

    private fun setupToolbarNameIfNoSpell() {
        if (args.spellId == 0L) {
            binding.toolbarSpellEdition.title = getString(R.string.spell_edition_toolbar_title)
        }
    }

    private fun validateTextFields() {
        binding.run {
            viewModel.validateTextFields(
                inputSpellEditionName.text.toString(),
                inputSpellEditionCastTime.text.toString(),
                inputSpellEditionDuration.text.toString(),
                inputSpellEditionScope.text.toString(),
                inputSpellEditionDescription.text.toString(),
            )
        }

    }


    private fun observeViewModelEvent() {
        observeSpellEvent()
        observeOnInvalidNameEvent()
        observeOnSaveEvent()
        observeOnCloseEvent()
    }

    private fun observeSpellEvent() {
        viewModel.spell.observeEvent(viewLifecycleOwner) { spell ->
            if (spell != null) {
                setupToolBarName(spell)
                fillTextFieldsWithSpellInfo(spell)
            }
        }
    }

    private fun fillTextFieldsWithSpellInfo(spell: Spell) {
        binding.run {
            inputSpellEditionName.setText(spell.spellName)
            if (spell.spellCastTime != null) {
                inputSpellEditionCastTime.setText(spell.spellCastTime)
            }
            if (spell.spellDuration != null) {
                inputSpellEditionDuration.setText(spell.spellDuration)
            }
            if (spell.spellScope != null) {
                inputSpellEditionScope.setText(spell.spellScope)
            }
            if (spell.spellDescription != null) {
                inputSpellEditionDescription.setText(spell.spellDescription)
            }
        }
    }

    private fun observeOnInvalidNameEvent() {
        viewModel.onInvalidName.observeEvent(viewLifecycleOwner) {
            binding.inputSpellEditionName.error = getString(it.string)
        }
    }

    private fun observeOnSaveEvent() {
        viewModel.onSaveEvent.observeEvent(viewLifecycleOwner) {
            binding.run {
                viewModel.save(
                    inputSpellEditionName.text.toString(),
                    inputSpellEditionCastTime.text.toString(),
                    inputSpellEditionDuration.text.toString(),
                    inputSpellEditionScope.text.toString(),
                    inputSpellEditionDescription.text.toString(),
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

    private fun setupToolBarName(spell: Spell) {
        binding.toolbarSpellEdition.title = spell.spellName
    }
}