package com.ebt.finance.features.admin.pemasukan_detail.presentation.state

data class DeletePemasukanState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    var error: String = ""
)
