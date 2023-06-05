package com.ebt.finance.features.admin.tambah_data.data.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.tambah_data.data.Dto.DistributorDto
import com.ebt.finance.features.admin.tambah_data.data.Dto.TambahPemasukaDto
import com.ebt.finance.features.admin.tambah_data.data.Dto.TambahPengeluaranDto
import com.ebt.finance.features.admin.tambah_data.data.datasources.TambahDataRemoteDataSource
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahDataBody
import com.ebt.finance.features.admin.tambah_data.domain.repositories.TambahDataRepository
import javax.inject.Inject

class TambahDataRepositoryImpl @Inject constructor(
    private val datasource: TambahDataRemoteDataSource
): TambahDataRepository {
    override suspend fun getDistributor(token: String): Either<FailedDto, DistributorDto> {
        return datasource.getDistributor(token)
    }

    override suspend fun postPemasukan(
        token: String,
        data: TambahDataBody
    ): Either<FailedDto, TambahPemasukaDto> {
        return datasource.postPemasukan(
            token = token,
            keterangan = data.keterangan,
            totalPemasukan = data.totalHarga,
            tgl = data.tgl,
            bukti = data.bukti,
            distributorId = data.distributorId
        )
    }

    override suspend fun postPengeluaran(
        token: String,
        data: TambahDataBody
    ): Either<FailedDto, TambahPengeluaranDto> {
        return datasource.postPengeluaran(
            token = token,
            keterangan = data.keterangan,
            totalPengeluaran = data.totalHarga,
            tgl = data.tgl,
            bukti = data.bukti,
            distributorId = data.distributorId
        )
    }

    override suspend fun getJenisPengeluaran(token: String): Either<FailedDto, DistributorDto> {
        return datasource.getJenisPengeluaran(token)
    }
}