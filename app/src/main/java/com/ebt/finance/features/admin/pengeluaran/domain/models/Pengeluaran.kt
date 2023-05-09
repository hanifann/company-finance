package com.ebt.finance.features.admin.pengeluaran.domain.models

data class Pengeluaran (
    val data: List<PengeluaranData>
)

data class PengeluaranData (
    val id: String,
    val jenisPengeluaran: String,
    val jenisPengeluaranID: String,
    val keterangan: String,
    val totalPengeluaran: String,
    val tgl: String,
    val buktiPengeluaran: String,
    val updatedAt: String,
    val createdAt: String
)
