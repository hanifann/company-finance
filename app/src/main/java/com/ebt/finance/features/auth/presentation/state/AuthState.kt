package com.ebt.finance.features.auth.presentation.state

data class AuthState(
    val isSuccess: Boolean? = null,
    val isLoading: Boolean = true,
    val data: String = "",
    val error: String = ""
)