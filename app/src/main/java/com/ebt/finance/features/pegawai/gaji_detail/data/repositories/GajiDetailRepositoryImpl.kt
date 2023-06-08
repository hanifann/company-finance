package com.ebt.finance.features.pegawai.gaji_detail.data.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.pegawai.gaji.data.dto.GajiDto
import com.ebt.finance.features.pegawai.gaji_detail.data.datasource.GajiDetailRemoteDataSource
import com.ebt.finance.features.pegawai.gaji_detail.domain.repositories.GajiDetailRepository
import javax.inject.Inject

class GajiDetailRepositoryImpl @Inject constructor(
    private val dataSource: GajiDetailRemoteDataSource
): GajiDetailRepository {
    override suspend fun getGajiDetail(
        token: String,
        userId: String,
        id: String
    ): Either<FailedDto, GajiDto> {
        return dataSource.getDetailGaji(
            token,
            userId,
            id
        )
    }
}