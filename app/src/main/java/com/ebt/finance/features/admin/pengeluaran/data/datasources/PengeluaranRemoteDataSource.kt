package com.ebt.finance.features.admin.pengeluaran.data.datasources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pengeluaran.data.dto.PengeluaranDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface PengeluaranRemoteDataSource {

    @Headers("Accept: application/json")
    @GET("/api/pengeluaran")
    suspend fun getPengeluaran(@Header("Authorization") token: String): Either<FailedDto, PengeluaranDto>
}