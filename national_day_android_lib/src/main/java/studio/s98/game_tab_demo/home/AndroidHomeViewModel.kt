package studio.s98.game_tab_demo.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import viewmodel.home.HomeEvents
import viewmodel.home.HomeViewModel

class AndroidHomeViewModel(context: Application) : AndroidViewModel(context) {

    private val viewModel by lazy {
        HomeViewModel(
            courotineScope = viewModelScope,
            context = getApplication()
        )
    }

    val state = viewModel.state

    fun onEvent(event: HomeEvents) {
        viewModel.onEvent(event)
    }
}