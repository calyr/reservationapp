package com.ucb.ucbtest.dollar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.usecases.DoDollarUpdate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DollarViewModel @Inject constructor(
    private val userCase: DoDollarUpdate
): ViewModel() {
    sealed class StateUI {
        object Loading: StateUI()
        class Success(val value: String) : StateUI()
    }
    private val _state = MutableStateFlow<StateUI>(StateUI.Loading)
    val state: StateFlow<StateUI> = _state.asStateFlow()

    init {
        observeRealDollarData()
    }

    private fun observeRealDollarData() {
        viewModelScope.launch {
            userCase.invoke().collect {
                data -> _state.value = StateUI.Success(data)
            }
        }
    }
}