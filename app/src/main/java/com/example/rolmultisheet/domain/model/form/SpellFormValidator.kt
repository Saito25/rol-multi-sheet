package com.example.rolmultisheet.domain.model.form

import com.example.rolmultisheet.domain.model.form.util.FormNameException
import com.example.rolmultisheet.domain.model.form.util.ModelValidator

class SpellFormValidator(
    private val name: String?,
    private val castTime: String?,
    private val duration: String?,
    private val scope: String?,
    private val description: String?,
) : ModelValidator {


    override fun validate(): Boolean {
        if (validateIsNullOrEmpty(name)) {
            throw FormNameException("Invalid name")
        }

        return true
    }
}