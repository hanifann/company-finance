package com.ebt.finance.features.admin.pengeluaran_detail.data.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pengeluaran.data.dto.PengeluaranDataDto
import com.ebt.finance.features.admin.pengeluaran.data.dto.PengeluaranDto
import com.ebt.finance.features.admin.pengeluaran_detail.data.datasources.PengeluaranDetailRemoteDataSource
import com.ebt.finance.features.admin.pengeluaran_detail.domain.repositories.PengeluaranDetailRepository
import javax.inject.Inject

class PengeluaranDetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: PengeluaranDetailRemoteDataSource
): PengeluaranDetailRepository {
    override suspend fun getPengeluaranDetail(
        id: String,
        token: String
    ): Either<FailedDto, PengeluaranDto> {
        return remoteDataSource.getPengeluaranDetail(id, token)
    }

    override suspend fun deletePengeluaran(
        id: String,
        token: String
    ): Either<FailedDto, PengeluaranDataDto> {
        return remoteDataSource.deletePengeluaran(id, token)
    }
}