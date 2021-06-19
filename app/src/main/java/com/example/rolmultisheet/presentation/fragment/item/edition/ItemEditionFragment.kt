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
import com.example.rolmultisheet.databinding.ItemEditionFragmentBinding
import com.example.rolmultisheet.domain.model.Item
import com.example.rolmultisheet.presentation.util.event.observeEvent
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class ItemEditionFragment : Fragment(R.layout.item_edition_fragment) {

    private val binding: ItemEditionFragmentBinding by viewBinding {
        ItemEditionFragmentBinding.bind(it)
    }

    private val args: ItemEditionFragmentArgs by navArgs()

    private val viewModel: ItemEditionViewModel by viewModels {
        ItemEditionViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            ),
            args.itemId
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
        binding.toolbarItemEdition.run {
            setupWithNavController(navController)
            inflateMenu(R.menu.save_menu)
            setOnMenuItemClickListener { onMenuItemClick(it) }
            setupToolbarNameIfNoItem()
        }
    }

    private fun onMenuItemClick(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.menuSave -> validateTextFields()
            else -> return false
        }
        return true
    }

    private fun setupToolbarNameIfNoItem() {
        if (args.itemId == 0L) {
            binding.toolbarItemEdition.title = getString(R.string.item_edition_toolbar_title)
        }
    }

    private fun validateTextFields() {
        binding.run {
            viewModel.validateTextFields(
                inputItemEditionName.text.toString(),
                inputItemEditionPrice.text.toString(),
                inputItemEditionWeight.text.toString(),
                inputItemEditionDescription.text.toString(),
            )
        }

    }

    private fun observeViewModelEvent() {
        observeItemEvent()
        observeOnInvalidNameEvent()
        observeOnSaveEvent()
        observeOnCloseEvent()
    }

    private fun observeItemEvent() {
        viewModel.item.observeEvent(viewLifecycleOwner) { item ->
            if (item != null) {
                setupToolBarName(item)
                fillTextFieldsWithItemInfo(item)
            }
        }
    }

    private fun fillTextFieldsWithItemInfo(item: Item) {
        binding.run {
            inputItemEditionName.setText(item.itemName)
            inputItemEditionPrice.setText(item.itemPrice.toString())
            inputItemEditionWeight.setText(item.itemWeight.toString())
            inputItemEditionDescription.setText(item.itemDescription)
        }
    }

    private fun observeOnInvalidNameEvent() {
        viewModel.onInvalidName.observeEvent(viewLifecycleOwner) {
            binding.inputItemEditionName.error = getString(it.string)
        }
    }

    private fun observeOnSaveEvent() {
        viewModel.onSaveEvent.observeEvent(viewLifecycleOwner) {
            binding.run {
                viewModel.save(
                    inputItemEditionName.text.toString(),
                    inputItemEditionPrice.text.toString(),
                    inputItemEditionWeight.text.toString(),
                    inputItemEditionDescription.text.toString(),
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

    private fun setupToolBarName(item: Item) {
        binding.toolbarItemEdition.title = item.itemName
    }
}