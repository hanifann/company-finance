package com.ebt.finance.features.login.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.login.data.dto.LoginDto

interface LoginRepository {
    suspend fun postLogin(email: String, password: String): Either<FailedDto, LoginDto>
}