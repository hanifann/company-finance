package com.ebt.finance.features.login.data.datasources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.login.data.dto.LoginDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginRemoteDataSource {
    @POST("/api/login")
    @FormUrlEncoded
    suspend fun postLogin(@Field("email") email: String,@Field("password") password: String): Either<FailedDto, LoginDto>
}