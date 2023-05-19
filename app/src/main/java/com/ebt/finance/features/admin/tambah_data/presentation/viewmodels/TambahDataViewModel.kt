package com.ebt.finance.features.admin.tambah_data.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ebt.finance.common.Constant
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.features.admin.tambah_data.domain.use_cases.GetDistributor
import com.ebt.finance.features.admin.tambah_data.presentation.states.KategoriState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TambahDataViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dataStore: DataStoreRepository,
    private val getDistributorUseCase: GetDistributor
): ViewModel() {

    private val _kategoriState = mutableStateOf(KategoriState())
    val kategoriState: State<KategoriState> = _kategoriState

    init {
        savedStateHandle.get<String>(Constant.PARAM_KATEGORI)?.let {
            _kategoriState.value = KategoriState(it)
        }
    }
}