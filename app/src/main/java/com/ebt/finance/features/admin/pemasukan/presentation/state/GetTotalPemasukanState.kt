package com.ebt.finance.features.admin.pemasukan.presentation.state

import com.ebt.finance.features.admin.pemasukan.domain.models.TotalPemasukan

data class GetTotalPemasukanState(
    val data: TotalPemasukan = TotalPemasukan(emptyList()),
    val isLoading: Boolean = false,
    val error: String = ""
)
