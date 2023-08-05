package com.ebt.finance.features.pegawai.gaji_detail.presentaion.states

import com.ebt.finance.features.pegawai.gaji.domain.models.GajiData

data class GajiDetailState(
    val isLoading: Boolean = false,
    val gaji: GajiData = GajiData(
        "",
        "",
        "",
    "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    ),
    val error: String = ""
)
