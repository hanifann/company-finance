package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TambahPemasukanData(
    @JsonProperty("distributor_id")
    val distributorId: String,
    @JsonProperty("keterangan")
    val keterangan: String,
    @JsonProperty("tgl")
    val tgl: String,
    @JsonProperty("total_pemasukan")
    val totalPemasukan: String,
    @JsonProperty("bukti_pemasukan")
    val buktiPemasukan: String,
    @JsonProperty("updated_at")
    val updatedAt: String,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("id")
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
