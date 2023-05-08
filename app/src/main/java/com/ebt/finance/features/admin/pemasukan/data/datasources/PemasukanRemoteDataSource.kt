package com.ebt.finance.features.admin.pemasukan.data.datasources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pemasukan.data.dto.PemasukanDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface PemasukanRemoteDataSource {

    @Headers("Accept: application/json")
    @GET("/api/pemasukan")
    suspend fun getPemasukan(@Header("Authorization") token: String): Either<FailedDto, PemasukanDto>
}