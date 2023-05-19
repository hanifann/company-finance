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

class GetDistributor @Inject constructor(
    private val repository: TambahDataRepository
) {

    operator fun invoke(token: String): Flow<Resource<Distributor>> = flow {
        emit(Resource.Loading())
        try {
            val result = repository.getDistributor(token)
            result.fold(
                ifLeft = {
                    Log.d("distributor", "invoke: ${it.toFailed().message}")
                    emit(Resource.Error(it.toFailed().message ?: "something went wrong"))
                },
                ifRight = {
                    emit(Resource.Success(it.toDistributor()))
                }
            )
        } catch (e: HttpException){
            Log.d("distributor", "invoke: ${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        } catch (e: IOException){
            Log.d("distributor", "invoke: ${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        }
    }
}