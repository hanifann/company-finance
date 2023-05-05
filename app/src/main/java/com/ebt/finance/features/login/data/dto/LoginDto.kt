package com.ebt.finance.features.login.data.dto


import com.ebt.finance.features.login.domain.models.Login
import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName("data")
    val `data`: String,
)

fun LoginDto.toLoginData(): Login {
    return Login(
        data = data
    )
}