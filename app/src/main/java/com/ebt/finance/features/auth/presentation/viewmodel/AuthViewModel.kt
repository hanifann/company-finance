package com.ebt.finance.features.auth.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.finance.R
import com.ebt.finance.common.Resource
import com.ebt.finance.features.auth.domain.use_case.GetTokenUseCase
import com.ebt.finance.features.auth.presentation.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStore: GetTokenUseCase
): ViewModel(){
    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state


    init {
        viewModelScope.launch {
            dataStore.invoke(R.string.TOKEN_KEY.toString())
                .collect{
                    when(it) {
                        is Resource.Success -> {
                            _state.value = AuthState(data = it.data.toString(), isSuccess = true, isLoading = false)
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
}