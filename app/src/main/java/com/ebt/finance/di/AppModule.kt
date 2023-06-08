package com.ebt.finance.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.ebt.finance.common.Constant
import com.ebt.finance.common.DataStoreRepository
import com.ebt.finance.common.DataStoreRepositoryImpl
import com.ebt.finance.features.admin.edit_data.data.data_sources.UpdateDataRemoteDataSource
import com.ebt.finance.features.admin.edit_data.data.repositories.UpdateDataRepositoryImpl
import com.ebt.finance.features.admin.edit_data.domain.repositories.UpdateDataRepository
import com.ebt.finance.features.admin.pemasukan.data.datasources.PemasukanRemoteDataSource
import com.ebt.finance.features.admin.pemasukan.data.repositories.PemasukanRepositoryImpl
import com.ebt.finance.features.admin.pemasukan.domain.repositories.PemasukanRepository
import com.ebt.finance.features.admin.pemasukan_detail.data.data_source.PemasukanDetailRemoteDataSource
import com.ebt.finance.features.admin.pemasukan_detail.data.repositories.PemasukanDetailRepositoryImpl
import com.ebt.finance.features.admin.pemasukan_detail.domain.repositories.PemasukanDetailRepository
import com.ebt.finance.features.admin.pengeluaran.data.datasources.PengeluaranRemoteDataSource
import com.ebt.finance.features.admin.pengeluaran.data.repositories.PengeluaranRepositoryImpl
import com.ebt.finance.features.admin.pengeluaran.domain.repositories.PengeluaranRepository
import com.ebt.finance.features.admin.pengeluaran_detail.data.datasources.PengeluaranDetailRemoteDataSource
import com.ebt.finance.features.admin.pengeluaran_detail.data.repositories.PengeluaranDetailRepositoryImpl
import com.ebt.finance.features.admin.pengeluaran_detail.domain.repositories.PengeluaranDetailRepository
import com.ebt.finance.features.admin.tambah_data.data.datasources.TambahDataRemoteDataSource
import com.ebt.finance.features.admin.tambah_data.data.repositories.TambahDataRepositoryImpl
import com.ebt.finance.features.admin.tambah_data.domain.repositories.TambahDataRepository
import com.ebt.finance.features.login.data.datasources.LoginRemoteDataSource
import com.ebt.finance.features.login.data.repositories.LoginRepositoryImp
import com.ebt.finance.features.login.domain.repositories.LoginRepository
import com.ebt.finance.features.pegawai.gaji.data.datasources.GajiRemoteDataSource
import com.ebt.finance.features.pegawai.gaji.data.repositories.GajiRepositoryImpl
import com.ebt.finance.features.pegawai.gaji.domain.repositories.GajiRepository
import com.ebt.finance.features.pegawai.gaji_detail.data.datasource.GajiDetailRemoteDataSource
import com.ebt.finance.features.pegawai.gaji_detail.data.repositories.GajiDetailRepositoryImpl
import com.ebt.finance.features.pegawai.gaji_detail.domain.repositories.GajiDetailRepository
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.create
import java.text.NumberFormat
import java.time.Duration
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private  val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).connectTimeout(Duration.ofSeconds(15))
        .writeTimeout(Duration.ofSeconds(15))
        .readTimeout(Duration.ofSeconds(15)).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .addCallAdapterFactory(EitherCallAdapterFactory.create())
        .client(okHttpClient)
        .build()


    //login
    //datasource
    @Provides
    @Singleton
    fun provideLoginRemoteDataSourc(): LoginRemoteDataSource {
        return retrofit
            .create(LoginRemoteDataSource::class.java)
    }
    //repository
    @Provides
    @Singleton
    fun provideLoginRepository(remoteDataSource: LoginRemoteDataSource): LoginRepository {
        return LoginRepositoryImp(remoteDataSource)
    }


    //admin
    //datasource
    @Provides
    @Singleton
    fun providePemasukanRemoteDataSource(): PemasukanRemoteDataSource {
        return retrofit
            .create(PemasukanRemoteDataSource::class.java)
    }
    //repository
    @Provides
    @Singleton
    fun providePemasukanRepository(remoteDataSource: PemasukanRemoteDataSource): PemasukanRepository {
        return PemasukanRepositoryImpl(remoteDataSource)
    }

    //detail_pemasukan
    //datasource
    @Provides
    @Singleton
    fun providePemasukanDetailDataSource(): PemasukanDetailRemoteDataSource {
        return retrofit
            .create(PemasukanDetailRemoteDataSource::class.java)
    }
    //repository
    @Provides
    @Singleton
    fun providePemasukanDetailRepository(remoteDataSource: PemasukanDetailRemoteDataSource): PemasukanDetailRepository {
        return PemasukanDetailRepositoryImpl(remoteDataSource)
    }

    //pengeluaran
    //datasource
    @Provides
    @Singleton
    fun providePengeluaranRemoteDataSource(): PengeluaranRemoteDataSource {
        return retrofit
            .create(PengeluaranRemoteDataSource::class.java)
    }
    //repository
    @Provides
    @Singleton
    fun providePengeluaranRepository(remoteDataSource: PengeluaranRemoteDataSource): PengeluaranRepository {
        return PengeluaranRepositoryImpl(remoteDataSource)
    }

    //detail_pengeluaran
    //datasource
    @Provides
    @Singleton
    fun providePengeluaranDetailRemoteDataSource(): PengeluaranDetailRemoteDataSource {
        return retrofit
            .create(PengeluaranDetailRemoteDataSource::class.java)
    }
    //rpository
    @Provides
    @Singleton
    fun providePengeluaranDetailRepostiroy(remoteDataSource: PengeluaranDetailRemoteDataSource): PengeluaranDetailRepository {
        return PengeluaranDetailRepositoryImpl(remoteDataSource)
    }

    //tambah_pemasukan
    //datasource
    @Provides
    @Singleton
    fun provideTambahDataRemoteDataSource(): TambahDataRemoteDataSource {
        return retrofit
            .create(TambahDataRemoteDataSource::class.java)
    }
    //repository
    @Provides
    @Singleton
    fun provideTambahDataRespotiroy(dataSource: TambahDataRemoteDataSource): TambahDataRepository {
        return TambahDataRepositoryImpl(dataSource)
    }

    //update_data
    //data_source
    @Provides
    @Singleton
    fun provideUpdateDataRemoteDataSource(): UpdateDataRemoteDataSource {
        return retrofit
            .create(UpdateDataRemoteDataSource::class.java)
    }
    //repository
    @Provides
    @Singleton
    fun provideUpdateDataRepository(dataSource: UpdateDataRemoteDataSource): UpdateDataRepository {
        return UpdateDataRepositoryImpl(dataSource)
    }

    //gaji_karyawan
    //datasource
    @Provides
    @Singleton
    fun provideGajiRemoteDataSource(): GajiRemoteDataSource {
        return retrofit
            .create(GajiRemoteDataSource::class.java)
    }
    //repository
    @Provides
    @Singleton
    fun provideGajiRepository(datasource: GajiRemoteDataSource): GajiRepository {
        return GajiRepositoryImpl(datasource)
    }

    //detail_gaji
    //datasource
    @Provides
    @Singleton
    fun provideGajiDetailRemoteDataSource(): GajiDetailRemoteDataSource {
        return retrofit.create(GajiDetailRemoteDataSource::class.java)
    }
    //rpository
    @Provides
    @Singleton
    fun provideGajiDetailRepository(dataSource: GajiDetailRemoteDataSource): GajiDetailRepository {
        return GajiDetailRepositoryImpl(dataSource)
    }



    //core
    //datastore
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { context.preferencesDataStoreFile("company_data") }
        )
    }
    //gson
    @get:Provides
    @Singleton
    val gson: Gson = Gson()
    //currency
    @get:Provides
    @Singleton
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class UserPreferenceModule {

    @Binds
    abstract fun bindUserPreferences(impl: DataStoreRepositoryImpl): DataStoreRepository
}