package com.ebt.finance.features.pegawai.gaji_detail.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.pegawai.gaji.data.dto.GajiDto

interface GajiDetailRepository {

    suspend fun getGajiDetail(token: String, userId: String, id: String): Either<FailedDto, GajiDto>
}