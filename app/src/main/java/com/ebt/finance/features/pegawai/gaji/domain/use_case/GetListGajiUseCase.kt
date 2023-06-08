package com.ebt.finance.features.pegawai.gaji.domain.use_case

import android.util.Log
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.pegawai.gaji.data.dto.toGaji
import com.ebt.finance.features.pegawai.gaji.domain.models.Gaji
import com.ebt.finance.features.pegawai.gaji.domain.repositories.GajiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetListGajiUseCase @Inject constructor(
    private val repository: GajiRepository
) {

    operator fun invoke(token: String, id: String): Flow<Resource<Gaji>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getListGaji(token, id)
            result.fold(
                ifLeft = {
                    Log.d("gaji", "invoke: ${it.toFailed().message}")
                    emit(Resource.Error(it.toFailed().message ?: "something went wrong"))
                },
                ifRight = {
                    emit(Resource.Success(it.toGaji()))
                }
            )
        } catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "something went wrong"))
        }
    }
}