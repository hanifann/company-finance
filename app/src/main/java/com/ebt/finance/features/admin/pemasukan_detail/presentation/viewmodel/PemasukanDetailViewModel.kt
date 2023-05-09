package com.ebt.finance.features.admin.pemasukan_detail.presentation.viewmodel

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
import com.ebt.finance.features.admin.pemasukan_detail.domain.use_case.GetPemasukanDetailUseCase
import com.ebt.finance.features.admin.pemasukan_detail.presentation.state.DistributorState
import com.ebt.finance.features.admin.pemasukan_detail.presentation.state.PemasukanDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject

@HiltViewModel
class PemasukanDetailViewModel @Inject constructor(
    private val useCase: GetPemasukanDetailUseCase,
    savedStateHandle: SavedStateHandle,
    private val dataStore: DataStoreRepository,
    private val formatter: NumberFormat
): ViewModel() {

    private val _state = mutableStateOf(PemasukanDetailState())
    val state: State<PemasukanDetailState> = _state

    private val _disState = mutableStateOf(DistributorState())
    val disState: State<DistributorState> = _disState

    init {
        savedStateHandle.get<String>(Constant.PARAM_DISTRIBUTOR)?.let {
            _disState.value = DistributorState(it)
        }
        savedStateHandle.get<String>(Constant.PARAM_INCOME_ID)?.let {
            _state.value = PemasukanDetailState(id = it)
            if (_state.value.id.isNotBlank()){
                getToken(_state.value.id)
            } else {
                _state.value = PemasukanDetailState(isLoading = true)
            }
        }
    }

    private fun getDetailPemasukan(id: String, token: String) {
        viewModelScope.launch {
            useCase
                .invoke(id, token)
                .collect {
                    when(it) {
                        is Resource.Success -> {
                            _state.value = PemasukanDetailState(data = it.data!!)
                        }
                        is Resource.Loading -> {
                            _state.value = PemasukanDetailState(isLoading = true)
                        }
                        is Resource.Error -> {
                            _state.value = PemasukanDetailState(error = it.message.toString())
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
                    _state.value = PemasukanDetailState(isLoading = true)
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