package com.ebt.finance.features.admin.pemasukan.data.dto

import com.ebt.finance.features.admin.pemasukan.domain.models.TotalPemasukan
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TotalPemasukanDto(
    @JsonProperty("data")
    val data: List<TotalPemasukanDataDto>
)

fun TotalPemasukanDto.toTotalPemasukanDto(): TotalPemasukan = TotalPemasukan(
    data = data.map { it.toTotalPemasukanData() }
)
