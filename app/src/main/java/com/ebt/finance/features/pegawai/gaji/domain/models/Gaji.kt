package com.ebt.finance.features.pegawai.gaji.domain.models

data class Gaji (
    val data: List<GajiData>
)

data class GajiData (
    val id: String,
    val bulan: String,
    val gapok: String,
    val makanTransport: String,
    val lembur: String,
    val tunjangan: String,
    val insentiv: String,
    val pinjaman: String?,
    val jamkes: String,
    val name: String,
    val total: String
)
