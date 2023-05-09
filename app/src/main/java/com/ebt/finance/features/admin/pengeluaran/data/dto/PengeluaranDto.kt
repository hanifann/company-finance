package com.ebt.finance.features.admin.pengeluaran.data.dto


import com.ebt.finance.features.admin.pengeluaran.domain.models.Pengeluaran
import com.google.gson.annotations.SerializedName

data class PengeluaranDto(
    @SerializedName("data")
    val `data`: List<PengeluaranDataDto>
)

fun PengeluaranDto.toPengeluaran(): Pengeluaran = Pengeluaran(
    data.map { it.toPengeluaranData() }
)