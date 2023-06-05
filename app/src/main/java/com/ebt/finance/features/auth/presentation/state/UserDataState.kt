package com.ebt.finance.features.auth.presentation.state

import com.ebt.finance.features.login.domain.models.UserData

data class UserDataState(
    val isLoading: Boolean = false,
    val userData: UserData = UserData(
        0,
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    ),
    val message: String =""
)
