package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TambahPengeluaranDataDto(
    @JsonProperty("bukti_pengeluaran")
    val buktiPengeluaran: String,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("jenis_pengeluaran_id")
    val jenisPengeluaranId: String,
    @JsonProperty("keterangan")
    val keterangan: String,
    @JsonProperty("tgl")
    val tgl: String,
    @JsonProperty("total_pengeluaran")
    val totalPengeluaran: String,
    @JsonProperty("updated_at")
    val updatedAt: String
)

fun TambahPengeluaranDataDto.toPengeluaranData(): TambahData = TambahData(
    keterangan,
    tgl,
    totalPengeluaran,
    buktiPengeluaran,
    jenisPengeluaranId,
)