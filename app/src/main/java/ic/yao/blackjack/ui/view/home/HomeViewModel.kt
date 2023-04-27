package ic.yao.blackjack.ui.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableLiveData<HomeState>()
    val uiState : LiveData<HomeState> = _uiState

    init {
        _uiState.value = HomeState()
    }

    fun onEvent(event : HomeEvent) {
        when(event) {
            is HomeEvent.ShowSettingsModal -> showSettingsModal()
            is HomeEvent.ChangeWinnerPoints -> changeWinnerPoints(event.points)
            is HomeEvent.DismissSettingsModal -> dismissSettingsModal()
        }
    }

    private fun showSettingsModal() {
        _uiState.value = _uiState.value?.copy(
            showSettingsModal = true
        )
    }

    private fun changeWinnerPoints(points : String) {
        _uiState.value = _uiState.value?.copy(
            winnerPoints = points
        )
        dismissSettingsModal()
    }

    private fun dismissSettingsModal() {
        _uiState.value = _uiState.value?.copy(
            showSettingsModal = false
        )
    }
}