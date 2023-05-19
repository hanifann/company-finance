package com.ebt.finance.features.admin.tambah_data.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.tambah_data.data.Dto.DistributorDto
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData

interface TambahDataRepository {
    suspend fun getDistributor(token: String): Either<FailedDto, DistributorDto>
    suspend fun postPemasukan(token: String, data: TambahData): Either<FailedDto, Void>
    suspend fun postPengeluaran(token: String, data: TambahData): Either<FailedDto, Void>
}