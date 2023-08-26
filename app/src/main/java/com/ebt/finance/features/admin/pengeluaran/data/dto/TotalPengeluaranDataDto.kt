package com.ebt.finance.features.admin.pengeluaran.data.dto

import com.ebt.finance.features.admin.pengeluaran.domain.models.TotalPengeluaranData
import com.fasterxml.jackson.annotation.JsonProperty

data class TotalPengeluaranDataDto(
    @JsonProperty("jumlah_pengeluaran")
    val jumlahPengeluaran: String
)

fun TotalPengeluaranDataDto.toTotalPengeluaranDataDto(): TotalPengeluaranData = TotalPengeluaranData(
    jumlahPengeluaran
)
