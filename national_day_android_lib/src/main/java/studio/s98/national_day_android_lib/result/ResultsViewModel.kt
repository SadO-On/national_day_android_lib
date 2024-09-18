package studio.s98.national_day_android_lib.result

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import studio.s98.national_day_android_lib.R

class ResultsViewModel(context: Application) : AndroidViewModel(context) {

    private val zero = MediaPlayer.create(context, R.raw.zero_star_sound)
    private val one = MediaPlayer.create(context, R.raw.one_star_sound)
    private val two = MediaPlayer.create(context, R.raw.two_star_sound)
    private val three = MediaPlayer.create(context, R.raw.three_star_sound)

    fun playSound(startCount: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            when (startCount) {
                0 -> zero.start()
                1 -> one.start()
                2 -> two.start()
                3 -> three.start()
            }
        }
    }

    fun releasePlayers() {
        zero.release()
        one.release()
        two.release()
        three.release()
    }
}