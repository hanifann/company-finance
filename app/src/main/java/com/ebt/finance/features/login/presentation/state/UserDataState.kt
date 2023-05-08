package com.ebt.finance.features.login.presentation.state

import com.ebt.finance.features.login.domain.models.UserData

data class UserDataState(
    val isLoading: Boolean = false,
    val data: UserData? = null,
    var error: String = ""
)