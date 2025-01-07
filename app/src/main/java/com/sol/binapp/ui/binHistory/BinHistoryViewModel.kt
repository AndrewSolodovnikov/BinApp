package com.sol.binapp.ui.binHistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sol.binapp.data.BinRepository
import com.sol.binapp.data.BinWithNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinHistoryViewModel @Inject constructor(
    private val repository: BinRepository
) : ViewModel() {
    private val _binHistoryState = MutableLiveData(RecipeListState())
    val binHistoryState: LiveData<RecipeListState> = _binHistoryState

    fun loadBinHistory() {
        viewModelScope.launch {
            val binList = repository.getBinFromCache()
            _binHistoryState.value = _binHistoryState.value?.copy(binList = binList)
        }
    }
}

data class RecipeListState(
    val binList: List<BinWithNumber> = emptyList(),
)
