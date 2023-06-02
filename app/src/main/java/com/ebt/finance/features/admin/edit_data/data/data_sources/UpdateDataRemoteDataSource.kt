package com.ebt.finance.features.admin.edit_data.data.data_sources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
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
    @Multipart
    suspend fun putPengeluaran(
        @Part("jenis_pengeluaran") jenisPemasukan: RequestBody,
        @Part("keterangan") keterangan: RequestBody,
        @Part("tgl") tgl: RequestBody,
        @Part("total_pengeluaran") totalPengeluaran: RequestBody,
        @Part("jenis_pengeluaran_id") distributorId: RequestBody,
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Either<FailedDto, FailedDto>
}