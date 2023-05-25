package com.ebt.finance.features.admin.tambah_data.data.datasources

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.tambah_data.data.Dto.DistributorDto
import com.ebt.finance.features.admin.tambah_data.data.Dto.TambahPemasukaDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface TambahDataRemoteDataSource {

    @Headers("Accept: application/json")
    @GET("/api/distributor")
    suspend fun getDistributor(@Header("Authorization") token: String): Either<FailedDto, DistributorDto>

    @Headers("Accept: application/json")
    @POST("/api/tambah_pemasukan")
    @Multipart
    suspend fun postPemasukan(
        @Part("jenis_pemasukan") jenisPemasukan: RequestBody,
        @Part("keterangan") keterangan: RequestBody,
        @Part("tgl") tgl: RequestBody,
        @Part("total_pemasukan") totalPemasukan: RequestBody,
        @Part("distributor_id") distributorId: RequestBody,
        @Part bukti: MultipartBody.Part,
        @Header("Authorization") token: String
    ): Either<FailedDto, TambahPemasukaDto>

//    @Headers("Accept: application/json")
//    @POST("/api/{tambah_pengeluaran}")
//    @FormUrlEncoded
//    suspend fun postPengeluaran(
//        @Field("jenis_pengeluaran") jenisPengeluaran: String,
//        @Field("keterangan") keterangan: String,
//        @Field("tgl") tgl: String,
//        @Field("total_pengeluaran") totalPengeluaran: String,
//        @Field("bukti_pemasukan") bukti: String,
//        token: String,
//    ): Either<FailedDto, Void>
}