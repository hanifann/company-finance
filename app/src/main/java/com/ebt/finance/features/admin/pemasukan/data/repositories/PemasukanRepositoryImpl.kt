package com.ebt.finance.features.admin.pemasukan.data.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pemasukan.data.datasources.PemasukanRemoteDataSource
import com.ebt.finance.features.admin.pemasukan.data.dto.PemasukanDto
import com.ebt.finance.features.admin.pemasukan.domain.repositories.PemasukanRepository
import javax.inject.Inject

class PemasukanRepositoryImpl @Inject constructor(
    private val remoteDataSource: PemasukanRemoteDataSource
): PemasukanRepository {
    override suspend fun getPemasukan(token: String): Either<FailedDto, PemasukanDto> {
        return remoteDataSource.getPemasukan(token)
    }
}