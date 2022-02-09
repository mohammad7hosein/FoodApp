package com.smh.foodapp.data.datastore

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.smh.foodapp.domain.model.FilterType
import com.smh.foodapp.util.Constants.Companion.DEFAULT_CUISINE_TYPE
import com.smh.foodapp.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.smh.foodapp.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.smh.foodapp.util.Constants.Companion.PREFERENCES_CUISINE_TYPE
import com.smh.foodapp.util.Constants.Companion.PREFERENCES_CUISINE_TYPE_ID
import com.smh.foodapp.util.Constants.Companion.PREFERENCES_DARK_THEME
import com.smh.foodapp.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.smh.foodapp.util.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.smh.foodapp.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.smh.foodapp.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.smh.foodapp.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)
    private val myDataStore: DataStore<Preferences> = context.dataStore

    private val scope = CoroutineScope(Main)

    private object preferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
        val selectedCuisineType = stringPreferencesKey(PREFERENCES_CUISINE_TYPE)
        val selectedCuisineTypeId = intPreferencesKey(PREFERENCES_CUISINE_TYPE_ID)
        val selectedTheme = booleanPreferencesKey(PREFERENCES_DARK_THEME)
    }

    init {
        observeDataStore()
    }

    val isDark = mutableStateOf(false)

    fun toggleTheme() {
        scope.launch {
            myDataStore.edit { preferences ->
                val current = preferences[preferenceKeys.selectedTheme] ?: false
                preferences[preferenceKeys.selectedTheme] = !current
            }
        }
    }

    private fun observeDataStore() {
        myDataStore.data.onEach { preferences ->
            preferences[preferenceKeys.selectedTheme]?.let { isDarkTheme ->
                isDark.value = isDarkTheme
            }
        }.launchIn(scope)
    }


    suspend fun saveFilterType(filterType: FilterType) {
        myDataStore.edit { preferences ->
            preferences[preferenceKeys.selectedMealType] = filterType.selectedMealType
            preferences[preferenceKeys.selectedMealTypeId] = filterType.selectedMealTypeId
            preferences[preferenceKeys.selectedDietType] = filterType.selectedDietType
            preferences[preferenceKeys.selectedDietTypeId] = filterType.selectedDietTypeId
            preferences[preferenceKeys.selectedCuisineType] = filterType.selectedCuisineType
            preferences[preferenceKeys.selectedCuisineTypeId] = filterType.selectedCuisineTypeId
        }
    }

    val readFilterType: Flow<FilterType> = myDataStore.data
        .catch { exception ->
            if (exception is IOException)
                emit(emptyPreferences())
            else {
                exception.printStackTrace()
                throw exception
            }
        }
        .map { preferences ->
            val selectedMealType = preferences[preferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = preferences[preferenceKeys.selectedMealTypeId] ?: 0
            val selectedDietType = preferences[preferenceKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = preferences[preferenceKeys.selectedDietTypeId] ?: 0
            val selectedCuisineType = preferences[preferenceKeys.selectedCuisineType] ?: DEFAULT_CUISINE_TYPE
            val selectedCuisineTypeId = preferences[preferenceKeys.selectedCuisineTypeId] ?: 0
            FilterType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId,
                selectedCuisineType,
                selectedCuisineTypeId
            )
        }

}