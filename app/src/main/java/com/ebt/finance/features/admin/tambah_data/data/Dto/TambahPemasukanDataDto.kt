package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData
import com.google.gson.annotations.SerializedName

data class TambahPemasukanData(
    @SerializedName("distributor_id")
    val distributorId: String,
    @SerializedName("keterangan")
    val keterangan: String,
    @SerializedName("tgl")
    val tgl: String,
    @SerializedName("total_pemasukan")
    val totalPemasukan: String,
    @SerializedName("bukti_pemasukan")
    val buktiPemasukan: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int
)

fun TambahPemasukanData.toTambahData(): TambahData = TambahData(
    keterangan,
    tgl,
    totalPemasukan,
    buktiPemasukan,
    distributorId,
    keterangan
)
