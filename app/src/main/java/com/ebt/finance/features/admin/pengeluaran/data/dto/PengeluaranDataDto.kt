package com.ebt.finance.features.admin.pengeluaran.data.dto


import com.ebt.finance.features.admin.pengeluaran.domain.models.PengeluaranData
import com.google.gson.annotations.SerializedName

data class PengeluaranDataDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("jenis_pengeluaran")
    val jenisPengeluaran: String?,
    @SerializedName("jenis_pengeluaran_id")
    val jenisPengeluaranId: String,
    @SerializedName("keterangan")
    val keterangan: String,
    @SerializedName("total_pengeluaran")
    val totalPengeluaran: String,
    @SerializedName("tgl")
    val tgl: String,
    @SerializedName("bukti_pengeluaran")
    val buktiPengeluaran: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("created_at")
    val createdAt: String
)

fun PengeluaranDataDto.toPengeluaranData(): PengeluaranData = PengeluaranData(
    id,
    jenisPengeluaran ?: "",
    jenisPengeluaranId,
    keterangan,
    totalPengeluaran,
    tgl,
    buktiPengeluaran,
    updatedAt,
    createdAt
)