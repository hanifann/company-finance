package com.ebt.finance.features.pegawai.gaji.data.datasources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.pegawai.gaji.data.dto.GajiDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface GajiRemoteDataSource {

    @Headers("Accept: application/json")
    @GET("/api/penggajian/{id}")
    suspend fun getListGaji(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Either<FailedDto, GajiDto>
}