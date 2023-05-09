package com.ebt.finance.features.admin.pengeluaran.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pengeluaran.data.dto.PengeluaranDto

interface PengeluaranRepository {
    suspend fun getPengeluaran(token: String): Either<FailedDto, PengeluaranDto>
}