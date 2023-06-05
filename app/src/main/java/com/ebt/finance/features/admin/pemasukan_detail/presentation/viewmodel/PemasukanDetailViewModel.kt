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
import com.ebt.finance.features.admin.pemasukan.domain.models.PemasukanData
import com.ebt.finance.features.admin.pemasukan_detail.domain.use_case.DeletePemasukanUseCase
import com.ebt.finance.features.admin.pemasukan_detail.domain.use_case.GetPemasukanDetailUseCase
import com.ebt.finance.features.admin.pemasukan_detail.presentation.state.DeletePemasukanState
import com.ebt.finance.features.admin.pemasukan_detail.presentation.state.DistributorState
import com.ebt.finance.features.admin.pemasukan_detail.presentation.state.PemasukanDetailState
import com.ebt.finance.features.auth.presentation.state.UserDataState
import com.ebt.finance.features.image_viewer.presentation.domain.ImageViewer
import com.ebt.finance.features.login.domain.models.UserData
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject

@HiltViewModel
class PemasukanDetailViewModel @Inject constructor(
    private val getUseCase: GetPemasukanDetailUseCase,
    private val deleteUseCase: DeletePemasukanUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val dataStore: DataStoreRepository,
    private val formatter: NumberFormat,
    private val gson: Gson
): ViewModel() {

    private val _state = mutableStateOf(PemasukanDetailState())
    val state: State<PemasukanDetailState> = _state

    private val _disState = mutableStateOf(DistributorState())
    val disState: State<DistributorState> = _disState

    private val _delState = mutableStateOf(DeletePemasukanState())
    val delState: State<DeletePemasukanState> = _delState

    private val _userDataState = mutableStateOf(UserDataState())
    val userDataState: State<UserDataState> = _userDataState

    init {
        getUserData()
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
            getUseCase
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

    fun deletePemasukan() {
        savedStateHandle.get<String>(Constant.PARAM_INCOME_ID)?.let {
            viewModelScope.launch {
                dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString()))
                    .collect { token ->
                        _delState.value = DeletePemasukanState(isLoading = true)
                        if (it.isNotBlank()) {
                            if (token.isNotBlank()) {
                                deleteUseCase
                                    .invoke(it, "Bearer $token")
                                    .collect { resource ->
                                        when (resource) {
                                            is Resource.Success -> {
                                                _delState.value =
                                                    DeletePemasukanState(isSuccess = true)
                                            }

                                            is Resource.Loading -> {
                                                _delState.value =
                                                    DeletePemasukanState(isLoading = true)
                                            }

                                            is Resource.Error -> {
                                                _delState.value =
                                                    DeletePemasukanState(error = resource.message.toString())
                                            }
                                        }
                                    }
                            }
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

    private fun getUserData(){
        viewModelScope.launch {
            dataStore.getData(stringPreferencesKey(R.string.USER_DATA_KEY.toString())).collect{
                if(it.isNotBlank()){
                    _userDataState.value = UserDataState(
                        userData = gson.fromJson(it, UserData::class.java)
                    )
                }
            }
        }
    }

    fun formatCurrenty(value: Double): String {
        formatter.maximumFractionDigits = 0
        formatter.currency = Currency.getInstance("IDR")
        return formatter.format(value)
    }

    fun toJson(image: ImageViewer): String {
        return gson.toJson(image)
    }

    fun paramToJson(pemasukan: PemasukanData): String{
        return gson.toJson(pemasukan)
    }

}