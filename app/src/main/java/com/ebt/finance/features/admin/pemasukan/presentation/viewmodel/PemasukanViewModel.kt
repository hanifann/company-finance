package com.ebt.finance.features.admin.pemasukan.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.finance.R
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.common.Resource
import com.ebt.finance.features.admin.pemasukan.domain.use_cases.GetPemasukanUseCase
import com.ebt.finance.features.admin.pemasukan.presentation.state.GetPemasukanState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject

@HiltViewModel
class PemasukanViewModel @Inject constructor(
    private val useCase: GetPemasukanUseCase,
    private val dataStore: DataStoreRepository,
    private val formatter: NumberFormat
): ViewModel() {

    private val _state = mutableStateOf(GetPemasukanState())
    val state: State<GetPemasukanState> = _state

    private fun getPemasukan(token: String) {
        viewModelScope.launch {
            useCase
                .invoke(token)
                .collect {
                    when(it) {
                        is Resource.Success -> {
                            _state.value = GetPemasukanState(data = it.data!!)
                        }
                        is Resource.Loading -> {
                            _state.value = GetPemasukanState(isLoading = true)
                        }
                        is Resource.Error -> {
                            _state.value = GetPemasukanState(error = it.message.toString())
                        }
                    }
                }
        }
    }

    fun getToken() {
        viewModelScope.launch {
            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{
                if(it.isNotBlank()){
                    getPemasukan("Bearer $it")
                } else {
                    _state.value = GetPemasukanState(isLoading = true)
                }
            }
        }
    }

    fun formatCurrenty(value: Double): String {
        formatter.maximumFractionDigits = 0
        formatter.currency = Currency.getInstance("IDR")
        return formatter.format(value)
    }
}