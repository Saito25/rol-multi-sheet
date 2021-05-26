package com.example.rolmultisheet.domain.model.form.util

interface ModelValidator {

    fun validate(): Boolean

    fun validateIsNullOrEmpty(value: String?): Boolean = value.isNullOrBlank()
    fun validateIsInteger(value: String?): Boolean = value?.toIntOrNull() is Int
    fun validateIsDouble(value: String?): Boolean = value?.toDoubleOrNull() is Double
    fun isNotNullAndPositive(number: Int?): Boolean = number != null && number > 0
    fun validateIsBoolean(value: String?): Boolean = value?.toBoolean() is Boolean
}