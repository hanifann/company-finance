package com.ebt.finance.features.admin.pemasukan.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pemasukan.data.dto.PemasukanDto

interface PemasukanRepository {
    suspend fun getPemasukan(token: String): Either<FailedDto, PemasukanDto>
}