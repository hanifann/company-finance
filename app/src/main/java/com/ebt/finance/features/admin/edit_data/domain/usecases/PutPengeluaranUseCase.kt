package com.ebt.finance.features.admin.edit_data.domain.usecases

import android.util.Log
import com.ebt.finance.common.Failed
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.edit_data.domain.repositories.UpdateDataRepository
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PutPengeluaranUseCase @Inject constructor(
    private val repository: UpdateDataRepository
) {
    operator fun invoke(token: String, id: String, data: TambahData): Flow<Resource<Failed>> =
        flow {
            emit(Resource.Loading())
            try {
                val result = repository.updatePengeluaran(
                    token,
                    id,
                    data
                )
                result.fold(
                    ifLeft = {
                        Log.d("update_data", "invoke: ${it.toFailed().message}")
                        emit(Resource.Error(it.toFailed().message ?: "something went wrong"))
                    },
                    ifRight = {
                        emit(Resource.Success(it.toFailed()))
                    }
                )
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
            }
        }
}