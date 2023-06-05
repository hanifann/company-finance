package com.ebt.finance.features.admin.edit_data.presentation.viewmodels

import android.util.Log
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
import com.ebt.finance.features.admin.edit_data.domain.usecases.PutPemasukanUseCase
import com.ebt.finance.features.admin.edit_data.domain.usecases.PutPengeluaranUseCase
import com.ebt.finance.features.admin.edit_data.presentation.states.UpdatePemasukanState
import com.ebt.finance.features.admin.edit_data.presentation.states.UpdatePengeluaranState
import com.ebt.finance.features.admin.pemasukan.domain.models.PemasukanData
import com.ebt.finance.features.admin.pengeluaran.domain.models.PengeluaranData
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData
import com.ebt.finance.features.admin.tambah_data.domain.use_cases.GetDistributor
import com.ebt.finance.features.admin.tambah_data.domain.use_cases.GetJenisPengeluaran
import com.ebt.finance.features.admin.tambah_data.presentation.states.DistributorState
import com.ebt.finance.features.admin.tambah_data.presentation.states.JenisPengeluaranState
import com.ebt.finance.features.admin.tambah_data.presentation.states.KategoriState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateDataViewModel @Inject constructor(
    private val dataStore: DataStoreRepository,
    private val putPemasukanUseCase: PutPemasukanUseCase,
    private val putPengeluaranUseCase: PutPengeluaranUseCase,
    private val getDistributor: GetDistributor,
    private val getJenisPengeluaran: GetJenisPengeluaran,
    private val savedStateHandle: SavedStateHandle,
    private val gson: Gson
): ViewModel() {

    private val _updatePemasukanState = mutableStateOf(UpdatePemasukanState())
    val updatePemasukanState: State<UpdatePemasukanState> = _updatePemasukanState

    private val _updatePengeluaranState = mutableStateOf(UpdatePengeluaranState())
    val updatePengeluaranState: State<UpdatePengeluaranState> =_updatePengeluaranState

    private val _distributorState = mutableStateOf(DistributorState())
    val distributorState: State<DistributorState> = _distributorState

    private val _jenisPengeluaranState = mutableStateOf(JenisPengeluaranState())
    val jenisPengeluaranState: State<JenisPengeluaranState> = _jenisPengeluaranState

    private val _kategoriState = mutableStateOf(KategoriState())
    val kategoriState: State<KategoriState> = _kategoriState

    init {
        savedStateHandle.get<String>(Constant.PARAM_KATEGORI)?.let {
            _kategoriState.value = KategoriState(it)
            if(_kategoriState.value.kategori.isNotBlank()){
                if (_kategoriState.value.kategori == "pemasukan"){
                    getPemasukanData()
                    getDistributor()
                } else {
                    getPengeluaranData()
                    getJenisPengeluaran()
                }
            }
        }
    }

    private fun getPemasukanData(){
        _updatePemasukanState.value = UpdatePemasukanState(true)
        savedStateHandle.get<String>(Constant.PARAM_DATA)?.let {
            if(it.isNotBlank()){
                val data = gson.fromJson(it, PemasukanData::class.java)
                _updatePemasukanState.value = UpdatePemasukanState(data = data)
                Log.e("asd", "getPemasukanData: $data", )
            }
        }
    }

    private fun getPengeluaranData(){
        _updatePengeluaranState.value = UpdatePengeluaranState(true)
        savedStateHandle.get<String>(Constant.PARAM_DATA)?.let {
            if(it.isNotBlank()){
                val data = gson.fromJson(it, PengeluaranData::class.java)
                _updatePengeluaranState.value = UpdatePengeluaranState(data = data)
            }
        }
    }

    fun updatePemasukan(data: TambahData, id: String) {
        viewModelScope.launch {
            _updatePemasukanState.value = UpdatePemasukanState(true)
            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{ token ->
                if(token.isNotBlank()){
                    putPemasukanUseCase
                        .invoke("Bearer $token", id,
                            TambahData(
                                bukti = "",
                                distributorId = data.distributorId,
                                keterangan = data.keterangan,
                                tgl = data.tgl,
                                totalHarga = data.totalHarga,
                            )
                        )
                        .collect{
                            when(it) {
                                is Resource.Loading -> {
                                    _updatePemasukanState.value = UpdatePemasukanState(true)
                                }
                                is Resource.Success -> {
                                    _updatePemasukanState.value = UpdatePemasukanState(
                                        data = PemasukanData(
                                            "",
                                            "",
                                            "",
                                            "",
                                            "",
                                            "",
                                            "",
                                            distributorId = ""
                                        ),
                                        isSuccess = true
                                    )
                                }
                                is Resource.Error -> {
                                    _updatePemasukanState.value = UpdatePemasukanState(message = it.message.toString())
                                }
                            }
                        }
                }
            }
        }
    }

    private fun getDistributor() {
        viewModelScope.launch {
            _distributorState.value = DistributorState(isLoading = true)
            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{ token ->
                if(token.isNotBlank()){
                    getDistributor
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
    private fun getJenisPengeluaran() {
        viewModelScope.launch {
            _jenisPengeluaranState.value = JenisPengeluaranState(isLoading = true)
            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString()))
                .collect { token ->
                    if (token.isNotBlank()) {
                        getJenisPengeluaran
                            .invoke("Bearer $token")
                            .collect {
                                when (it) {
                                    is Resource.Success -> {
                                        _jenisPengeluaranState.value =
                                            JenisPengeluaranState(data = it.data!!)
                                    }

                                    is Resource.Loading -> {
                                        _jenisPengeluaranState.value =
                                            JenisPengeluaranState(isLoading = true)
                                    }

                                    is Resource.Error -> {
                                        _jenisPengeluaranState.value =
                                            JenisPengeluaranState(message = it.message.toString())
                                    }
                                }
                            }
                    }
                }
        }
    }
}