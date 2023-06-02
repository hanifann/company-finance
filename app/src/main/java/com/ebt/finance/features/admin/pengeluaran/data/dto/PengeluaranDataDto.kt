package com.ebt.finance.features.admin.pengeluaran.data.dto


import com.ebt.finance.features.admin.pengeluaran.domain.models.PengeluaranData
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PengeluaranDataDto(
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("jenis_pengeluaran")
    val jenisPengeluaran: String?,
    @JsonProperty("jenis_pengeluaran_id")
    val jenisPengeluaranId: String?,
    @JsonProperty("keterangan")
    val keterangan: String?,
    @JsonProperty("total_pengeluaran")
    val totalPengeluaran: String?,
    @JsonProperty("tgl")
    val tgl: String?,
    @JsonProperty("bukti_pengeluaran")
    val buktiPengeluaran: String?,
    @JsonProperty("updated_at")
    val updatedAt: String?,
    @JsonProperty("created_at")
    val createdAt: String?
)

fun PengeluaranDataDto.toPengeluaranData(): PengeluaranData = PengeluaranData(
    id ?: "",
    jenisPengeluaran ?: "",
    jenisPengeluaranId ?: "",
    keterangan ?: "",
    totalPengeluaran ?: "",
    tgl ?: "",
    buktiPengeluaran ?: "",
    updatedAt ?: "",
    createdAt ?: ""
)