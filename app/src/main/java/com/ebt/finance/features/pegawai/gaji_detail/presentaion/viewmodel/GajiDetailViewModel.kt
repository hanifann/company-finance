package com.ebt.finance.features.pegawai.gaji_detail.presentaion.viewmodel

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
import com.ebt.finance.features.login.domain.models.UserData
import com.ebt.finance.features.pegawai.gaji_detail.domain.use_case.GetGajiDetailUseCase
import com.ebt.finance.features.pegawai.gaji_detail.presentaion.states.GajiDetailState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GajiDetailViewModel @Inject constructor(
    private val useCase: GetGajiDetailUseCase,
    private val dataStore: DataStoreRepository,
    private val savedStateHandle: SavedStateHandle,
    private val gson: Gson
): ViewModel() {

    private val _gajiDetailState = mutableStateOf(GajiDetailState())
    val gajiDetailState: State<GajiDetailState> = _gajiDetailState

    fun getDetailGaji() {
        viewModelScope.launch {
            savedStateHandle.get<String>(Constant.PARAM_GAJI_ID).let { id ->
                if(!id.isNullOrBlank()){
                    dataStore.getData(stringPreferencesKey(R.string.USER_DATA_KEY.toString())).collect{userId ->
                        if(userId.isNotBlank()){
                            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{token ->
                                if(token.isNotBlank()){
                                    useCase.invoke(
                                        "Bearer $token",
                                        gson.fromJson(userId, UserData::class.java).id.toString(),
                                        id,
                                    ).collect{
                                            when(it){
                                                is Resource.Loading -> {
                                                    _gajiDetailState.value = GajiDetailState(
                                                        true
                                                    )
                                                }
                                                is Resource.Success -> {
                                                    _gajiDetailState.value = GajiDetailState(
                                                        gaji = it.data!!
                                                    )
                                                }
                                                is Resource.Error -> {
                                                    _gajiDetailState.value = GajiDetailState(
                                                        error = it.message.toString()
                                                    )
                                                }
                                            }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}