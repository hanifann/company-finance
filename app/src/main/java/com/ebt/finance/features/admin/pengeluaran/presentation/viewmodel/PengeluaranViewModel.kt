package com.ebt.finance.features.admin.pengeluaran.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.finance.R
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.common.Resource
import com.ebt.finance.features.admin.pemasukan.presentation.state.TokenState
import com.ebt.finance.features.admin.pengeluaran.domain.use_case.GetPengeluaranUseCase
import com.ebt.finance.features.admin.pengeluaran.domain.use_case.GetTotalPengeluaranUseCase
import com.ebt.finance.features.admin.pengeluaran.presentation.state.PengeluaranState
import com.ebt.finance.features.admin.pengeluaran.presentation.state.TotalPengeluaranState
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
class PengeluaranViewModel @Inject constructor(
    private val useCase: GetPengeluaranUseCase,
    private val totalUseCase: GetTotalPengeluaranUseCase,
    private val dataStore: DataStoreRepository,
    private val formatter: NumberFormat
): ViewModel() {
    private val _state = mutableStateOf(PengeluaranState())
    val state: State<PengeluaranState> = _state

    private val _totalState = mutableStateOf(TotalPengeluaranState())
    val totalState: State<TotalPengeluaranState> = _totalState

    private val _tokenState = mutableStateOf(TokenState())
    val tokenState: State<TokenState> = _tokenState

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

    fun getTotalPengeluaran(token: String, bulanTahun: String) {
        viewModelScope.launch {
            totalUseCase.invoke(token, bulanTahun)
                .collect{
                    when(it){
                        is Resource.Success -> {
                            _totalState.value = TotalPengeluaranState(data = it.data!!)
                        }
                        is Resource.Loading -> {
                            _totalState.value = TotalPengeluaranState(isLoading = true)
                        }
                        is Resource.Error -> {
                            _totalState.value = TotalPengeluaranState(error = it.message.toString())
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
                    getTotalPengeluaran("Bearer $it", formatDate(LocalDate.now()))
                    _tokenState.value = TokenState("Bearer $it")
                } else {
                    _state.value = PengeluaranState(isLoading = true)
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