package com.example.rolmultisheet.domain.model.form

import com.example.rolmultisheet.domain.model.form.util.FormDexterityException
import com.example.rolmultisheet.domain.model.form.util.FormNameException
import com.example.rolmultisheet.domain.model.form.util.FormStrengthException
import com.example.rolmultisheet.domain.model.form.util.ModelValidator

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
        when {
            validateIsNullOrEmpty(characterName) ->
                throw FormNameException("Name can not be empty")
            isNotNullAndPositive(characterStrength) ->
                throw FormStrengthException("Strength can not be empty and it must be positive")
            isNotNullAndPositive(characterDexterity) ->
                throw FormDexterityException("Dexterity can not be empty and it must be positive")
            isNotNullAndPositive(characterConstitution) ->
                throw FormDexterityException("Constitution can not be empty and it must be positive")
            isNotNullAndPositive(characterIntelligence) ->
                throw FormDexterityException("Intelligence can not be empty and it must be positive")
            isNotNullAndPositive(characterWisdom) ->
                throw FormDexterityException("Wisdom can not be empty and it must be positive")
            isNotNullAndPositive(characterCharisma) ->
                throw FormDexterityException("Charisma can not be empty and it must be positive")
            else -> true
        }
}