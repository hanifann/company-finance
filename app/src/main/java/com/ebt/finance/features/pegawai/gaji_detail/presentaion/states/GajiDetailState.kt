package com.ebt.finance.features.pegawai.gaji_detail.presentaion.states

import com.ebt.finance.features.pegawai.gaji.domain.models.Gaji

data class GajiDetailState(
    val isLoading: Boolean = false,
    val gaji: Gaji = Gaji(emptyList()),
    val error: String = ""
)
