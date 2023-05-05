package com.ebt.finance.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): DataStoreRepository {
    override fun getData(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map {
                it[key] ?: ""
            }
    }

    override suspend fun saveData(data: String, key: Preferences.Key<String>) {
        dataStore.edit {
            it[key] = data
        }
    }

    override suspend fun clearData() {
        dataStore.edit {
            it.clear()
        }
    }
}