package com.ebt.finance.features.admin.pemasukan.domain.use_cases

import android.util.Log
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.pemasukan.data.dto.toPemasukan
import com.ebt.finance.features.admin.pemasukan.domain.models.Pemasukan
import com.ebt.finance.features.admin.pemasukan.domain.repositories.PemasukanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPemasukanUseCase @Inject constructor(
    private val repository: PemasukanRepository
) {

    operator fun invoke(token: String): Flow<Resource<Pemasukan>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getPemasukan(token)
            result.fold(
                ifLeft = {
                    emit(Resource.Error(message = it.toFailed().message ?: "something went wrong"))
                    Log.d("pemasukan", "invoke: ${it.toFailed().message}")
                },
                ifRight = {

                    emit(Resource.Success(data = it.toPemasukan()))
                }
            )
        } catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "something went wrong"))
        }
    }
}