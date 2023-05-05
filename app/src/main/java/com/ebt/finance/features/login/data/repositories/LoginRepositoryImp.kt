package com.ebt.finance.features.login.data.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.login.data.datasources.LoginRemoteDataSource
import com.ebt.finance.features.login.data.dto.LoginDto
import com.ebt.finance.features.login.data.dto.UserDataDto
import com.ebt.finance.features.login.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource
): LoginRepository {

    override suspend fun postLogin(email: String, password: String): Either<FailedDto, LoginDto> {
        return remoteDataSource.postLogin(email, password)
    }

    override suspend fun getUserData(token: String): Either<FailedDto, UserDataDto> {
        return remoteDataSource.getUserData(token)
    }
}