package com.ebt.finance.features.admin.pengeluaran.data.dto


import com.ebt.finance.features.admin.pengeluaran.domain.models.Pengeluaran
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PengeluaranDto(
    @JsonProperty("data")
    val `data`: List<PengeluaranDataDto>
)

fun PengeluaranDto.toPengeluaran(): Pengeluaran = Pengeluaran(
    data.map { it.toPengeluaranData() }
)