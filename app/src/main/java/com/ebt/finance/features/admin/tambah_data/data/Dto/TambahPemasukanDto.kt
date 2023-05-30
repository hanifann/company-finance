package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.Tambah
import com.google.gson.annotations.SerializedName

data class TambahPemasukaDto(
    @SerializedName("data")
    val `data`: TambahPemasukanData
)

fun TambahPemasukaDto.toTambahDataDetail(): Tambah = Tambah(
    data.toTambahData()
)