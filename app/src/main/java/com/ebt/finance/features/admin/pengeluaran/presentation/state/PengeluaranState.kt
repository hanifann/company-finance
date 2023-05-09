package com.ebt.finance.features.admin.pengeluaran.presentation.state

import com.ebt.finance.features.admin.pengeluaran.domain.models.Pengeluaran

data class PengeluaranState(
    val isLoading: Boolean = false,
    val data: Pengeluaran = Pengeluaran(emptyList()),
    val error: String = ""
)
