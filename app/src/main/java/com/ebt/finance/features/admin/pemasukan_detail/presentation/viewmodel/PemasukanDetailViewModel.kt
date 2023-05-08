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
import com.ebt.finance.features.admin.pemasukan_detail.presentation.state.PemasukanDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PemasukanDetailViewModel @Inject constructor(
    private val useCase: GetPemasukanDetailUseCase,
    savedStateHandle: SavedStateHandle,
    private val dataStore: DataStoreRepository
): ViewModel() {

    private val _state = mutableStateOf(PemasukanDetailState())
    val state: State<PemasukanDetailState> = _state

    init {
        savedStateHandle.get<String>(Constant.PARAM_INCOME_ID)?.let {
            _state.value = PemasukanDetailState(id = it)
            if (it.isNotBlank()){
                getToken(it)
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

}