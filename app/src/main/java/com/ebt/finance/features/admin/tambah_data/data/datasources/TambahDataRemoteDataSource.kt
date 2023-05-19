package com.ebt.finance.features.admin.tambah_data.data.datasources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.tambah_data.data.Dto.DistributorDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface TambahDataRemoteDataSource {

    @Headers("Accept: application/json")
    @GET("/api/distributor")
    suspend fun getDistributor(@Header("Authorization") token: String): Either<FailedDto, DistributorDto>

    @Headers("Accept: application/json")
    @POST("/api/{tambah_pemasukan}")
    @FormUrlEncoded
    suspend fun postPemasukan(
        @Field("jenis_pemasukan") jenisPemasukan: String,
        @Field("keterangan") keterangan: String,
        @Field("tgl") tgl: String,
        @Field("total_pemasukan") totalPemasukan: String,
        @Field("bukti_pemasukan") bukti: String,
        token: String,
    ): Either<FailedDto, Void>

    @Headers("Accept: application/json")
    @POST("/api/{tambah_pemasukan}")
    @FormUrlEncoded
    suspend fun postPengeluaran(
        @Field("jenis_pengeluaran") jenisPengeluaran: String,
        @Field("keterangan") keterangan: String,
        @Field("tgl") tgl: String,
        @Field("total_pengeluaran") totalPengeluaran: String,
        @Field("bukti_pemasukan") bukti: String,
        token: String,
    ): Either<FailedDto, Void>
}