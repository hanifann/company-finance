package com.ebt.finance.features.admin.pengeluaran_detail.domain.use_case

import android.util.Log
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.pengeluaran.data.dto.toPengeluaranData
import com.ebt.finance.features.admin.pengeluaran.domain.models.PengeluaranData
import com.ebt.finance.features.admin.pengeluaran_detail.domain.repositories.PengeluaranDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeletePengeluaranUseCase @Inject constructor(
    private val repository: PengeluaranDetailRepository
) {

    operator fun invoke(id: String, token: String): Flow<Resource<PengeluaranData>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.deletePengeluaran(id, token)
            result.fold(
                ifLeft = {
                    Log.e("pengeluaran", "invoke: ${it.toFailed().message}", )
                    emit(Resource.Error(message = it.toFailed().message ?: "something went wrong"))
                },
                ifRight = {
                    emit(Resource.Success(data = it.toPengeluaranData()))
                }
            )
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "something went wrong"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "something went wrong"))
        }
    }
}