package studio.s98.tab_demo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import studio.s98.game_tab_demo.GameActivity
import studio.s98.game_tab_demo.GameNavigation

class DummyActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("shared-pref", this.applicationContext.getSharedPreferences("", MODE_PRIVATE).getInt("level", -1).toString())
        Log.i("shared-pref", this.applicationContext.getSharedPreferences("", MODE_PRIVATE).getInt("xp", -1).toString())

        setContent {
            Surface {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Your app",
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Button(onClick = {
                        startActivity(Intent(this@DummyActivity, GameActivity::class.java))
                    }) {
                        Text(text = "Play")
                    }

                    Box {

                    }
                }
            }

        }
    }
}