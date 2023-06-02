package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.Tambah
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TambahPemasukaDto(
    @JsonProperty("data")
    val `data`: TambahPemasukanData
)

fun TambahPemasukaDto.toTambahDataDetail(): Tambah = Tambah(
    data.toTambahData()
)