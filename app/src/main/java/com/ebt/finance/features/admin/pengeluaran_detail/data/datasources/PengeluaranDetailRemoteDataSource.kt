package com.ebt.finance.features.admin.pengeluaran_detail.data.datasources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pengeluaran.data.dto.PengeluaranDataDto
import com.ebt.finance.features.admin.pengeluaran.data.dto.PengeluaranDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface PengeluaranDetailRemoteDataSource {

    @Headers("Accept: application/json")
    @GET("/api/get_pengeluaran_by_id/{id}")
    suspend fun getPengeluaranDetail(@Path("id") id: String, @Header("Authorization") token: String): Either<FailedDto, PengeluaranDto>

    @Headers("Accept: application/json")
    @DELETE("/api/delete_pengeluaran/{id}")
    suspend fun deletePengeluaran(@Path("id") id: String, @Header("Authorization") token: String): Either<FailedDto, PengeluaranDataDto>
}