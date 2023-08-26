package com.ebt.finance.features.admin.pemasukan.data.dto

import com.ebt.finance.features.admin.pemasukan.domain.models.TotalPemasukanData
import com.fasterxml.jackson.annotation.JsonProperty

data class TotalPemasukanDataDto(
    @JsonProperty("jumlah_pemasukan")
    val jumlahPemasukan: String
)

fun TotalPemasukanDataDto.toTotalPemasukanData(): TotalPemasukanData = TotalPemasukanData(
    jumlah_pemasukan = jumlahPemasukan
)
