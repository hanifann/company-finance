package com.ebt.finance.features.admin.pengeluaran.data.datasources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pengeluaran.data.dto.PengeluaranDto
import com.ebt.finance.features.admin.pengeluaran.data.dto.TotalPengeluaranDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PengeluaranRemoteDataSource {

    @Headers("Accept: application/json")
    @GET("/api/pengeluaran")
    suspend fun getPengeluaran(@Header("Authorization") token: String): Either<FailedDto, PengeluaranDto>

    @Headers("Accept: application/json")
    @POST("/api/jumlah_pengeluaran_by_bulan")
    @FormUrlEncoded
    suspend fun getTotalPengeluaran(
        @Header("Authorization") token: String,
        @Field("tahun_bulan") tahunBulan: String
    ): Either<FailedDto, TotalPengeluaranDto>
}