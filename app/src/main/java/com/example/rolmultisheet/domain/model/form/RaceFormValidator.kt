package com.example.rolmultisheet.domain.model.form

import com.example.rolmultisheet.domain.model.form.util.FormNameException
import com.example.rolmultisheet.domain.model.form.util.ModelValidator

class RaceFormValidator(
    private val name: String,
    private val velocity: String,
    private val height: String,
    private val age: String,
) : ModelValidator {


    override fun validate(): Boolean {
        if (validateIsNullOrEmpty(name)) {
            throw FormNameException("Invalid name")
        }

        return true
    }
}