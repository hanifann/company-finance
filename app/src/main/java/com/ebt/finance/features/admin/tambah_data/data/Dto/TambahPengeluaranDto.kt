package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.Tambah
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TambahPengeluaranDto(
    @JsonProperty("data")
    val `data`: TambahPengeluaranDataDto,
)

fun TambahPengeluaranDto.toTambahPengeluaran(): Tambah = Tambah(
    data.toPengeluaranData()
)