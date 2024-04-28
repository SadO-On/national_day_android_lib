package studio.s98.game_tab_demo.result

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import studio.s98.game_tab_demo.R
import studio.s98.game_tab_demo.Screen
import studio.s98.game_tab_demo.board.BoardTileWidget
import studio.s98.game_tab_demo.shared.BackButtonWidget
import studio.s98.game_tab_demo.shared.LottieWidget
import studio.s98.game_tab_demo.shared.PrimaryButtonWidget
import studio.s98.game_tab_demo.util.ScaleAnimation
import studio.s98.game_tab_demo.util.mainBackground
import studio.s98.game_tab_demo.util.shareImage
import viewmodel.models.Letter

@Composable
fun ResultScreen(
    navHostController: NavHostController,
    isWin: Boolean,
    starCount: Int,
    missingWords: List<String>
) {

    val isShow = remember { mutableStateOf(false) }
    val context = LocalContext.current as Activity
    val viewModel = remember { ResultsViewModel(context.application) }

    DisposableEffect(key1 = Unit) {
        onDispose {
            viewModel.releasePlayers()
        }
    }

    LaunchedEffect(key1 = Unit) {
        delay(500)
        viewModel.playSound(starCount)
    }

    ResultContent(
        isShow = isShow.value,
        isWin = isWin,
        starCount = starCount,
        phraseRes = getPhrasesRes(starCount),
        starsRes = getStarsRes(starCount),
        onBackClicked = {
            navHostController.popBackStack(
                route = Screen.Home.route,
                inclusive = false
            )
        }, missingWords = missingWords,
        onDoneLottie = { isShow.value = true }) {
        navHostController.popBackStack()
    }
}

private fun getStarsRes(starCount: Int): Int {
    return when (starCount) {
        1 -> R.raw.one_star
        2 -> R.raw.two_star
        3 -> R.raw.three_star
        else -> R.raw.zero_star
    }


}

private fun getPhrasesRes(starCount: Int): Int {
    return when (starCount) {
        1 -> R.drawable.phrase_1
        2 -> R.drawable.phrase_2
        3 -> R.drawable.phrase_3
        else -> R.drawable.phrase_1
    }
}

@Composable
private fun ResultContent(
    isShow: Boolean,
    isWin: Boolean,
    starCount: Int,
    phraseRes: Int,
    starsRes: Int,
    missingWords: List<String>,
    onBackClicked: () -> Unit,
    onDoneLottie: () -> Unit,
    onNextClicked: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackButtonWidget {
            onBackClicked()
        }
        LottieWidget(modifier = Modifier.size(300.dp), res = starsRes) {
            onDoneLottie()
        }


        if (isShow)
            ScaleAnimation(delayTime = 100) {
                Image(
                    painter = painterResource(id = phraseRes),
                    modifier = Modifier.offset(y = (-80).dp),
                    contentDescription = ""
                )
            }

        if (isShow && (starCount == 2 || starCount == 3))
            ScaleAnimation(delayTime = 100) {
                Image(
                    painter = painterResource(id = R.drawable.share),
                    modifier = Modifier.clickable {
                        if (starCount == 2)
                            shareImage(R.drawable.share_2, context)
                        else
                            shareImage(R.drawable.share_3, context)

                    },
                    contentDescription = ""
                )
            }

        if (isShow && !isWin)
            ScaleAnimation(delayTime = 300) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Column(
                        modifier = Modifier.padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        for (word in missingWords) {

                            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                for (i in word.indices) {
                                    BoardTileWidget(
                                        modifier = Modifier.size(30.dp),
                                        isOdd = i % 2 == 0,
                                        letter = Letter(
                                            id = "",
                                            letter = word[i].toString(),
                                            isWrong = false,
                                            isSelected = false,
                                            isSwiped = false,
                                        ),
                                        isSelected = false,
                                        textSize = 16
                                    )
                                }
                            }
                        }
                    }
                }
            }


        if (isShow)
            Spacer(modifier = Modifier.weight(1f))

        if (isShow)
            ScaleAnimation(delayTime = 250) {
                PrimaryButtonWidget(text = "التالي") {
                    onNextClicked()
                }
            }
    }
}

@Preview
@Composable
private fun ResultScreenPreview() {
    ResultContent(
        isShow = true,
        isWin = true,
        phraseRes = R.drawable.phrase_3,
        starsRes = R.raw.two_star,
        onBackClicked = {},
        onDoneLottie = {},
        starCount = 2,
        missingWords = arrayListOf("ظن", "رمال", "جمل"),
        onNextClicked = {}
    )
}