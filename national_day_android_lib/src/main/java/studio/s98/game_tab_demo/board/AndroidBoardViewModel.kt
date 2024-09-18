package studio.s98.game_tab_demo.board

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import studio.s98.game_tab_demo.R
import viewmodel.board.BoardEvents
import viewmodel.board.BoardViewModel
import viewmodel.board.SoundType

class AndroidBoardViewModel(context: Application) : AndroidViewModel(context) {

    private val viewModel by lazy {
        BoardViewModel(
            coroutineScope = viewModelScope,
            context = getApplication()
        )
    }

    private val startSound = MediaPlayer.create(context, R.raw.new_board)
    private val correctSound = MediaPlayer.create(context, R.raw.correct_swipe)
    private val wrongSound = MediaPlayer.create(context, R.raw.wrong_swipe)
    private val almostSound = MediaPlayer.create(context, R.raw.almost)

    val state = viewModel.state
    private val soundState = viewModel.soundState

    fun onEvent(event: BoardEvents) {
        viewModel.onEvent(event)
    }

    fun release(){
        startSound.release()
        correctSound.release()
        wrongSound.release()
        almostSound.release()
    }
    fun soundStateListener() {
        viewModelScope.launch {
            soundState.collect {
                withContext(Dispatchers.Default) {
                    when (soundState.value.soundState) {
                        SoundType.STARTED -> {
                            startSound.start()
                        }

                        SoundType.CORRECT_SWIPE -> {
                            correctSound.start()
                        }

                        SoundType.WRONG_SWIPE -> {
                            wrongSound.start()
                        }

                        SoundType.HALF_TIME -> {
                            almostSound.start()
                        }

                        SoundType.IDLE -> {}
                    }
                }
            }
        }
    }
}