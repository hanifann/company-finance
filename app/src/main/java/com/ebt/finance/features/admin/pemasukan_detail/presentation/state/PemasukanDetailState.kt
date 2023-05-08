package com.ebt.finance.features.admin.pemasukan_detail.presentation.state

import com.ebt.finance.features.admin.pemasukan.domain.models.PemasukanData

data class PemasukanDetailState(
    val isLoading: Boolean = false,
    val data: PemasukanData = PemasukanData("", "", "", "", "", ""),
    val id: String = "",
    val error: String = ""
)
