package com.ebt.finance.features.pegawai.gaji.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.pegawai.gaji.data.dto.GajiDto

interface GajiRepository {
    suspend fun getListGaji(token: String, id: String): Either<FailedDto, GajiDto>
}