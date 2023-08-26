package com.ebt.finance.features.admin.pemasukan.domain.use_cases

import com.ebt.finance.common.Resource
import com.ebt.finance.common.toFailed
import com.ebt.finance.features.admin.pemasukan.data.dto.toTotalPemasukanDto
import com.ebt.finance.features.admin.pemasukan.domain.models.TotalPemasukan
import com.ebt.finance.features.admin.pemasukan.domain.repositories.PemasukanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTotalPemasukanUseCase @Inject constructor(
    private val repository: PemasukanRepository
) {
    operator fun invoke(token: String, tahunBulan: String): Flow<Resource<TotalPemasukan>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getTotalPemasukan(token, tahunBulan)
            result.fold(
                ifLeft = {
                    emit(Resource.Error(it.toFailed().message ?: "something went wrong"))
                },
                ifRight = {
                    emit(Resource.Success(it.toTotalPemasukanDto()))
                }
            )
        } catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "something went wrong"))
        }
    }
}