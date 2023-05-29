package com.ebt.finance.features.admin.pengeluaran.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.finance.R
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.common.Resource
import com.ebt.finance.features.admin.pengeluaran.domain.use_case.GetPengeluaranUseCase
import com.ebt.finance.features.admin.pengeluaran.presentation.state.PengeluaranState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject

@HiltViewModel
class PengeluaranViewModel @Inject constructor(
    private val useCase: GetPengeluaranUseCase,
    private val dataStore: DataStoreRepository,
    private val formatter: NumberFormat
): ViewModel() {
    private val _state = mutableStateOf(PengeluaranState())
    val state: State<PengeluaranState> = _state

    private fun getPengeluaran(token: String) {
        viewModelScope.launch {
            useCase
                .invoke(token)
                .collect {
                    when(it) {
                        is Resource.Success -> {
                            _state.value = PengeluaranState(data = it.data!!)
                        }
                        is Resource.Loading -> {
                            _state.value = PengeluaranState(true)
                        }
                        is Resource.Error -> {
                            _state.value = PengeluaranState(error = it.message.toString())
                        }
                    }
                }
        }
    }

    fun getToken() {
        viewModelScope.launch {
            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{
                if(it.isNotBlank()){
                    getPengeluaran("Bearer $it")
                } else {
                    _state.value = PengeluaranState(isLoading = true)
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