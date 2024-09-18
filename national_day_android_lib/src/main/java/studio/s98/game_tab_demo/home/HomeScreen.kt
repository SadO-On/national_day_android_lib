package studio.s98.game_tab_demo.home

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import studio.s98.game_tab_demo.R
import studio.s98.game_tab_demo.Screen
import studio.s98.game_tab_demo.shared.BackButtonWidget
import studio.s98.game_tab_demo.shared.PrimaryButtonWidget
import studio.s98.game_tab_demo.util.ScaleAnimation
import studio.s98.game_tab_demo.util.mainBackground
import viewmodel.home.HomeEvents

@Composable
fun HomeScreen(navController: NavController) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current as Activity
    val viewModel = remember { AndroidHomeViewModel(context = context.application) }
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true, block = {
        viewModel.onEvent(HomeEvents.GetLevel)
    })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.primary)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScaleAnimation(delayTime = 500) {
            BackButtonWidget {
                context.finish()
            }
        }

        ScaleAnimation(delayTime = 300) {
            LevelWidget(Modifier.padding(horizontal = 24.dp), uiState.userLevel)
        }
        ScaleAnimation(delayTime = 400) {

            Image(
                painter = painterResource(id = R.drawable.title),
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp),
                contentDescription = "Title"
            )
        }
        StarterVideoPlayer()
        Spacer(modifier = Modifier.weight(1f))
        ScaleAnimation(delayTime = 100) {

            PrimaryButtonWidget(text = "العب") {
                navController.navigate(Screen.Board.withArgs(Gson().toJson(uiState.userLevel)))
            }
        }
        ScaleAnimation(delayTime = 600) {
            Image(
                painter = painterResource(id = R.drawable.powered_by),
                contentDescription = "98's Studio",
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 8.dp)
                    .clickable {
                        uriHandler.openUri("https://www.98s.studio/en")
                    }
            )
        }
    }
}
