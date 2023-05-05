package com.ebt.finance.common

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository{
    fun getData(key: Preferences.Key<String>): Flow<String>

    suspend fun saveData(data: String, key: Preferences.Key<String>)

    suspend fun clearData()
}