package com.ebt.finance.features.admin.tambah_data.domain.model

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class TambahDataBody(
    val jenisData: RequestBody,
    val keterangan: RequestBody,
    val tgl: RequestBody,
    val totalHarga: RequestBody,
    val bukti: MultipartBody.Part,
    val distributorId: RequestBody,
)
