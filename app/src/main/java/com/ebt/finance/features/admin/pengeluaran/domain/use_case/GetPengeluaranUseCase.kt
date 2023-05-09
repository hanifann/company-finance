package com.ebt.finance.features.admin.pengeluaran.domain.use_case

import android.util.Log
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.pengeluaran.data.dto.toPengeluaran
import com.ebt.finance.features.admin.pengeluaran.domain.models.Pengeluaran
import com.ebt.finance.features.admin.pengeluaran.domain.repositories.PengeluaranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPengeluaranUseCase @Inject constructor(
    private val repository: PengeluaranRepository
) {

    operator fun invoke(token: String): Flow<Resource<Pengeluaran>> = flow {
        emit(Resource.Loading())
        try {
            val result = repository.getPengeluaran(token)
            result.fold(
                ifLeft = {
                    Log.d("expanse", "invoke: ${it.toFailed().message}")
                    emit(Resource.Error(it.toFailed().message ?: "something went wrong"))
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