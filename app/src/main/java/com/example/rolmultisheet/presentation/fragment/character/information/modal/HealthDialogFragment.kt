package com.example.rolmultisheet.presentation.fragment.character.information.modal

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.HealthDialogFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class HealthDialogFragment : DialogFragment(R.layout.health_dialog_fragment) {

    private val binding: HealthDialogFragmentBinding by viewBinding {
        HealthDialogFragmentBinding.bind(it)
    }

    private val currentHealth: Int by lazy {
        requireArguments().getInt(requestCurrentHealth, 0)
    }

    private val maxHealth: Int by lazy {
        requireArguments().getInt(requestMaxHealth, 0)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.dr_rounded_background)
        setupViews()
    }

    private fun setupViews() {
        setupCurrentInput()
        setupMaxInput()
        setupCancelButton()
        setupSaveButton()
    }

    private fun setupCurrentInput() {
        binding.textInputHealthDialogCurrentLife.setText(currentHealth.toString())
    }

    private fun setupMaxInput() {
        binding.textInputHealthDialogMaxLife.setText(maxHealth.toString())
    }

    private fun setupCancelButton() {
        binding.buttonHealthDialogCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun setupSaveButton() {
        binding.buttonHealthDialogSave.setOnClickListener {
            setFragmentResult(requestKey, getHealthBundle())
            dismiss()
        }
    }

    private fun getHealthBundle(): Bundle {
        val currentLife = getValueOrZero(binding.textInputHealthDialogCurrentLife.text)
        val maxLife = getValueOrZero(binding.textInputHealthDialogMaxLife.text)

        return bundleOf(requestCurrentHealth to currentLife, requestMaxHealth to maxLife)
    }

    private fun getValueOrZero(editable: Editable?): Int =
        if (editable.isNullOrBlank()) 0 else editable.toString().toInt()


    companion object {
        const val path: String = "/health_dialog"
        const val requestKey: String = "healthDialogRequest"
        const val requestCurrentHealth: String = "healthDialogCurrent"
        const val requestMaxHealth: String = "healthDialogMax"

        fun newInstance(currentLife: Int, maxLife: Int): HealthDialogFragment =
            HealthDialogFragment().apply {
                arguments =
                    bundleOf(requestCurrentHealth to currentLife, requestMaxHealth to maxLife)
            }
    }
}