package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData
import com.google.gson.annotations.SerializedName

data class TambahPengeluaranDataDto(
    @SerializedName("bukti_pengeluaran")
    val buktiPengeluaran: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jenis_pengeluaran_id")
    val jenisPengeluaranId: String,
    @SerializedName("keterangan")
    val keterangan: String,
    @SerializedName("tgl")
    val tgl: String,
    @SerializedName("total_pengeluaran")
    val totalPengeluaran: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

fun TambahPengeluaranDataDto.toPengeluaranData(): TambahData = TambahData(
    keterangan,
    tgl,
    totalPengeluaran,
    buktiPengeluaran,
    jenisPengeluaranId,
    keterangan
)