package com.example.rolmultisheet.domain.model.form.util

interface ModelValidator {

    fun validate(): Boolean

    fun validateIsNullOrEmpty(value: String?): Boolean = value.isNullOrBlank()
    fun validateIsInteger(value: String?): Boolean = value?.toIntOrNull() is Int
    fun validateIsDouble(value: String?): Boolean = value?.toDoubleOrNull() is Double
    fun validateIsBoolean(value: String?): Boolean = value?.toBoolean() ?: false
}