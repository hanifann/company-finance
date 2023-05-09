package com.ebt.finance.features.admin.pengeluaran_detail.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pengeluaran.data.dto.PengeluaranDto

interface PengeluaranDetailRepository {
    suspend fun getPengeluaranDetail(id: String, token: String): Either<FailedDto, PengeluaranDto>
}