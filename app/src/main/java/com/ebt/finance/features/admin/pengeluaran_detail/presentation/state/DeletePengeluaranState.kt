package com.ebt.finance.features.admin.pengeluaran_detail.presentation.state

data class DeletePengeluaranState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    var error: String = ""
)
