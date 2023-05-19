package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.Distributor
import com.google.gson.annotations.SerializedName

data class DistributorDto(
    @SerializedName("data")
    val `data`: List<DistributorDataDto>
)

fun DistributorDto.toDistributor(): Distributor = Distributor(
    data.map { it.toDistributorData() }
)