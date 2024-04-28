package studio.s98.game_tab_demo

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val window = this.window
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        actionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }
        super.onCreate(savedInstanceState)
        setContent {
            GameNavigation()
        }
    }
}