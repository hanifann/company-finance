package com.ebt.finance.features.admin.pengeluaran_detail.domain.use_case

import android.util.Log
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.pengeluaran.data.dto.toPengeluaran
import com.ebt.finance.features.admin.pengeluaran.domain.models.Pengeluaran
import com.ebt.finance.features.admin.pengeluaran_detail.domain.repositories.PengeluaranDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPengeluaranDetailUseCase @Inject constructor(
    private val repository: PengeluaranDetailRepository
) {

    operator fun invoke(id: String, token: String): Flow<Resource<Pengeluaran>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getPengeluaranDetail(id, token)
            result.fold(
                ifLeft = {
                    emit(Resource.Error(it.toFailed().message ?: "something went wrong"))
                    Log.d("expanse_detail", "invoke: ${it.toFailed().message}")
                },
                ifRight = {
                    emit(Resource.Success(data = it.toPengeluaran()))
                }
            )
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        }
    }
}