package com.ebt.finance.features.admin.pengeluaran.data.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pengeluaran.data.datasources.PengeluaranRemoteDataSource
import com.ebt.finance.features.admin.pengeluaran.data.dto.PengeluaranDto
import com.ebt.finance.features.admin.pengeluaran.data.dto.TotalPengeluaranDto
import com.ebt.finance.features.admin.pengeluaran.domain.repositories.PengeluaranRepository
import javax.inject.Inject

class PengeluaranRepositoryImpl @Inject constructor(
    private val remoteDataSource: PengeluaranRemoteDataSource
): PengeluaranRepository {
    override suspend fun getPengeluaran(token: String): Either<FailedDto, PengeluaranDto> {
        return remoteDataSource.getPengeluaran(token)
    }

    override suspend fun getTotalPengeluaran(
        token: String,
        bulanTahun: String
    ): Either<FailedDto, TotalPengeluaranDto> {
        return  remoteDataSource.getTotalPengeluaran(token, bulanTahun)
    }
}