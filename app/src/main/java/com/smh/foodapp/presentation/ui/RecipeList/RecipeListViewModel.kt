package com.smh.foodapp.presentation.ui.RecipeList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smh.foodapp.data.api.RecipeService
import com.smh.foodapp.domain.model.*
import com.smh.foodapp.domain.network.ConnectivityManager
import com.smh.foodapp.util.Constants.Companion.API_KEY
import com.smh.foodapp.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.smh.foodapp.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.smh.foodapp.util.Constants.Companion.QUERY_API_KEY
import com.smh.foodapp.util.Constants.Companion.QUERY_CUISINE
import com.smh.foodapp.util.Constants.Companion.QUERY_DIET
import com.smh.foodapp.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.smh.foodapp.util.Constants.Companion.QUERY_NUMBER
import com.smh.foodapp.util.Constants.Companion.QUERY_SEARCH
import com.smh.foodapp.util.Constants.Companion.QUERY_TYPE
import com.smh.foodapp.util.Constants.Companion.STATE_KEY_SELECTED_CUISINE
import com.smh.foodapp.util.Constants.Companion.STATE_KEY_SELECTED_DIET
import com.smh.foodapp.util.Constants.Companion.STATE_KEY_SELECTED_MEAL
import com.smh.foodapp.util.DialogQueue
import com.smh.foody.models.Recipe
import com.smh.foodapp.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeService: RecipeService,
    private val connectivityManager: ConnectivityManager,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val selectedMealType: MutableState<MealType> = mutableStateOf(MealType.MAIN_COURSE)
    val selectedDietType: MutableState<DietType> = mutableStateOf(DietType.GLUTEN_FREE)
    val selectedCuisineType: MutableState<CuisineType> = mutableStateOf(CuisineType.SPANISH)

    val recipes: MutableState<List<Result>> = mutableStateOf(ArrayList())
    val isLoading = mutableStateOf(false)
    val dialogQueue = DialogQueue()

    val showSplash = MutableStateFlow(true)

    private val _searchText = mutableStateOf(
        SearchBoxState(
            hint = "Search..."
        )
    )
    val searchText: State<SearchBoxState> = _searchText

    init {
        savedStateHandle.get<MealType>(STATE_KEY_SELECTED_MEAL)?.let { c ->
            setSelectedMealType(c)
        }
        savedStateHandle.get<DietType>(STATE_KEY_SELECTED_DIET)?.let { c ->
            setSelectedDietType(c)
        }
        savedStateHandle.get<CuisineType>(STATE_KEY_SELECTED_CUISINE)?.let { c ->
            setSelectedCuisineType(c)
        }
        showRecipes(applyQueries())
    }

    fun onEvent(event: RecipeListEvent) {
        when (event) {
            is RecipeListEvent.Filter -> {
                showRecipes(applyQueries())
            }
            is RecipeListEvent.Search -> {
                showRecipes(applySearchQuery(event.query))
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

    private fun getRecipes(queries: Map<String, String>): Flow<DataState<Recipe>> = flow {
        emit(DataState.Loading())
        try {
            if (connectivityManager.isNetworkAvailable.value) {
                val response = handleRecipeResponse(recipeService.getRecipes(queries))
                emit(response)
            } else
                emit(DataState.Error("No Network Connection"))
        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: "Unknown Error"))
        }
    }

    private fun handleRecipeResponse(response: Response<Recipe>): DataState<Recipe> {
        return when {
            response.message().toString().contains("timeout") -> DataState.Error("Timeout")
            response.code() == 402 -> DataState.Error("Api Key Limited")
            response.body()!!.results.isNullOrEmpty() -> DataState.Error("Recipes Not Found")
            response.isSuccessful -> DataState.Success(response.body()!!)
            else -> DataState.Error(response.message())
        }
    }

    private fun showRecipes(queries: Map<String, String>) {
        getRecipes(queries).onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    isLoading.value = true
                    showSplash.value = false
                }
                is DataState.Success -> {
                    dataState.data?.let { data ->
                        isLoading.value = false
                        recipes.value = data.results
                    }
                }
                is DataState.Error -> {
                    isLoading.value = false
                    dataState.message?.let { error ->
                        dialogQueue.appendErrorMessage("Error", error)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun applyQueries(): HashMap<String, String> {
        val queries = HashMap<String, String>()
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = selectedMealType.value.text
        queries[QUERY_DIET] = selectedDietType.value.text
        queries[QUERY_CUISINE] = selectedCuisineType.value.text
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

    fun onSelectedMealTypeChanged(meal: String) {
        val newMealType = getMealType(meal)
        setSelectedMealType(newMealType)
    }

    private fun setSelectedMealType(mealType: MealType) {
        selectedMealType.value = mealType
        savedStateHandle.set(STATE_KEY_SELECTED_MEAL, mealType)
    }

    fun onSelectedDietTypeChanged(diet: String) {
        val newDietType = getDietType(diet)
        setSelectedDietType(newDietType)
    }

    private fun setSelectedDietType(dietType: DietType) {
        selectedDietType.value = dietType
        savedStateHandle.set(STATE_KEY_SELECTED_DIET, dietType)
    }

    fun onSelectedCuisineTypeChanged(meal: String) {
        val newCuisineType = getCuisineType(meal)
        setSelectedCuisineType(newCuisineType)
    }

    private fun setSelectedCuisineType(cuisineType: CuisineType) {
        selectedCuisineType.value = cuisineType
        savedStateHandle.set(STATE_KEY_SELECTED_CUISINE, cuisineType)
    }

}