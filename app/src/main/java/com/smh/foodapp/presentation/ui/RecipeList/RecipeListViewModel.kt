package com.smh.foodapp.presentation.ui.RecipeList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smh.foodapp.data.api.RecipeService
import com.smh.foodapp.data.datastore.SettingsDataStore
import com.smh.foodapp.domain.model.DataState
import com.smh.foodapp.domain.model.FilterType
import com.smh.foodapp.domain.model.Recipe
import com.smh.foodapp.domain.network.ConnectivityManager
import com.smh.foodapp.util.Constants.Companion.API_KEY
import com.smh.foodapp.util.Constants.Companion.DEFAULT_CUISINE_TYPE
import com.smh.foodapp.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.smh.foodapp.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.smh.foodapp.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.smh.foodapp.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.smh.foodapp.util.Constants.Companion.QUERY_API_KEY
import com.smh.foodapp.util.Constants.Companion.QUERY_CUISINE
import com.smh.foodapp.util.Constants.Companion.QUERY_DIET
import com.smh.foodapp.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.smh.foodapp.util.Constants.Companion.QUERY_NUMBER
import com.smh.foodapp.util.Constants.Companion.QUERY_SEARCH
import com.smh.foodapp.util.Constants.Companion.QUERY_TYPE
import com.smh.foodapp.util.DialogQueue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeService: RecipeService,
    private val connectivityManager: ConnectivityManager,
    private val dataStore: SettingsDataStore
) : ViewModel() {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE
    private var cuisineType = DEFAULT_CUISINE_TYPE
    private val filterType = dataStore.readFilterType

    private val _state = mutableStateOf(RecipeListState())
    val state: State<RecipeListState> = _state

    private val _searchText = mutableStateOf(
        SearchBoxState(
            hint = "Search..."
        )
    )
    val searchText: State<SearchBoxState> = _searchText

    val dialogQueue = DialogQueue()

    init {
        viewModelScope.launch {
            filterType.collect { value ->
                _state.value = state.value.copy(filter = value)
                showRecipes(value)
            }
        }
    }

    fun onEvent(event: RecipeListEvent) {
        when (event) {
            is RecipeListEvent.Filter -> {
                saveFilterType(event.filterType)
                showRecipes(event.filterType)
            }
            is RecipeListEvent.Search -> {
                getRecipes(applySearchQuery(event.query)).onEach { dataState ->
                    when (dataState) {
                        is DataState.Loading -> {
                            _state.value = state.value.copy(isLoading = true)
                        }
                        is DataState.Success -> {
                            dataState.data?.let { data ->
                                _state.value = state.value.copy(
                                    isLoading = false,
                                    recipes = data
                                )
                            }
                        }
                        is DataState.Error -> {
                            dataState.message?.let { error ->
                                dialogQueue.appendErrorMessage("Error", error)
                            }
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is RecipeListEvent.ShowFilterDialog -> {
                _state.value = state.value.copy(
                    isShowFilterDialog = !state.value.isShowFilterDialog
                )
            }
            is RecipeListEvent.EnteredText -> {
                _searchText.value = searchText.value.copy(
                    text = event.text
                )
            }
            is RecipeListEvent.ChangeTextFocus -> {
                _searchText.value = searchText.value.copy(
                    isHintVisible = !event.focusState.isFocused && _searchText.value.text.isBlank()
                )
            }
        }
    }

    private fun getRecipes(queries: Map<String, String>): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.Loading())
        try {
            if (connectivityManager.isNetworkAvailable.value) {
                val response = handleRecipeResponse(recipeService.getRecipes(queries))
                emit(response)
            } else
                emit(DataState.Error("No Internet Connection"))
        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: "Unknown Error"))
        }
    }

    private fun handleRecipeResponse(response: Response<List<Recipe>>): DataState<List<Recipe>> {
        return when {
            response.message().toString().contains("timeout") -> DataState.Error("Timeout")
            response.code() == 402 -> DataState.Error("Api Key Limited")
            response.body().isNullOrEmpty() -> DataState.Error("Recipes Not Found")
            response.isSuccessful -> DataState.Success(response.body()!!)
            else -> DataState.Error(response.message())
        }
    }

    private fun showRecipes(filterType: FilterType) {
        getRecipes(applyQueries()).onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                }
                is DataState.Success -> {
                    dataState.data?.let { data ->
                        _state.value = state.value.copy(
                            isLoading = false,
                            filter = filterType,
                            recipes = data
                        )
                    }
                }
                is DataState.Error -> {
                    dataState.message?.let { error ->
                        dialogQueue.appendErrorMessage("Error", error)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun saveFilterType(filterType: FilterType) {
        viewModelScope.launch {
            dataStore.saveFilterType(filterType)
        }
    }

    private fun applyQueries(): HashMap<String, String> {
        val queries = HashMap<String, String>()

        viewModelScope.launch {
            filterType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
                cuisineType = value.selectedCuisineType
            }
        }

        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_CUISINE] = cuisineType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

    private fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries = HashMap<String, String>()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

}