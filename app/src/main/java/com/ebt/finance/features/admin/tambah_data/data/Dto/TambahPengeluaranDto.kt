package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.Tambah
import com.google.gson.annotations.SerializedName

data class TambahPengeluaranDto(
    @SerializedName("data")
    val `data`: TambahPengeluaranDataDto,
)

fun TambahPengeluaranDto.toTambahPengeluaran(): Tambah = Tambah(
    data.toPengeluaranData()
)