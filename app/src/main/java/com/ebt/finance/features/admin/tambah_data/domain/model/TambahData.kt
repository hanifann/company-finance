package com.ebt.finance.features.admin.tambah_data.domain.model

data class TambahData(
    val keterangan: String,
    val tgl: String,
    val totalHarga: String,
    val bukti: String,
    val distributorId: String,
)

data class Tambah(
    val data: TambahData
)
