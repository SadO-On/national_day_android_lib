package studio.s98.national_day_android_lib.board


import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import viewmodel.board.FalehFeel


@Composable
fun AnimatedFrames(modifier: Modifier, feel: FalehFeel) {

    val frames = remember { mutableIntStateOf(IDLE) }
    val duration = remember { mutableIntStateOf(IDLE_TIME) }
    val frameName = remember { mutableStateOf(IDLE_NAME) }

    LaunchedEffect(feel) {
        when (feel) {
            FalehFeel.IDLE -> {
                duration.intValue = IDLE_TIME
                frames.intValue = IDLE
                frameName.value = IDLE_NAME
            }

            FalehFeel.SLEEP -> {
                duration.intValue = SLEEP_TIME
                frames.intValue = SLEEP
                frameName.value = SLEEP_NAME
            }

            FalehFeel.CORRECT -> {
                duration.intValue = CORRECT_TIME
                frames.intValue = CORRECT
                frameName.value = CORRECT_NAME
                delay(2500)
                duration.intValue = IDLE_TIME
                frames.intValue = IDLE
                frameName.value = IDLE_NAME
            }
        }
    }


    FeelsAnimationWidget(
        targetFrames = frames.intValue,
        modifier,
        frameName.value,
        duration.intValue
    )
}

@Composable
private fun FeelsAnimationWidget(
    targetFrames: Int,
    modifier: Modifier,
    frameName: String,
    duration: Int
) {
    val context = LocalContext.current

    val value by rememberInfiniteTransition(label = "").animateValue(
        initialValue = 1,
        targetValue = targetFrames,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )


    Image(
        painter = painterResource(id = getDrawableId(value, context, frameName = frameName,  context.packageName)),
        contentDescription = null,
        modifier = modifier
    )
}

@SuppressLint("DiscouragedApi")
fun getDrawableId(frame: Int, context: Context, frameName: String, packageName: String): Int {
    val resourceName = "$frameName$frame"

    var resourceId =
        context.resources.getIdentifier(resourceName, "drawable", packageName)
    if (resourceId == 0) {
        resourceId =
            context.resources.getIdentifier("${frameName}1", "drawable", packageName)

    }

    return resourceId
}

const val IDLE = 124
const val SLEEP = 170
const val CORRECT = 60

const val IDLE_NAME = "idle_frame"
const val SLEEP_NAME = "sleep_frame"
const val CORRECT_NAME = "right_answer"

const val IDLE_TIME = 7500
const val SLEEP_TIME = 9000
const val CORRECT_TIME = 2700