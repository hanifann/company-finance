package com.ebt.finance.features.pegawai.gaji.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.finance.R
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.common.Resource
import com.ebt.finance.features.login.domain.models.UserData
import com.ebt.finance.features.pegawai.gaji.domain.use_case.GetListGajiUseCase
import com.ebt.finance.features.pegawai.gaji.presentation.state.GajiState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject

@HiltViewModel
class GajiViewModel @Inject constructor(
    private val getListGajiUseCase: GetListGajiUseCase,
    private val dataStore: DataStoreRepository,
    private val gson: Gson,
    private val formatter: NumberFormat
): ViewModel() {

    private val _gajiState = mutableStateOf(GajiState())
    val gajiState: State<GajiState> = _gajiState

    fun logOut() {
        viewModelScope.launch {
            dataStore.clearData()
        }
    }

    fun getListGaji(){
        viewModelScope.launch {
            _gajiState.value = GajiState(isLoading = true)
            dataStore.getData(stringPreferencesKey(R.string.USER_DATA_KEY.toString()))
                .collect{ user ->
                    if(user.isNotBlank()){
                        dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{token ->
                            if(token.isNotBlank()){
                                getListGajiUseCase.invoke(
                                    "Bearer $token",
                                    gson.fromJson(user, UserData::class.java).id.toString()
                                ).collect{
                                    when(it){
                                        is Resource.Loading -> {
                                            _gajiState.value = GajiState(isLoading = true)
                                        }
                                        is Resource.Success -> {
                                            _gajiState.value = GajiState(
                                                gaji = it.data!!
                                            )
                                        }
                                        is Resource.Error -> {
                                            _gajiState.value = GajiState(
                                                message = it.message.toString()
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

    fun formatCurrenty(value: Double): String {
        formatter.maximumFractionDigits = 0
        formatter.currency = Currency.getInstance("IDR")
        return formatter.format(value)
    }
}