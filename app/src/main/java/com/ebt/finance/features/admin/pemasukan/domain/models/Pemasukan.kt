package com.ebt.finance.features.admin.pemasukan.domain.models

data class Pemasukan (
    val data: List<PemasukanData>
)

data class PemasukanData (
    val id: String,
    val namaDistributor: String,
    val keterangan: String,
    val tgl: String,
    val totalPemasukan: String,
    val buktiPemasukan: String,
    val updatedAt: String,
    val distributorId: String
)


