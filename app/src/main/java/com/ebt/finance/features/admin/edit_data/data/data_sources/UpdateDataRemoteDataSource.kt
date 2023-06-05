package com.ebt.finance.features.admin.edit_data.data.data_sources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path

interface UpdateDataRemoteDataSource {

    @Headers("Accept: application/json")
    @PUT("/api/update_pemasukan/{id}")
    @FormUrlEncoded
    suspend fun putPemasukan(
        @Field("jenis_pemasukan") jenisPemasukan: String,
        @Field("keterangan") keterangan: String,
        @Field("tgl") tgl: String,
        @Field("total_pemasukan") totalPemasukan: String,
        @Field("distributor_id") distributorId: String,
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Either<FailedDto, FailedDto>

    @Headers("Accept: application/json")
    @PUT("/api/update_pengeluaran/{id}")
    @FormUrlEncoded
    suspend fun putPengeluaran(
        @Field("jenis_pengeluaran") jenisPengeluaran: String,
        @Field("keterangan") keterangan: String,
        @Field("tgl") tgl: String,
        @Field("total_pengeluaran") totalPengeluaran: String,
        @Field("jenis_pengeluaran_id") distributorId: String,
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Either<FailedDto, FailedDto>
}