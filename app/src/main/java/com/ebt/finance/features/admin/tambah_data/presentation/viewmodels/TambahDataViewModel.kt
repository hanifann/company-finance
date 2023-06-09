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
import com.ebt.finance.features.admin.tambah_data.domain.use_cases.GetJenisPengeluaran
import com.ebt.finance.features.admin.tambah_data.domain.use_cases.PostPengeluaranUseCase
import com.ebt.finance.features.admin.tambah_data.domain.use_cases.PostTambahPemasukan
import com.ebt.finance.features.admin.tambah_data.presentation.states.DistributorState
import com.ebt.finance.features.admin.tambah_data.presentation.states.JenisPengeluaranState
import com.ebt.finance.features.admin.tambah_data.presentation.states.KategoriState
import com.ebt.finance.features.admin.tambah_data.presentation.states.TambahPemasukanState
import com.ebt.finance.features.admin.tambah_data.presentation.states.TambahPengeluaranState
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
    private val postPemasukanUseCase: PostTambahPemasukan,
    private val postPengeluaranUseCase: PostPengeluaranUseCase,
    private val getJenisPengeluaranUseCase: GetJenisPengeluaran
): ViewModel() {

    private val _kategoriState = mutableStateOf(KategoriState())
    val kategoriState: State<KategoriState> = _kategoriState

    private val _distributorState = mutableStateOf(DistributorState())
    val distributorState: State<DistributorState> = _distributorState

    private val _tambahPemasukanState = mutableStateOf(TambahPemasukanState())
    val tambahPemasukanState: State<TambahPemasukanState> = _tambahPemasukanState

    private val _tambahPengeluaranState = mutableStateOf(TambahPengeluaranState())
    val tambahPengeluaranState: State<TambahPengeluaranState> = _tambahPengeluaranState

    private val _jenisPengeluaranState = mutableStateOf(JenisPengeluaranState())
    val jenisPengeluaranState: State<JenisPengeluaranState> = _jenisPengeluaranState

    init {
        savedStateHandle.get<String>(Constant.PARAM_KATEGORI)?.let {
            _kategoriState.value = KategoriState(it)
            if(_kategoriState.value.kategori.isNotBlank()){
                if (_kategoriState.value.kategori == "pemasukan"){
                    getDistributor()
                } else {
                    getJenisPengeluaran()
                }
            }
        }

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

    fun tambahPengeluaran(data: TambahData, file: File) {
        viewModelScope.launch {
            _tambahPengeluaranState.value = TambahPengeluaranState(true)
            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{ token ->

                val requestImage = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                val keterangan =
                    data.keterangan.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                val tgl = data.tgl.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                val totalPemasukan =
                    data.totalHarga.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                val buktiPemasukan =
                    MultipartBody.Part.createFormData("bukti_pengeluaran", file.name, requestImage)
                val distributorId =
                    data.distributorId.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                if(token.isNotBlank()){
                    postPengeluaranUseCase
                        .invoke("Bearer $token", TambahDataBody(
                            bukti = buktiPemasukan,
                            distributorId = distributorId,
                            keterangan = keterangan,
                            tgl = tgl,
                            totalHarga = totalPemasukan
                        ))
                        .collect{
                            when(it) {
                                is Resource.Loading -> {
                                    _tambahPengeluaranState.value = TambahPengeluaranState(true)
                                }
                                is Resource.Success -> {
                                    _tambahPengeluaranState.value = TambahPengeluaranState(data = it.data!!, isSuccess = true)
                                }
                                is Resource.Error -> {
                                    _tambahPengeluaranState.value = TambahPengeluaranState(message = it.message.toString())
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
            dataStore.getData(stringPreferencesKey(R.string.TOKEN_KEY.toString())).collect{ token ->
                if(token.isNotBlank()){
                    getJenisPengeluaranUseCase
                        .invoke("Bearer $token")
                        .collect{
                            when (it) {
                                is Resource.Success -> {
                                    _jenisPengeluaranState.value = JenisPengeluaranState(data = it.data!!)
                                }
                                is Resource.Loading -> {
                                    _jenisPengeluaranState.value = JenisPengeluaranState(isLoading = true)
                                }
                                is Resource.Error -> {
                                    _jenisPengeluaranState.value = JenisPengeluaranState(message = it.message.toString())
                                }
                            }
                        }
                }
            }
        }
    }
}