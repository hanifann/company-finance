package com.ebt.finance.features.admin.pengeluaran_detail.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.finance.R
import com.ebt.finance.common.Constant
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.common.Resource
import com.ebt.finance.features.admin.pengeluaran_detail.domain.use_case.DeletePengeluaranUseCase
import com.ebt.finance.features.admin.pengeluaran_detail.domain.use_case.GetPengeluaranDetailUseCase
import com.ebt.finance.features.admin.pengeluaran_detail.presentation.state.DeletePengeluaranState
import com.ebt.finance.features.admin.pengeluaran_detail.presentation.state.JenisPengeluaranState
import com.ebt.finance.features.admin.pengeluaran_detail.presentation.state.PengeluaranDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject

@HiltViewModel
class PengeluaranDetailViewModel @Inject constructor(
    private val getPengeluaranUseCase: GetPengeluaranDetailUseCase,
    private val deletePengeluaranUseCase: DeletePengeluaranUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val dataStore: DataStoreRepository,
    private val formatter: NumberFormat
): ViewModel(){

    private val _state = mutableStateOf(PengeluaranDetailState())
    val state: State<PengeluaranDetailState> = _state

    private val _jenisPengeluaranState = mutableStateOf(JenisPengeluaranState())
    val jenisPengeluaranState: State<JenisPengeluaranState> = _jenisPengeluaranState

    private val _deletePengeluaranState = mutableStateOf(DeletePengeluaranState())
    val deletePengeluaranState: State<DeletePengeluaranState> = _deletePengeluaranState

    init {
        savedStateHandle.get<String>(Constant.PARAM_JENIS_PENGELUARAN)?.let {
            _jenisPengeluaranState.value = JenisPengeluaranState(it)
        }
        savedStateHandle.get<String>(Constant.PARAM_EXPANSE_ID)?.let {
            _state.value = PengeluaranDetailState(id = it)
            if (_state.value.id.isNotBlank()){
                getToken(_state.value.id)
            } else {
                _state.value = PengeluaranDetailState(isLoading = true)
            }
        }
    }

    private fun getDetailPemasukan(id: String, token: String) {
        viewModelScope.launch {
            getPengeluaranUseCase
                .invoke(id, token)
                .collect {
                    when(it) {
                        is Resource.Success -> {
                            _state.value = PengeluaranDetailState(data = it.data!!)
                        }
                        is Resource.Loading -> {
                            _state.value = PengeluaranDetailState(isLoading = true)
                        }
                        is Resource.Error -> {
                            _state.value = PengeluaranDetailState(error = it.message.toString())
                        }
                    }
                }
        }
    }

    fun getToken(id: String) {
        viewModelScope.launch {
            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{
                if(it.isNotBlank()){
                    getDetailPemasukan(id, "Bearer $it")
                } else {
                    _state.value = PengeluaranDetailState(isLoading = true)
                }
            }
        }
    }

    fun deletePemasukan() {
        savedStateHandle.get<String>(Constant.PARAM_EXPANSE_ID)?.let {
            if (it.isNotBlank()){
                viewModelScope.launch {
                    dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{token ->
                        if(token.isNotBlank()){
                            deletePengeluaranUseCase.invoke(it, "Bearer $token").collect{data ->
                                when(data){
                                    is Resource.Loading ->{
                                        _deletePengeluaranState.value = DeletePengeluaranState(true)
                                    }
                                    is Resource.Success -> {
                                        _deletePengeluaranState.value = DeletePengeluaranState(isSuccess = true)
                                    }
                                    is Resource.Error -> {
                                        _deletePengeluaranState.value = DeletePengeluaranState(error = data.message.toString())
                                    }
                                }
                            }
                        }
                    }
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