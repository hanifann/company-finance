package com.ebt.finance.features.admin.pemasukan.data.dto


import com.ebt.finance.features.admin.pemasukan.domain.models.PemasukanData
import com.google.gson.annotations.SerializedName

data class PemasukanDataDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("nama_distributor")
    val namaDistributor: String?,
    @SerializedName("keterangan")
    val keterangan: String?,
    @SerializedName("tgl")
    val tgl: String?,
    @SerializedName("total_pemasukan")
    val totalPemasukan: String?,
    @SerializedName("bukti_pemasukan")
    val buktiPemasukan: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)

fun PemasukanDataDto.toPemasukanData(): PemasukanData = PemasukanData(
    id ?: "",
    namaDistributor ?: "",
    keterangan ?: "",
    tgl ?: "",
    totalPemasukan ?: "",
    buktiPemasukan ?: "",
    updatedAt ?: ""
)