package com.ebt.finance.features.login.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.common.Resource
import com.ebt.finance.features.login.domain.use_case.PostLoginUseCase
import com.ebt.finance.features.login.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: PostLoginUseCase,
    private val dataStore: DataStoreRepository

): ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun postLogin(email: String, password: String) {
        viewModelScope.launch {
            useCase.invoke(email, password)
                .collect {
                    when(it) {
                        is Resource.Success -> {
                            _state.value = LoginState(data = it.data)
                            saveToken(it.data!!.data)
                        }
                        is Resource.Error -> {
                            _state.value = LoginState(
                                error = it.message ?: "Telah terjadi kesalahan, silahkan coba lagi dalam beberapa saat"
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = LoginState(true)
                        }
                    }
                }
        }
    }

    private fun saveToken(token: String) {
        viewModelScope.launch {
            dataStore.saveData(token, stringPreferencesKey("token"))
        }
    }
}