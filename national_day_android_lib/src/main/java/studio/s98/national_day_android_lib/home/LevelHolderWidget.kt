package studio.s98.national_day_android_lib.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import studio.s98.national_day_android_lib.R


@Composable
fun LevelHolderWidget(level: String) {

    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.level_holder),
            contentDescription = "Level holder"
        )
        Text(
            text = level,
            modifier = Modifier.padding(top = 12.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 40.sp
        )
    }
}


@Preview
@Composable
fun LevelHolderWidgetPreview() {
    LevelHolderWidget("12")
}