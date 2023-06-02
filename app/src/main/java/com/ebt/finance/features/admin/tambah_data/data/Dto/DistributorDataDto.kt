package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.DistributorData
import com.google.gson.annotations.SerializedName

data class DistributorDataDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("nama_distributor")
    val namaDistributor: String?,
    @SerializedName("penjab")
    val namaPenjab: String?,
    @SerializedName("tlp")
    val tlp: String?,
    @SerializedName("area_cover")
    val areaCover: String?,
    @SerializedName("alamat")
    val alamat: String?,
    @SerializedName("jenis_pengeluaran")
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

