package studio.s98.game_tab_demo.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import studio.s98.game_tab_demo.R


@Composable
fun BackButtonWidget(onClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Image(
            modifier = Modifier.clickable {
                onClick()
            }.size(56.dp),
            painter = painterResource(R.drawable.back_btn),
            contentDescription = null,
        )
    }
}


@Preview
@Composable
private fun BackButtonWidgetPreview() {
    BackButtonWidget{}
}