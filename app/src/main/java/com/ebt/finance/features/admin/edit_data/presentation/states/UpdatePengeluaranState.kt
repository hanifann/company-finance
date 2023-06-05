package com.ebt.finance.features.admin.edit_data.presentation.states

import com.ebt.finance.features.admin.pemasukan.domain.models.PemasukanData

data class UpdatePengeluaranState(
    val isLoading: Boolean = false,
    val data: PemasukanData = PemasukanData(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        distributorId = ""
    ),
    var message: String = "",
    var isSuccess: Boolean = false
)
