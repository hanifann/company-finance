package com.ebt.finance.features.admin.edit_data.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData

interface UpdateDataRepository {
    suspend fun updatePemasukan(token: String, id: String, data: TambahData): Either<FailedDto, FailedDto>
    suspend fun updatePengeluaran(token: String, id: String, data: TambahData): Either<FailedDto, FailedDto>
}