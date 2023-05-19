package com.ebt.finance.features.admin.tambah_data.domain.model

data class TambahData(
    val jenisData: String,
    val keterangan: String,
    val tgl: String,
    val totalHarga: String,
    val bukti: String,
    val distributorId: String,
    val kategori: String
)
