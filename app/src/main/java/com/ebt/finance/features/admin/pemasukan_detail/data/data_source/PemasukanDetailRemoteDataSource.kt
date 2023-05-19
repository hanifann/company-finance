package com.ebt.finance.features.admin.pemasukan_detail.data.data_source

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pemasukan.data.dto.PemasukanDataDto
import com.ebt.finance.features.admin.pemasukan.data.dto.PemasukanDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface PemasukanDetailRemoteDataSource {

    @Headers("Accept: application/json")
    @GET("/api/get_pemasukan_by_id/{id}")
    suspend fun getPemasukanDetail(@Path("id") id: String, @Header("Authorization") token: String): Either<FailedDto, PemasukanDto>
    @Headers("Accept: application/json")
    @DELETE("/api/delete_pemasukan/{id}")
    suspend fun deletePemasukan(@Path("id") id: String, @Header("Authorization") token: String): Either<FailedDto, PemasukanDataDto>
}