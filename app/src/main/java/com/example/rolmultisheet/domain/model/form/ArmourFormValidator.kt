package com.example.rolmultisheet.domain.model.form

import com.example.rolmultisheet.domain.model.form.util.*

class ArmourFormValidator(
    private val name: String?,
    private val armourClass: String?,
    private val armourMaxBonus: String?,
    private val armourRequiredMinStrength: String?,
    private val armourStealthDisadvantage: String?,
    private val price: String?,
    private val weight: String?,
    private val description: String?,
) : ModelValidator {


    override fun validate(): Boolean {
        if (validateIsNullOrEmpty(name)) {
            throw FormNameException("Invalid name")
        } else if (!validateIsInteger(armourClass) || armourClass!!.toInt() < 0) {
            throw FormArmourClassFormatException("Invalid armour class format")
        } else if (!validateIsInteger(armourMaxBonus) || armourMaxBonus!!.toInt() < 0) {
            throw FormArmourMaxBonusFormatException("Invalid armour max bonus format")
        } else if (!validateIsInteger(armourRequiredMinStrength) || armourRequiredMinStrength!!.toInt() < 0) {
            throw FormArmourRequiredMinStrengthFormatException("Invalid armour min strength required format")
        } else if (!validateIsBoolean(armourStealthDisadvantage)) {
            throw FormArmourStealthDisadvantageFormatException("Only boolean values are accepts")
        } else if (!validateIsDouble(price) || price!!.toDouble() < 0.0) {
            throw FormPriceFormatException("Invalid format or range")
        } else if (!validateIsDouble(weight) || weight!!.toDouble() < 0) {
            throw FormWeightFormatException("Invalid format or range")
        }

        return true
    }
}