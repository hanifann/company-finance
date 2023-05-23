package com.ebt.finance.features.admin.tambah_data.presentation.viewmodels

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
import com.ebt.finance.features.admin.tambah_data.domain.use_cases.GetDistributor
import com.ebt.finance.features.admin.tambah_data.presentation.states.DistributorState
import com.ebt.finance.features.admin.tambah_data.presentation.states.KategoriState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TambahDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataStore: DataStoreRepository,
    private val getDistributorUseCase: GetDistributor
): ViewModel() {

    private val _kategoriState = mutableStateOf(KategoriState())
    val kategoriState: State<KategoriState> = _kategoriState

    private val _distributorState = mutableStateOf(DistributorState())
    val distributorState: State<DistributorState> = _distributorState

    init {
        savedStateHandle.get<String>(Constant.PARAM_KATEGORI)?.let {
            _kategoriState.value = KategoriState(it)
        }
        getDistributor()
    }

    private fun getDistributor() {
        viewModelScope.launch {
            _distributorState.value = DistributorState(isLoading = true)
            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{ token ->
                if(token.isNotBlank()){
                    getDistributorUseCase
                        .invoke("Bearer $token")
                        .collect{
                            when (it) {
                                is Resource.Success -> {
                                    _distributorState.value = DistributorState(distributor = it.data!!)
                                }
                                is Resource.Loading -> {
                                    _distributorState.value = DistributorState(isLoading = true)
                                }
                                is Resource.Error -> {
                                    _distributorState.value = DistributorState(message = it.message.toString())
                                }
                            }
                        }
                }
            }
        }
    }
}