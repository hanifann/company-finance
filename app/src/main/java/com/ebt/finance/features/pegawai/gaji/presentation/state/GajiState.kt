package com.ebt.finance.features.pegawai.gaji.presentation.state

import com.ebt.finance.features.pegawai.gaji.domain.models.Gaji

data class GajiState(
    val isLoading: Boolean = false,
    val gaji: Gaji = Gaji(emptyList()),
    val message: String = ""
)
