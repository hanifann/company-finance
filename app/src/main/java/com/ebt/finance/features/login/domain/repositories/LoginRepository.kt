package com.ebt.finance.features.login.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.login.data.dto.LoginDto
import com.ebt.finance.features.login.data.dto.UserDataDto

interface LoginRepository {
    suspend fun postLogin(email: String, password: String): Either<FailedDto, LoginDto>
    suspend fun getUserData(token: String): Either<FailedDto, UserDataDto>
}