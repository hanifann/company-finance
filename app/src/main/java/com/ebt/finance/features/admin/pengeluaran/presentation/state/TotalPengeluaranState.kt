package com.ebt.finance.features.admin.pengeluaran.presentation.state

import com.ebt.finance.features.admin.pengeluaran.domain.models.TotalPengeluaran

data class TotalPengeluaranState(
    val data: TotalPengeluaran = TotalPengeluaran(emptyList()),
    val isLoading: Boolean = false,
    val error: String = ""
)
