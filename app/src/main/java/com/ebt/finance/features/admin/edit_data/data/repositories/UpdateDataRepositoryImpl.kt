package com.ebt.finance.features.admin.edit_data.data.repositories

import arrow.core.Either
import com.ebt.finance.common.FailedDto
import com.ebt.finance.features.admin.edit_data.data.data_sources.UpdateDataRemoteDataSource
import com.ebt.finance.features.admin.edit_data.domain.repositories.UpdateDataRepository
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData
import javax.inject.Inject

class UpdateDataRepositoryImpl @Inject constructor(
    private val remoteDataSource: UpdateDataRemoteDataSource
): UpdateDataRepository {
    override suspend fun updatePemasukan(token: String, id: String, data: TambahData)
    : Either<FailedDto, FailedDto> {
        return remoteDataSource.putPemasukan(
            token = token,
            id = id,
            jenisPemasukan = "",
            distributorId = data.distributorId,
            keterangan = data.keterangan,
            tgl = data.tgl,
            totalPemasukan = data.totalHarga
        )
    }

    override suspend fun updatePengeluaran(token: String, id: String, data: TambahData)
    : Either<FailedDto, FailedDto> {
        return remoteDataSource.putPengeluaran(
            token = token,
            id = id,
            distributorId = data.distributorId,
            keterangan = data.keterangan,
            tgl = data.tgl,
            totalPengeluaran = data.totalHarga,
            jenisPengeluaran = ""
        )
    }
}