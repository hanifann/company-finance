package com.ebt.finance.features.admin.pengeluaran_detail.presentation.state

import com.ebt.finance.features.admin.pengeluaran.domain.models.Pengeluaran

data class PengeluaranDetailState(
    val id: String = "",
    val isLoading: Boolean = false,
    val data: Pengeluaran = Pengeluaran(emptyList()),
    val error: String = ""
)
