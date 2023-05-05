package com.ebt.finance.common


import com.google.gson.annotations.SerializedName

data class FailedDto(
    @SerializedName("message")
    val message: String?
)

fun FailedDto.toFailed(): Failed {
    return Failed(
        message
    )
}