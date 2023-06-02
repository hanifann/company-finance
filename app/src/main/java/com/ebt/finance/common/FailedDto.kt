package com.ebt.finance.common


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class FailedDto(
    @JsonProperty("message")
    val message: String?
)

fun FailedDto.toFailed(): Failed {
    return Failed(
        message
    )
}