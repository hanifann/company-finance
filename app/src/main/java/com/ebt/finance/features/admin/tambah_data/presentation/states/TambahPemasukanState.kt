package com.ebt.finance.features.admin.tambah_data.presentation.states

import com.ebt.finance.features.admin.tambah_data.domain.model.Tambah
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData

data class TambahPemasukanState(
    val loading: Boolean = false,
    val data: Tambah = Tambah(TambahData(
        "",
        "",
        "",
        "",
        "",
    )),
    var message: String = "",
    var isSuccess: Boolean = false
)
