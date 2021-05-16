package com.example.rolmultisheet.domain.model.form.util

interface ModelValidator {

    fun validate(): Boolean

    fun validateIsNullOrEmpty(value: String?): Boolean = value.isNullOrBlank()
}