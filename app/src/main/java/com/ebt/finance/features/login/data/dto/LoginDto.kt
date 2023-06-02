package com.ebt.finance.features.login.data.dto


import com.ebt.finance.features.login.domain.models.Login
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginDto(
    @JsonProperty("data")
    val `data`: String,
)

fun LoginDto.toLoginData(): Login {
    return Login(
        data = data
    )
}