package studio.s98.game_tab_demo.shared

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import studio.s98.game_tab_demo.R

@Composable
fun PrimaryButtonWidget(text: String, onClick: () -> Unit) {
    val context = LocalContext.current
    val player = MediaPlayer.create(context, R.raw.click)


    Box(contentAlignment = Alignment.Center, modifier = Modifier.clickable {
        onClick()
        player.start()
    }) {
        Image(
            painter = painterResource(id = R.drawable.play_button),
            contentDescription = "play button"
        )
        Text(
            text = text,
            modifier = Modifier.padding(bottom = 12.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 40.sp
        )
    }

}


@Preview
@Composable
private fun PrimaryButtonWidgetPreview() {
    PrimaryButtonWidget(text = "إلعب") {

    }
}