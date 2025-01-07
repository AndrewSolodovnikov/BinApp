package com.sol.binapp.ui.binBankInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sol.binapp.data.BIN
import com.sol.binapp.data.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinBankInfoViewModel @Inject constructor(
    private val repository: BinRepository
): ViewModel() {
    private val _binBankInfoState = MutableLiveData(BinBankInfoState())
    val binBankInfoState: LiveData<BinBankInfoState> = _binBankInfoState

    fun loadBinBankInfo(binNumberArgs: String) {
        viewModelScope.launch {
            val bin: BIN? = repository.getBin(binNumberArgs)

            _binBankInfoState.value = _binBankInfoState.value?.copy(
                bin = bin,
                isError = false,
                isLoading = true
            )
        }
    }

    fun clearState() {
        viewModelScope.launch {
            _binBankInfoState.value = BinBankInfoState()
        }
    }
}

data class BinBankInfoState(
    val bin: BIN? = null,
    val isError: Boolean = false,
    val isLoading: Boolean = false
)
