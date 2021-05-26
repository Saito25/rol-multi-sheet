package com.example.rolmultisheet.domain.model.form

import com.example.rolmultisheet.domain.model.form.util.*

class CharacterFormValidator(
    private val characterName: String?,
    private val characterStrength: Int?,
    private val characterDexterity: Int?,
    private val characterConstitution: Int?,
    private val characterIntelligence: Int?,
    private val characterWisdom: Int?,
    private val characterCharisma: Int?,
) : ModelValidator {

    override fun validate(): Boolean =
        if (validateIsNullOrEmpty(characterName)) {
            throw FormNameException("Name can not be empty")
        } else if (isNotNullAndPositive(characterStrength)) {
            throw FormStrengthException("Strength can not be empty and it must be positive")
        } else if (isNotNullAndPositive(characterDexterity)) {
            throw FormDexterityException("Dexterity can not be empty and it must be positive")
        } else if (isNotNullAndPositive(characterConstitution)) {
            throw FormConstitutionException("Constitution can not be empty and it must be positive")
        } else if (isNotNullAndPositive(characterIntelligence)) {
            throw FormIntelligenceException("Intelligence can not be empty and it must be positive")
        } else if (isNotNullAndPositive(characterWisdom)) {
            throw FormWisdomException("Wisdom can not be empty and it must be positive")
        } else if (isNotNullAndPositive(characterCharisma)) {
            throw FormCharismaException("Charisma can not be empty and it must be positive")
        } else {
            true
        }
}