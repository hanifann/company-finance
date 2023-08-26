package com.ebt.finance.features.admin.pengeluaran.domain.models

data class TotalPengeluaran(
    val `data`: List<TotalPengeluaranData>
)

data class TotalPengeluaranData(
    val jumlah_pengeluaran: String
)