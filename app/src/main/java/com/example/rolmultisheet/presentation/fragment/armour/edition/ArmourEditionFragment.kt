package com.example.rolmultisheet.presentation.fragment.armour.edition

import android.os.Bundle
import android.text.Editable
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
import com.example.rolmultisheet.databinding.ArmourEditionFragmentBinding
import com.example.rolmultisheet.domain.model.Armour
import com.example.rolmultisheet.presentation.util.event.observeEvent
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class ArmourEditionFragment : Fragment(R.layout.armour_edition_fragment) {

    private val binding: ArmourEditionFragmentBinding by viewBinding {
        ArmourEditionFragmentBinding.bind(it)
    }

    private val args: ArmourEditionFragmentArgs by navArgs()

    private val viewModel: ArmourEditionViewModel by viewModels {
        ArmourEditionViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            ),
            args.armourId
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
        binding.toolbarArmourEdition.run {
            setupWithNavController(navController)
            inflateMenu(R.menu.save_manu)
            setOnMenuItemClickListener { onMenuItemClick(it) }
            setupToolbarNameIfNoArmour()
        }
    }

    private fun onMenuItemClick(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.menuSave -> validateTextFields()
            else -> return false
        }
        return true
    }

    private fun setupToolbarNameIfNoArmour() {
        if (args.armourId == 0L) {
            binding.toolbarArmourEdition.title = getString(R.string.armour_edition_toolbar_title)
        }
    }

    // TODO: Create method o extension function to complete this statement
    private fun validateTextFields() {
        binding.run {
            viewModel.validateTextFields(
                inputArmourEditionName.text.toString(),
                getValueOrZero(inputArmourEditionClass.text),
                getValueOrZero(inputArmourEditionBonus.text),
                getValueOrZero(inputArmourEditionStrength.text),
                checkArmourEditStealth.isChecked.toString(),
                getValueOrZero(inputArmourEditionPrice.text),
                getValueOrZero(inputArmourEditionWeight.text),
                inputArmourEditionDescription.text.toString(),
            )
        }

    }

    private fun getValueOrZero(editable: Editable?): String =
        if (editable.isNullOrBlank()) "0" else editable.toString()


    private fun observeViewModelEvent() {
        observeArmourEvent()
        observeOnInvalidNameEvent()
        observeOnInvalidArmourClass()
        observeOnSaveEvent()
        observeOnCloseEvent()
    }

    private fun observeOnInvalidArmourClass() {
        viewModel.onInvalidArmourClass.observeEvent(viewLifecycleOwner) {
            binding.inputArmourEditionClass.error = getString(it.string)
        }
    }

    private fun observeArmourEvent() {
        viewModel.armour.observeEvent(viewLifecycleOwner) { armour ->
            if (armour != null) {
                setupToolBarName(armour)
                fillTextFieldsWithArmourInfo(armour)
            }
        }
    }

    private fun fillTextFieldsWithArmourInfo(armour: Armour) {
        binding.run {
            inputArmourEditionName.setText(armour.armourName)
            inputArmourEditionClass.setText(armour.armourClass.toString())
            inputArmourEditionBonus.setText(armour.armourMaxBonus.toString())
            inputArmourEditionStrength.setText(armour.armourRequiredMinStrength.toString())
            checkArmourEditStealth.isChecked = armour.armourStealthDisadvantage
            inputArmourEditionPrice.setText(armour.armourPrice.toString())
            inputArmourEditionWeight.setText(armour.armourWeight.toString())
            inputArmourEditionDescription.setText(armour.armourDescription)
        }
    }

    private fun observeOnInvalidNameEvent() {
        viewModel.onInvalidName.observeEvent(viewLifecycleOwner) {
            binding.inputArmourEditionName.error = getString(it.string)
        }
    }

    private fun observeOnSaveEvent() {
        viewModel.onSaveEvent.observeEvent(viewLifecycleOwner) {
            binding.run {
                viewModel.save(
                    inputArmourEditionName.text.toString(),
                    inputArmourEditionClass.text.toString(),
                    inputArmourEditionBonus.text.toString(),
                    inputArmourEditionStrength.text.toString(),
                    checkArmourEditStealth.isChecked.toString(),
                    inputArmourEditionPrice.text.toString(),
                    inputArmourEditionWeight.text.toString(),
                    inputArmourEditionDescription.text.toString()
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

    private fun setupToolBarName(armour: Armour) {
        binding.toolbarArmourEdition.title = armour.armourName
    }
}