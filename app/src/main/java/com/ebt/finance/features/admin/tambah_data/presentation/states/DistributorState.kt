package com.ebt.finance.features.admin.tambah_data.presentation.states

import com.ebt.finance.features.admin.tambah_data.domain.model.Distributor

data class DistributorState(
    val isLoading: Boolean = false,
    val distributor: Distributor = Distributor(emptyList()),
    val message: String = ""
)
