package com.ebt.finance.features.login.domain.use_case

import android.util.Log
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.login.data.dto.toLoginData
import com.ebt.finance.features.login.domain.models.Login
import com.ebt.finance.features.login.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val repository: LoginRepository,
) {
    operator fun  invoke(email: String, password: String): Flow<Resource<Login>> = flow{
        try {
            emit(Resource.Loading())
            val response = repository.postLogin(email, password)
            response.fold(
                ifLeft = {
                    Log.d("api", "invoke: isleft")
                    emit(Resource.Error(message = it.toFailed().message ?: "somthing went wrong"))
                },
                ifRight = {
                    Log.d("api", "invoke: ${it.toLoginData()}")
                    emit(Resource.Success(it.toLoginData()))
                }
            )
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Telah terjadi kesalahann"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "error"))
        }
    }
}