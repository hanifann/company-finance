package com.ebt.finance.features.admin.tambah_data.data.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.tambah_data.data.Dto.DistributorDto
import com.ebt.finance.features.admin.tambah_data.data.datasources.TambahDataRemoteDataSource
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData
import com.ebt.finance.features.admin.tambah_data.domain.repositories.TambahDataRepository
import javax.inject.Inject

class TambahDataRepositoryImpl @Inject constructor(
    private val datasource: TambahDataRemoteDataSource
): TambahDataRepository {
    override suspend fun getDistributor(token: String): Either<FailedDto, DistributorDto> {
        return datasource.getDistributor(token)
    }

    override suspend fun postPemasukan(token: String, data: TambahData): Either<FailedDto, Void> {
        return datasource.postPemasukan(
            jenisPemasukan = data.jenisData,
            keterangan = data.keterangan,
            tgl = data.tgl,
            totalPemasukan = data.totalHarga,
            bukti = data.bukti,
            token = token
        )
    }

    override suspend fun postPengeluaran(token: String, data: TambahData): Either<FailedDto, Void> {
        return datasource.postPemasukan(
            jenisPemasukan = data.jenisData,
            keterangan = data.keterangan,
            tgl = data.tgl,
            totalPemasukan = data.totalHarga,
            bukti = data.bukti,
            token = token
        )
    }
}