package com.ebt.finance.features.admin.pengeluaran.domain.use_case

import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.pengeluaran.data.dto.toTotalPengeluaranDto
import com.ebt.finance.features.admin.pengeluaran.domain.models.TotalPengeluaran
import com.ebt.finance.features.admin.pengeluaran.domain.repositories.PengeluaranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTotalPengeluaranUseCase @Inject constructor(
    private val repository: PengeluaranRepository
) {
    operator fun  invoke(token: String, bulanTahun: String): Flow<Resource<TotalPengeluaran>> = flow {
        emit(Resource.Loading())
        try {
            val result = repository.getTotalPengeluaran(token, bulanTahun)
            result.fold(
                ifLeft = {
                    emit(Resource.Error(it.toFailed().message ?: "something went wrong"))
                },
                ifRight = {
                    emit(Resource.Success(it.toTotalPengeluaranDto()))
                }
            )
        } catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        }
    }
}