package com.example.rolmultisheet.domain.model.form

import com.example.rolmultisheet.domain.model.form.util.*

class WeaponFormValidator(
    private val weaponName: String?,
    private val weaponDamage: String?,
    private val weaponScope: String?,
    private val weaponDameType: String?,
    private val weaponIsTwoHand: String?,
    private val weaponPrice: String?,
    private val weaponWeight: String?,
    private val weaponDescription: String?,
) : ModelValidator {


    override fun validate(): Boolean {
        if (validateIsNullOrEmpty(weaponName)) {
            throw FormNameException("Invalid name")
        } else if (validateIsNullOrEmpty(weaponDamage)) {
            throw FormWeaponDamageFormatException("Invalid damage range")
        } else if (!validateIsInteger(weaponScope) || weaponScope!!.toInt() < 0) {
            throw FormWeaponScopeFormatException("Invalid scope range")
        } else if (!validateIsBoolean(weaponIsTwoHand)) {
            throw FormWeaponTwoHandFormatException("Only boolean values are accepts")
        } else if (!validateIsDouble(weaponPrice) || weaponPrice!!.toInt() < 0.0) {
            throw FormPriceFormatException("Invalid format or range")
        } else if (!validateIsDouble(weaponWeight) || weaponWeight!!.toDouble() < 0) {
            throw FormWeightFormatException("Invalid format or range")
        }

        return true
    }
}