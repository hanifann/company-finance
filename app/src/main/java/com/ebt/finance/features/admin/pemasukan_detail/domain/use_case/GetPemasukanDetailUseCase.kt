package com.ebt.finance.features.admin.pemasukan_detail.domain.use_case

import android.util.Log
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.pemasukan.data.dto.toPemasukan
import com.ebt.finance.features.admin.pemasukan.domain.models.Pemasukan
import com.ebt.finance.features.admin.pemasukan_detail.domain.repositories.PemasukanDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPemasukanDetailUseCase @Inject constructor(
    private val repository: PemasukanDetailRepository
) {

    operator fun invoke(id: String, token: String): Flow<Resource<Pemasukan>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getPemasukanDetail(id, token)
            result.fold(
                ifLeft = {
                    emit(Resource.Error(it.toFailed().message ?: "something went wrong"))
                    Log.d("detail income", "invoke: ${it.toFailed().message}")
                },
                ifRight = {
                    emit(Resource.Success(data = it.toPemasukan()))
                    Log.d("detail income", "invoke: ${it.toPemasukan()}")
                }
            )
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        }
    }
}