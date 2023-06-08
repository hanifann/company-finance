package com.ebt.finance.features.pegawai.gaji.data.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.pegawai.gaji.data.datasources.GajiRemoteDataSource
import com.ebt.finance.features.pegawai.gaji.data.dto.GajiDto
import com.ebt.finance.features.pegawai.gaji.domain.repositories.GajiRepository
import javax.inject.Inject

class GajiRepositoryImpl @Inject constructor(
    private val dataSource: GajiRemoteDataSource
): GajiRepository {
    override suspend fun getListGaji(token: String, id: String): Either<FailedDto, GajiDto> {
        return dataSource.getListGaji(
            token,
            id
        )
    }
}