package com.ebt.finance.features.admin.home.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.finance.R
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.features.auth.presentation.state.UserDataState
import com.ebt.finance.features.login.domain.models.UserData
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeAdminViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val gson: Gson
): ViewModel() {

    private val _userDataState = mutableStateOf(UserDataState())
    val userDataState: State<UserDataState> = _userDataState

    init {
        getUserData()
    }
    fun logOut() {
        viewModelScope.launch {
            dataStoreRepository.clearData()
        }
    }

    private fun getUserData(){
        viewModelScope.launch {
            dataStoreRepository.getData(stringPreferencesKey(R.string.USER_DATA_KEY.toString()))
                .collect{
                    if(it.isNotBlank()){
                        _userDataState.value = UserDataState(
                            userData = gson.fromJson(it, UserData::class.java)
                        )
                    }
            }
        }
    }
}