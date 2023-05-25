package com.ebt.finance.features.admin.tambah_data.domain.use_cases

import android.util.Log
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.tambah_data.data.Dto.toTambahDataDetail
import com.ebt.finance.features.admin.tambah_data.domain.model.Tambah
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahDataBody
import com.ebt.finance.features.admin.tambah_data.domain.repositories.TambahDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostTambahPemasukan @Inject constructor(
    private val repository: TambahDataRepository
) {
    operator fun invoke(token: String, dataBody: TambahDataBody): Flow<Resource<Tambah>> = flow {
        emit(Resource.Loading())
        try {
            val result = repository.postPemasukan(token, dataBody)
            result.fold(
                ifLeft = {
                    emit(Resource.Error(it.toFailed().message ?: "something went wrong"))
                    Log.d("post pemasukan", "invoke: ${it.toFailed().message}")
                },
                ifRight = {
                    emit(Resource.Success(data = it.toTambahDataDetail()))
                }
            )
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        } catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        }
    }
}