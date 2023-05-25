package com.ebt.finance.features.admin.tambah_data.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.tambah_data.data.Dto.DistributorDto
import com.ebt.finance.features.admin.tambah_data.data.Dto.TambahPemasukaDto
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahDataBody

interface TambahDataRepository {
    suspend fun getDistributor(token: String): Either<FailedDto, DistributorDto>
    suspend fun postPemasukan(token: String, data: TambahDataBody): Either<FailedDto, TambahPemasukaDto>
//    suspend fun postPengeluaran(token: String, data: TambahData): Either<FailedDto, Void>
}