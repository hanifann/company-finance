package com.ebt.finance.features.pegawai.gaji_detail.data.datasource

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.pegawai.gaji.data.dto.GajiDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface GajiDetailRemoteDataSource {

    @Headers("Accept: application/json")
    @GET("/api/penggajian_by_id/{userId}/{id}")
    suspend fun getDetailGaji(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Path("id") id: String
    ): Either<FailedDto, GajiDto>
}