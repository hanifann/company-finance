package com.ebt.finance.features.admin.tambah_data.presentation.states

import com.ebt.finance.features.admin.tambah_data.domain.model.Distributor

data class JenisPengeluaranState(
    val isLoading: Boolean = false,
    val data: Distributor = Distributor(emptyList()),
    val message: String = ""
)
