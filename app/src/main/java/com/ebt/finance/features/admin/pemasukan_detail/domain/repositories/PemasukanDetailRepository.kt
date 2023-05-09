package com.ebt.finance.features.admin.pemasukan_detail.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pemasukan.data.dto.PemasukanDto

interface PemasukanDetailRepository {
    suspend fun getPemasukanDetail(id: String, token: String): Either<FailedDto, PemasukanDto>
}