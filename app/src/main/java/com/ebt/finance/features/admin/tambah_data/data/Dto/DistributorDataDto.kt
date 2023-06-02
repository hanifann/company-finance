package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.DistributorData
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DistributorDataDto(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("nama_distributor")
    val namaDistributor: String?,
    @JsonProperty("penjab")
    val namaPenjab: String?,
    @JsonProperty("tlp")
    val tlp: String?,
    @JsonProperty("area_cover")
    val areaCover: String?,
    @JsonProperty("alamat")
    val alamat: String?,
    @JsonProperty("jenis_pengeluaran")
    val jenisPengeluaran: String?
)

fun DistributorDataDto.toDistributorData(): DistributorData = DistributorData(
    id,
    namaDistributor ?: "",
    namaPenjab ?: "",
    tlp ?: "",
    areaCover ?: "",
    alamat ?: "",
    jenisPengeluaran ?: ""
)

