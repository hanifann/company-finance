package com.ebt.finance.features.admin.tambah_data.data.Dto


import com.ebt.finance.features.admin.tambah_data.domain.model.Distributor
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DistributorDto(
    @JsonProperty("data")
    val `data`: List<DistributorDataDto>
)

fun DistributorDto.toDistributor(): Distributor = Distributor(
    data.map { it.toDistributorData() }
)