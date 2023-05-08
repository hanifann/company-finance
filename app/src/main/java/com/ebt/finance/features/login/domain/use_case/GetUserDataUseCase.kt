package com.ebt.finance.features.login.domain.use_case

import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.login.data.dto.toUserData
import com.ebt.finance.features.login.domain.models.UserData
import com.ebt.finance.features.login.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    operator fun invoke(token: String): Flow<Resource<UserData>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getUserData(token)
            response.fold(
                ifLeft = {
                    emit(Resource.Error(message = it.toFailed().message ?: "something went wrong"))
                },
                ifRight = {
                    emit(Resource.Success(data = it.toUserData()))
                }
            )
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Telah terjadi kesalahann"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "error"))
        }
    }
}