package com.ebt.finance.features.admin.pemasukan.data.dto


import com.ebt.finance.features.admin.pemasukan.domain.models.Pemasukan
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PemasukanDto(
    @JsonProperty("data")
    val `data`: List<PemasukanDataDto>
)

fun PemasukanDto.toPemasukan(): Pemasukan = Pemasukan(
    data.map { it.toPemasukanData() }
)