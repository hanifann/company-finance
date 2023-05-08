package com.ebt.finance.features.login.domain.models

data class UserData(
    val id: Int,
    val name: String,
    val email: String,
    val noIdentitas: String,
    val tempatLahir: String,
    val dob: String,
    val noRek: String,
    val roleId: String,
    val posisiId: String,
    val status: String,
    val domisili: String,
    val noTelp: String,
)
