package com.ebt.finance.features.admin.pemasukan.domain.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.pemasukan.data.dto.PemasukanDto
import com.ebt.finance.features.admin.pemasukan.data.dto.TotalPemasukanDto

interface PemasukanRepository {
    suspend fun getPemasukan(token: String): Either<FailedDto, PemasukanDto>
    suspend fun getTotalPemasukan(token: String, tahunBulan: String): Either<FailedDto, TotalPemasukanDto>
}