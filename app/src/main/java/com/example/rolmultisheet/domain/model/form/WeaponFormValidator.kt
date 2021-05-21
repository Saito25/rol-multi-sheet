package com.example.rolmultisheet.domain.model.form

import com.example.rolmultisheet.domain.model.form.util.*

class WeaponFormValidator(
    val weaponName: String?,
    val weaponDamage: String?,
    val weaponScope: String?,
    val weaponDameType: String?,
    val weaponIsTwoHand: String?,
    val weaponPrice: String?,
    val weaponWeight: String?,
    val weaponDescription: String?,
) : ModelValidator {


    override fun validate(): Boolean {
        if (validateIsNullOrEmpty(weaponName)) {
            throw FormNameException("Invalid name")
        } else if (!validateIsInteger(weaponDamage) || weaponDamage!!.toInt() < 0) {
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