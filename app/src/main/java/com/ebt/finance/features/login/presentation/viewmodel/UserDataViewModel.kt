package com.ebt.finance.features.login.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.finance.R
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.common.Resource
import com.ebt.finance.features.login.domain.use_case.GetUserDataUseCase
import com.ebt.finance.features.login.presentation.state.UserDataState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(
    private val useCase: GetUserDataUseCase,
    private val dataStore: DataStoreRepository,
    private val gson: Gson
): ViewModel() {

    private val _state = mutableStateOf(UserDataState())
    val state: State<UserDataState> = _state

    fun getUserData(token: String) {
        viewModelScope.launch {
            useCase.invoke(token).collect{
                when(it){
                    is Resource.Loading -> {
                        _state.value = UserDataState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = UserDataState(data = it.data)
                        saveData(data = gson.toJson(it.data))
                    }
                    is Resource.Error -> {
                        _state.value = UserDataState(error = it.message.toString())
                        clearData()
                    }
                }
            }
        }
    }

    private fun saveData(data: String) {
        viewModelScope.launch {
            dataStore.saveData(data, stringPreferencesKey(R.string.USER_DATA_KEY.toString()))
        }
    }

    private fun clearData() {
        viewModelScope.launch {
            dataStore.clearData()
        }
    }
}