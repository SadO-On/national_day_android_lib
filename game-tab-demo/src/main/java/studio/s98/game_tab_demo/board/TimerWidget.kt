package studio.s98.game_tab_demo.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerWidget(percent: Float, time: String) {
    val gradient = listOf(
        Color(0xFF81C1BE),
        Color(0xFF81C1BE),
        Color(0xFF76B4B4),
        Color(0xFF6CA8AB),
    )

    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(45.dp)
            .width(128.dp)
            .border(
                width = 2.dp,
                color = Color(0xff103F5D),
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = gradient
                ),
                shape = RoundedCornerShape(10.dp)
            ),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
        ) {
            LinearProgressIndicator(
                progress = percent/100f,
                modifier = Modifier.fillMaxHeight(),
                backgroundColor = Color.Transparent,
                color = Color(0xff43CDC2),
            )
            Text(
                text = time,
                modifier = Modifier.align(Alignment.Center).padding(horizontal = 24.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize =  20.sp,
            )
        }
    }
}


@Preview
@Composable
private fun TimerWidgetPreview() {
    TimerWidget(percent = 50f, time = "1:00")
}