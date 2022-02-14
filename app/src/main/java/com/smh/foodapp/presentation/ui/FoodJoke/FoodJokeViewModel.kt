package com.smh.foodapp.presentation.ui.FoodJoke

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smh.foodapp.data.api.RecipeService
import com.smh.foodapp.domain.model.DataState
import com.smh.foodapp.domain.model.FoodJoke
import com.smh.foodapp.util.Constants.Companion.API_KEY
import com.smh.foodapp.util.DialogQueue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FoodJokeViewModel @Inject constructor(
    private val recipeService: RecipeService
) : ViewModel() {

    val dialogQueue = DialogQueue()

    private val _state = mutableStateOf(FoodJoke(""))
    val state: State<FoodJoke> = _state

    init {
        showFoodJoke()
    }

    fun showFoodJoke() {
        getFoodJoke().onEach { dataState ->
            when(dataState) {
                is DataState.Success -> {
                    dataState.data?.let {
                        _state.value = state.value.copy(
                            text = it.text
                        )
                    }
                }
                is DataState.Error -> {
                    dataState.message?.let { error ->
                        dialogQueue.appendErrorMessage("Error", error)
                    }
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

     private fun getFoodJoke(): Flow<DataState<FoodJoke>> = flow {
        emit(DataState.Loading())
        try {
            val response = handleFoodJokeResponse(recipeService.getFoodJoke(API_KEY))
            emit(response)
        } catch (e: Exception) {
//            if (!connectivityManager.isNetworkAvailable.value)
//                emit(DataState.Error("No Network Connection"))

                emit(DataState.Error(e.message ?: "Unknown Error"))
        }
    }

    private fun handleFoodJokeResponse(response: Response<FoodJoke>): DataState<FoodJoke> {
        return when {
            response.message().toString().contains("timeout") -> DataState.Error("Timeout")
            response.code() == 402 -> DataState.Error("Api Key Limited")
            response.isSuccessful -> DataState.Success(response.body()!!)
            else -> DataState.Error(response.message())
        }
    }

}