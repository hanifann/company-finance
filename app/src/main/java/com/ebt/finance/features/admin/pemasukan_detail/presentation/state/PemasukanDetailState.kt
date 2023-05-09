package com.ebt.finance.features.admin.pemasukan_detail.presentation.state

import com.ebt.finance.features.admin.pemasukan.domain.models.Pemasukan

data class PemasukanDetailState(
    val isLoading: Boolean = false,
    val data: Pemasukan = Pemasukan(emptyList()),
    val id: String = "",
    val error: String = "",
)
