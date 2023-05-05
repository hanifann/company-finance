package com.ebt.finance.features.login.data.datasources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.login.data.dto.LoginDto
import com.ebt.finance.features.login.data.dto.UserDataDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginRemoteDataSource {
    @Headers("Accept: application/json")
    @POST("/api/login")
    @FormUrlEncoded
    suspend fun postLogin(@Field("email") email: String,@Field("password") password: String): Either<FailedDto, LoginDto>

    @Headers("Accept: application/json")
    @GET("/api/me")
    suspend fun getUserData(@Header("Authorization") token: String): Either<FailedDto, UserDataDto>

}