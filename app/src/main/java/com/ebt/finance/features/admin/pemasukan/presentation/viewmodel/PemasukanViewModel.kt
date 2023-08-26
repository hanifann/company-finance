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
import com.ebt.finance.features.admin.pemasukan.domain.use_cases.GetTotalPemasukanUseCase
import com.ebt.finance.features.admin.pemasukan.presentation.state.GetPemasukanState
import com.ebt.finance.features.admin.pemasukan.presentation.state.GetTotalPemasukanState
import com.ebt.finance.features.admin.pemasukan.presentation.state.TokenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Currency
import javax.inject.Inject

@HiltViewModel
class PemasukanViewModel @Inject constructor(
    private val useCase: GetPemasukanUseCase,
    private val totalPemasukanUseCase: GetTotalPemasukanUseCase,
    private val dataStore: DataStoreRepository,
    private val formatter: NumberFormat
): ViewModel() {

    private val _state = mutableStateOf(GetPemasukanState())
    val state: State<GetPemasukanState> = _state
    private val _totalState = mutableStateOf(GetTotalPemasukanState())
    val totalState: State<GetTotalPemasukanState> = _totalState
    private val _tokenState = mutableStateOf(TokenState())
    val tokenState: State<TokenState> = _tokenState

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

    fun getTotalPemasukan(token: String, tahunBulan: String) {
        viewModelScope.launch {
            totalPemasukanUseCase
                .invoke(token, tahunBulan)
                .collect{
                    when(it) {
                        is Resource.Success -> {
                            _totalState.value = GetTotalPemasukanState(data = it.data!!)
                        }
                        is Resource.Loading -> {
                            _totalState.value = GetTotalPemasukanState(isLoading = true)
                        }
                        is Resource.Error -> {
                            _totalState.value = GetTotalPemasukanState(error = it.message.toString())
                        }
                    }
                }
        }
    }

    fun getToken() {
        viewModelScope.launch {
            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{
                if(it.isNotBlank()){
                    _tokenState.value = TokenState("Bearer $it")
                    getPemasukan("Bearer $it")
                    getTotalPemasukan("Bearer $it", formatDate(LocalDate.now()))
                } else {
                    _state.value = GetPemasukanState(isLoading = true)
                }
            }
        }
    }

    fun formatDate(value: LocalDate): String {
        val format = DateTimeFormatter.ofPattern("yyyy-MM")
        return value.format(format)
    }

    fun getDate(): List<String>{
        val list = arrayListOf<String>()
        Calendar.getInstance().let {
                calendar ->
            calendar.add(Calendar.MONTH, 1)
            calendar.add(Calendar.MONTH, -24)
            for (i in 0 until 24) {
                list.add(SimpleDateFormat("yyyy-MM").format(calendar.timeInMillis))
                calendar.add(Calendar.MONTH, 1)
            }
        }
        return list
    }

    fun formatCurrenty(value: Double): String {
        formatter.maximumFractionDigits = 0
        formatter.currency = Currency.getInstance("IDR")
        return formatter.format(value)
    }
}