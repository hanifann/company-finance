package com.ebt.finance.features.admin.pemasukan.data.datasources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pemasukan.data.dto.PemasukanDto
import com.ebt.finance.features.admin.pemasukan.data.dto.TotalPemasukanDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PemasukanRemoteDataSource {

    @Headers("Accept: application/json")
    @GET("/api/pemasukan")
    suspend fun getPemasukan(@Header("Authorization") token: String): Either<FailedDto, PemasukanDto>

    @Headers("Accept: application/json")
    @POST("/api/jumlah_pemasukan_by_bulan")
    @FormUrlEncoded
    suspend fun getTotalPemasukan(
        @Header("Authorization") token: String,
        @Field("tahun_bulan") tahunBulan: String
    ): Either<FailedDto, TotalPemasukanDto>
}