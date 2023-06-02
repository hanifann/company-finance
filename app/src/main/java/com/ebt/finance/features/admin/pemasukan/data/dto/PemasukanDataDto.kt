package com.ebt.finance.features.admin.pemasukan.data.dto


import com.ebt.finance.features.admin.pemasukan.domain.models.PemasukanData
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PemasukanDataDto(
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("nama_distributor")
    val namaDistributor: String?,
    @JsonProperty("keterangan")
    val keterangan: String?,
    @JsonProperty("tgl")
    val tgl: String?,
    @JsonProperty("total_pemasukan")
    val totalPemasukan: String?,
    @JsonProperty("bukti_pemasukan")
    val buktiPemasukan: String?,
    @JsonProperty("updated_at")
    val updatedAt: String?,
    @JsonProperty("distributor_id")
    val distributorId: String?
)

fun PemasukanDataDto.toPemasukanData(): PemasukanData = PemasukanData(
    id ?: "",
    namaDistributor ?: "",
    keterangan ?: "",
    tgl ?: "",
    totalPemasukan ?: "",
    buktiPemasukan ?: "",
    updatedAt ?: "",
    distributorId ?: ""
)