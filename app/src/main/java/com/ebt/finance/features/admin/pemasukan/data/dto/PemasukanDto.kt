package com.ebt.finance.features.admin.pemasukan.data.dto


import com.ebt.finance.features.admin.pemasukan.domain.models.Pemasukan
import com.google.gson.annotations.SerializedName

data class PemasukanDto(
    @SerializedName("data")
    val `data`: List<PemasukanDataDto>
)

fun PemasukanDto.toPemasukan(): Pemasukan = Pemasukan(
    data.map { it.toPemasukanData() }
)