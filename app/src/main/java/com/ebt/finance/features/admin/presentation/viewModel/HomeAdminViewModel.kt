package com.ebt.finance.features.admin.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.finance.common.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeAdminViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    fun logOut() {
        viewModelScope.launch {
            dataStoreRepository.clearData()
        }
    }
}