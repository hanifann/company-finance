package com.ebt.finance.features.login.presentation.state

import com.ebt.finance.features.login.domain.models.Login

data class LoginState(
    val isLoading: Boolean = false,
    val data: Login? = null,
    var error: String = ""
)
