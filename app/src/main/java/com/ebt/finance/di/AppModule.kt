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
import com.ebt.finance.features.login.data.datasources.LoginRemoteDataSource
import com.ebt.finance.features.login.data.repositories.LoginRepositoryImp
import com.ebt.finance.features.login.domain.repositories.LoginRepository
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
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoginRemoteDataSourc(): LoginRemoteDataSource {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .build()
            .create(LoginRemoteDataSource::class.java)
    }

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

    @Provides
    @Singleton
    fun provideLoginRepository(remoteDataSource: LoginRemoteDataSource): LoginRepository {
        return LoginRepositoryImp(remoteDataSource)
    }

    @get:Provides
    @Singleton
    val gson: Gson = Gson()
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class UserPreferenceModule {

    @Binds
    abstract fun bindUserPreferences(impl: DataStoreRepositoryImpl): DataStoreRepository
}