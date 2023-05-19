package com.ebt.finance.features.admin.pemasukan_detail.domain.use_case

import android.util.Log
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.pemasukan.data.dto.toPemasukanData
import com.ebt.finance.features.admin.pemasukan.domain.models.PemasukanData
import com.ebt.finance.features.admin.pemasukan_detail.domain.repositories.PemasukanDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeletePemasukanUseCase @Inject constructor(
    private val repository: PemasukanDetailRepository
) {

    operator fun invoke(id: String, token: String): Flow<Resource<PemasukanData>> = flow {
        try {
            val result = repository.deletePemasukan(id, token)
            result.fold(
                ifLeft = {
                    emit(Resource.Error(it.toFailed().message ?: "Something went wrong"))
                    Log.d("pemasukan", "invoke: ${it.toFailed().message}")
                },
                ifRight = {
                    emit(Resource.Success(it.toPemasukanData()))
                }
            )
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        }
    }
}