package com.example.rolmultisheet.domain.model.form

import com.example.rolmultisheet.domain.model.form.util.FormHitDiceException
import com.example.rolmultisheet.domain.model.form.util.FormNameException
import com.example.rolmultisheet.domain.model.form.util.ModelValidator

class JobFormValidator(
    private val name: String?,
    private val hitDice: String?,
    private val feature: String?,
    private val saveThrow: String?,
) : ModelValidator {


    override fun validate(): Boolean {
        if (validateIsNullOrEmpty(name)) {
            throw FormNameException("Invalid name")
        } else if (validateIsNullOrEmpty(hitDice)) {
            throw FormHitDiceException("Invalid hit dice")
        }

        return true
    }
}