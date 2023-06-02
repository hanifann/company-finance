package com.ebt.finance.features.admin.tambah_data.domain.use_cases

import android.util.Log
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.tambah_data.data.Dto.toTambahPengeluaran
import com.ebt.finance.features.admin.tambah_data.domain.model.Tambah
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahDataBody
import com.ebt.finance.features.admin.tambah_data.domain.repositories.TambahDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostPengeluaranUseCase @Inject constructor(
    private val repository: TambahDataRepository
) {

    operator fun invoke(token: String, data: TambahDataBody): Flow<Resource<Tambah>> = flow {
        try {
            val result = repository.postPengeluaran(token = token, data = data)
            result.fold(
                ifLeft = {
                    Log.e("pengeluaran", "invoke: ${it.toFailed().message}")
                    emit(Resource.Error(it.toFailed().message ?: "something went wrong"))
                },
                ifRight = {
                    emit(Resource.Success(data = it.toTambahPengeluaran()))
                }
            )
        } catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        }
    }
}