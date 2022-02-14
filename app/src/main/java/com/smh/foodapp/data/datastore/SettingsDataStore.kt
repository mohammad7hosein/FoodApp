package com.smh.foodapp.data.datastore

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.smh.foodapp.util.Constants.Companion.PREFERENCES_DARK_THEME
import com.smh.foodapp.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)
    private val myDataStore: DataStore<Preferences> = context.dataStore

    private val scope = CoroutineScope(Main)

    companion object {
        private val selectedTheme = booleanPreferencesKey(PREFERENCES_DARK_THEME)
    }

    init {
        observeDataStore()
    }

    val isDark = mutableStateOf(false)

    fun toggleTheme() {
        scope.launch {
            myDataStore.edit { preferences ->
                val current = preferences[selectedTheme] ?: false
                preferences[selectedTheme] = !current
            }
        }
    }

    private fun observeDataStore() {
        myDataStore.data.onEach { preferences ->
            preferences[selectedTheme]?.let { isDarkTheme ->
                isDark.value = isDarkTheme
            }
        }.launchIn(scope)
    }

}