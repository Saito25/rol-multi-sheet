package com.example.rolmultisheet.presentation.fragment.weapon.edition

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
import com.example.rolmultisheet.databinding.WeaponEditionFragmentBinding
import com.example.rolmultisheet.domain.model.Weapon
import com.example.rolmultisheet.presentation.util.event.observeEvent
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class WeaponEditionFragment : Fragment(R.layout.weapon_edition_fragment) {

    private val binding: WeaponEditionFragmentBinding by viewBinding {
        WeaponEditionFragmentBinding.bind(it)
    }

    private val args: WeaponEditionFragmentArgs by navArgs()

    private val viewModel: WeaponEditionViewModel by viewModels {
        WeaponEditionViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            ),
            args.weaponId
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
        binding.toolbarWeaponEdition.run {
            setupWithNavController(navController)
            inflateMenu(R.menu.save_menu)
            setOnMenuItemClickListener { onMenuItemClick(it) }
            setupToolbarNameIfNoWeapon()
        }
    }

    private fun onMenuItemClick(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.menuSave -> validateTextFields()
            else -> return false
        }
        return true
    }

    private fun setupToolbarNameIfNoWeapon() {
        if (args.weaponId == 0L) {
            binding.toolbarWeaponEdition.title = getString(R.string.weapon_edition_toolbar_title)
        }
    }

    private fun validateTextFields() {
        println("ValidateTextFields")
        binding.run {
            viewModel.validateTextFields(
                inputWeaponEditionName.text.toString(),
                inputWeaponEditionDamage.text.toString(),
                getValueOrZero(inputWeaponEditionScope.text),
                inputWeaponEditionDamageType.text.toString(),
                checkWeaponEditTwoHand.isChecked.toString(),
                getValueOrZero(inputWeaponEditionPrice.text),
                getValueOrZero(inputWeaponEditionWeight.text),
                inputWeaponEditionDescription.text.toString(),
            )
        }

    }

    private fun getValueOrZero(editable: Editable?): String =
        if (editable.isNullOrBlank()) "0" else editable.toString()


    private fun observeViewModelEvent() {
        observeWeaponEvent()
        observeOnInvalidNameEvent()
        observeOnInvalidWeaponClass()
        observeOnSaveEvent()
        observeOnCloseEvent()
    }

    private fun observeOnInvalidWeaponClass() {
        viewModel.onInvalidDamage.observeEvent(viewLifecycleOwner) {
            binding.inputWeaponEditionDamage.error = getString(it.string)
        }
    }

    private fun observeWeaponEvent() {
        viewModel.weapon.observeEvent(viewLifecycleOwner) { weapon ->
            if (weapon != null) {
                setupToolBarName(weapon)
                fillTextFieldsWithWeaponInfo(weapon)
            }
        }
    }

    private fun fillTextFieldsWithWeaponInfo(weapon: Weapon) {
        binding.run {
            inputWeaponEditionName.setText(weapon.weaponName)
            inputWeaponEditionDamage.setText(weapon.weaponDamage)
            inputWeaponEditionScope.setText(weapon.weaponScope.toString())
            inputWeaponEditionDamageType.setText(weapon.weaponDameType)
            checkWeaponEditTwoHand.isChecked = weapon.weaponIsTwoHand
            inputWeaponEditionPrice.setText(weapon.weaponPrice.toString())
            inputWeaponEditionWeight.setText(weapon.weaponWeight.toString())
            inputWeaponEditionDescription.setText(weapon.weaponDescription)
        }
    }

    private fun observeOnInvalidNameEvent() {
        viewModel.onInvalidName.observeEvent(viewLifecycleOwner) {
            binding.inputWeaponEditionName.error = getString(it.string)
        }
    }

    private fun observeOnSaveEvent() {
        viewModel.onSaveEvent.observeEvent(viewLifecycleOwner) {
            binding.run {
                viewModel.save(
                    inputWeaponEditionName.text.toString(),
                    inputWeaponEditionDamage.text.toString(),
                    inputWeaponEditionScope.text.toString(),
                    inputWeaponEditionDamageType.text.toString(),
                    checkWeaponEditTwoHand.isChecked.toString(),
                    inputWeaponEditionPrice.text.toString(),
                    inputWeaponEditionWeight.text.toString(),
                    inputWeaponEditionDescription.text.toString()
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

    private fun setupToolBarName(weapon: Weapon) {
        binding.toolbarWeaponEdition.title = weapon.weaponName
    }
}