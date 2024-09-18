package studio.s98.national_day_android_lib.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import studio.s98.national_day_android_lib.R
import studio.s98.national_day_android_lib.shared.LottieWidget
import studio.s98.national_day_android_lib.shared.PrimaryButtonWidget

@Composable
fun TutorialScreen(onDone: () -> Unit) {
    val step = remember {
        mutableIntStateOf(1)
    }
    Dialog(
        onDismissRequest = {},
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            LottieWidget(modifier = Modifier.size(650.dp), res = getStep(step.intValue)) {
                if (step.intValue > 3) {
                    onDone()
                }
            }
            PrimaryButtonWidget(text = if (step.intValue == 3) "فهمت" else "التالي") {
                step.intValue += 1
            }
        }
    }
}

@Preview
@Composable
fun TutorialScreenPreview(){
    TutorialScreen {

    }
}

fun getStep(step: Int): Int {
    return when (step) {
        1 -> R.raw.tutorial_first
        2 -> R.raw.tutorial_second
        else -> R.raw.tutorial_third
    }
}
