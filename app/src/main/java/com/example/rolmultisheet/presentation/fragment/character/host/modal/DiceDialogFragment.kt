package com.example.rolmultisheet.presentation.fragment.character.host.modal

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.DiceDialogFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class DiceDialogFragment : DialogFragment(R.layout.dice_dialog_fragment) {

    private val binding: DiceDialogFragmentBinding by viewBinding {
        DiceDialogFragmentBinding.bind(it)
    }

    private val viewModel: DiceDialogViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.dr_rounded_background)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        setupDiceViews()
        setupCancelButton()
        setupResetButton()
        setupThrowButton()
    }

    private fun setupDiceViews() {
        setupDiceViewOnClickListener(binding.labelDiceDialogD2, 2)
        setupDiceViewOnClickListener(binding.labelDiceDialogD3, 3)
        setupDiceViewOnClickListener(binding.labelDiceDialogD4, 4)
        setupDiceViewOnClickListener(binding.labelDiceDialogD6, 6)
        setupDiceViewOnClickListener(binding.labelDiceDialogD8, 8)
        setupDiceViewOnClickListener(binding.labelDiceDialogD10, 10)
        setupDiceViewOnClickListener(binding.labelDiceDialogD12, 12)
        setupDiceViewOnClickListener(binding.labelDiceDialogD20, 20)
        setupDiceViewOnClickListener(binding.labelDiceDialogD100, 100)
    }

    private fun setupCancelButton() {
        binding.buttonDiceDialogCancel.setOnClickListener { dismiss() }
    }

    private fun setupResetButton() {
        binding.buttonDiceDialogReset.setOnClickListener { viewModel.resetThrowValue() }
    }

    private fun setupThrowButton() {
        binding.buttonDiceDialogThrow.setOnClickListener { viewModel.getThrowValue() }
    }

    private fun observeViewModel() {
        observeCurrentDices()
        observeCurrentResult()
    }

    private fun setupDiceViewOnClickListener(textView: TextView, diceValue: Int) {
        textView.setOnClickListener { viewModel.addDice(diceValue) }
    }

    private fun observeCurrentDices() {
        viewModel.dices.observe(viewLifecycleOwner) {
            binding.labelDiceDialogCurrentDices.text = it
        }
    }

    private fun observeCurrentResult() {
        viewModel.throwValue.observe(viewLifecycleOwner) {
            binding.labelDiceDialogResult.text = it
        }
    }


    companion object {
        const val path = "/dice_dialog"
    }
}