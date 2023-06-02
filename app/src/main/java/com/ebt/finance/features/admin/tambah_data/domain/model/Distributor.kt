package com.ebt.finance.features.admin.tambah_data.domain.model

data class Distributor(
    val data: List<DistributorData>
)

data class DistributorData(
    val id: String,
    val namaDistributor: String,
    val namaPenjab: String,
    val tlp: String,
    val areaCover: String,
    val alamat: String,
    val jenisPengeluaran: String
)
