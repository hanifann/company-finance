package com.ebt.finance.features.auth.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.finance.R
import com.ebt.finance.common.Resource
import com.ebt.finance.features.auth.domain.use_case.GetTokenUseCase
import com.ebt.finance.features.auth.presentation.state.AuthState
import com.ebt.finance.features.auth.presentation.state.UserDataState
import com.ebt.finance.features.login.domain.models.UserData
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStore: GetTokenUseCase,
    private val gson: Gson
): ViewModel(){

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    private val _userDataState = mutableStateOf(UserDataState())
    val userDataState: State<UserDataState> = _userDataState
    init {
        viewModelScope.launch {
            dataStore.invoke(R.string.TOKEN_KEY.toString())
                .collect{
                    when(it) {
                        is Resource.Success -> {
                            _state.value = AuthState(data = it.data.toString(), isSuccess = true, isLoading = false)
                            getUserData()
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {
                            _state.value = AuthState(isSuccess = false, isLoading = false)
                        }
                    }
                }
        }
    }

    private fun getUserData() {
        viewModelScope.launch {
            dataStore.invoke(R.string.USER_DATA_KEY.toString())
                .collect{
                    when(it){
                        is Resource.Success -> {
                            _userDataState.value = UserDataState(
                                userData = gson.fromJson(it.data, UserData::class.java),
                                isSuccess = true
                            )
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {
                            _userDataState.value = UserDataState(message = it.message.toString())
                        }
                    }
                }
        }
    }
}