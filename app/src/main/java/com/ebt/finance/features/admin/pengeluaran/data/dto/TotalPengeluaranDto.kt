package com.ebt.finance.features.admin.pengeluaran.data.dto

import com.ebt.finance.features.admin.pengeluaran.domain.models.TotalPengeluaran
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TotalPengeluaranDto(
    @JsonProperty("data")
    val data: List<TotalPengeluaranDataDto>
)

fun TotalPengeluaranDto.toTotalPengeluaranDto(): TotalPengeluaran = TotalPengeluaran(
    data = data.map { it.toTotalPengeluaranDataDto() }
)