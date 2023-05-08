package com.ebt.finance.features.auth.domain.use_case

import androidx.datastore.preferences.core.stringPreferencesKey
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.common.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val repository: DataStoreRepository
){
    operator fun invoke(token: String): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getData(stringPreferencesKey(token))
            delay(500)
            response.collect{
                if(it.isNotBlank()){
                    emit(Resource.Success(it))
                } else {
                    emit(Resource.Error("empty"))
                }
            }
        } catch (_: Exception){

        }
    }
}