package studio.s98.game_tab_demo.shared

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import studio.s98.game_tab_demo.R


@Composable
fun LottieWidget(modifier: Modifier, res: Int, onFinish: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))

    val progress by animateLottieCompositionAsState(composition)


    LottieAnimation(
        modifier = modifier,
        composition = composition,
        contentScale = ContentScale.FillHeight,
        progress = { progress },
    )

    if (progress == 1.0f) {
        onFinish()
    }
}

@Preview
@Composable
private fun LottieWidgetPreview() {
    LottieWidget(modifier = Modifier.size(128.dp), res = R.raw.three_star) {

    }
}