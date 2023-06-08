package com.ebt.finance.features.pegawai.gaji.data.dto


import com.ebt.finance.features.pegawai.gaji.domain.models.Gaji
import com.fasterxml.jackson.annotation.JsonProperty

data class GajiDto(
    @JsonProperty("data")
    val `data`: List<GajiDatadto>
)

fun GajiDto.toGaji(): Gaji = Gaji(
    data.map { it.toGajiData() }
)