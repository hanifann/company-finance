package com.ebt.finance.features.admin.pemasukan_detail.data.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pemasukan.data.dto.PemasukanDto
import com.ebt.finance.features.admin.pemasukan_detail.data.data_source.PemasukanDetailRemoteDataSource
import com.ebt.finance.features.admin.pemasukan_detail.domain.repositories.PemasukanDetailRepository
import javax.inject.Inject

class PemasukanDetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: PemasukanDetailRemoteDataSource
): PemasukanDetailRepository {
    override suspend fun getPemasukanDetail(
        id: String,
        token: String
    ): Either<FailedDto, PemasukanDto> {
        return remoteDataSource.getPemasukanDetail(id, token)
    }
}