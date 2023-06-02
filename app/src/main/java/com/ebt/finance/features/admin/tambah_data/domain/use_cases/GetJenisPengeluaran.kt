package com.ebt.finance.features.admin.tambah_data.domain.use_cases

import android.util.Log
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.tambah_data.data.Dto.toDistributor
import com.ebt.finance.features.admin.tambah_data.domain.model.Distributor
import com.ebt.finance.features.admin.tambah_data.domain.repositories.TambahDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetJenisPengeluaran @Inject constructor(
    private val repository: TambahDataRepository
) {

    operator fun invoke(token: String): Flow<Resource<Distributor>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getJenisPengeluaran(token)
            result.fold(
                ifLeft = {
                    Log.d("jenis_pengeluaran", "invoke: ${it.toFailed().message}")
                    emit(Resource.Error(it.toFailed().message ?: "something went wrong"))
                },
                ifRight = {
                    emit(Resource.Success(it.toDistributor()))
                }
            )
        } catch (e: IOException){
            Log.d("jenis_pengeluaran", "invoke: ${e.localizedMessage ?: "something went wrong"}")
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        } catch (e: HttpException){
            Log.d("jenis_pengeluaran", "invoke: ${e.localizedMessage ?: "something went wrong"}")
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        }
    }
}