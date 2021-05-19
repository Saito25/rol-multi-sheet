package com.example.rolmultisheet.domain.model.form.util

class ItemFormValidator(
    private val name: String?,
    private val price: Int?,
    private val weight: Double?,
    private val description: String?,
) : ModelValidator {


    override fun validate(): Boolean {
        if (validateIsNullOrEmpty(name)) {
            throw FormNameException("Invalid name")
        }

        return true
    }
}