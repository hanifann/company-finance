package com.ebt.finance.features.admin.pemasukan.domain.models

data class TotalPemasukan(
    val `data`: List<TotalPemasukanData>
)

data class TotalPemasukanData(
    val jumlah_pemasukan: String
)