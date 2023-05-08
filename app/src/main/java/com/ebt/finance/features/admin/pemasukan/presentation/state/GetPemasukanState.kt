package com.ebt.finance.features.admin.pemasukan.presentation.state

import com.ebt.finance.features.admin.pemasukan.domain.models.Pemasukan

data class GetPemasukanState(
    val data: Pemasukan = Pemasukan(emptyList()),
    val isLoading: Boolean = false,
    val error: String = ""
)
