package com.ebt.finance.features.pegawai.gaji.data.dto


import com.ebt.finance.features.pegawai.gaji.domain.models.GajiData
import com.fasterxml.jackson.annotation.JsonProperty

data class GajiDatadto(
    @JsonProperty("bulan")
    val bulan: String,
    @JsonProperty("gapok")
    val gapok: String,
    @JsonProperty("id")
    val id: String,
    @JsonProperty("insentiv")
    val insentiv: String,
    @JsonProperty("jamkes")
    val jamkes: String,
    @JsonProperty("lembur")
    val lembur: String,
    @JsonProperty("makan_transport")
    val makanTransport: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("pinjaman")
    val pinjaman: String,
    @JsonProperty("total")
    val total: String,
    @JsonProperty("tunjangan")
    val tunjangan: String
)

fun GajiDatadto.toGajiData(): GajiData = GajiData(
    id,
    bulan,
    gapok,
    makanTransport,
    lembur,
    tunjangan,
    insentiv,
    pinjaman,
    jamkes,
    name,
    total
)