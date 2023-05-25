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
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahDataBody
import com.ebt.finance.features.admin.tambah_data.domain.use_cases.GetDistributor
import com.ebt.finance.features.admin.tambah_data.domain.use_cases.PostTambahPemasukan
import com.ebt.finance.features.admin.tambah_data.presentation.states.DistributorState
import com.ebt.finance.features.admin.tambah_data.presentation.states.KategoriState
import com.ebt.finance.features.admin.tambah_data.presentation.states.TambahPemasukanState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class TambahDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataStore: DataStoreRepository,
    private val getDistributorUseCase: GetDistributor,
    private val postPemasukanUseCase: PostTambahPemasukan
): ViewModel() {

    private val _kategoriState = mutableStateOf(KategoriState())
    val kategoriState: State<KategoriState> = _kategoriState

    private val _distributorState = mutableStateOf(DistributorState())
    val distributorState: State<DistributorState> = _distributorState

    private val _tambahPemasukanState = mutableStateOf(TambahPemasukanState())
    val tambahPemasukanState: State<TambahPemasukanState> = _tambahPemasukanState

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

    fun tambahPemasukan(data: TambahData, file: File) {
        viewModelScope.launch {
            _tambahPemasukanState.value = TambahPemasukanState(true)
            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{ token ->

                val requestImage = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                val jenisPemasukan =
                    data.kategori.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                val keterangan =
                    data.keterangan.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                val tgl = data.tgl.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                val totalPemasukan =
                    data.totalHarga.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                val buktiPemasukan =
                    MultipartBody.Part.createFormData("bukti_pemasukan", file.name, requestImage)
                val distributorId =
                    data.distributorId.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                if(token.isNotBlank()){
                    postPemasukanUseCase
                        .invoke("Bearer $token", TambahDataBody(
                            bukti = buktiPemasukan,
                            distributorId = distributorId,
                            jenisData = jenisPemasukan,
                            keterangan = keterangan,
                            tgl = tgl,
                            totalHarga = totalPemasukan
                        ))
                        .collect{
                            when(it) {
                                is Resource.Loading -> {
                                    _tambahPemasukanState.value = TambahPemasukanState(true)
                                }
                                is Resource.Success -> {
                                    _tambahPemasukanState.value = TambahPemasukanState(data = it.data!!, isSuccess = true)
                                }
                                is Resource.Error -> {
                                    _tambahPemasukanState.value = TambahPemasukanState(message = it.message.toString())
                                }


                            }
                        }
                }
            }
        }
    }
}