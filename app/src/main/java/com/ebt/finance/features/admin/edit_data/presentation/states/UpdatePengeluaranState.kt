package com.ebt.finance.features.admin.edit_data.presentation.states

import com.ebt.finance.features.admin.pengeluaran.domain.models.PengeluaranData

data class UpdatePengeluaranState(
    val isLoading: Boolean = false,
    val data: PengeluaranData = PengeluaranData(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",

    ),
    var message: String = "",
    var isSuccess: Boolean = false
)
