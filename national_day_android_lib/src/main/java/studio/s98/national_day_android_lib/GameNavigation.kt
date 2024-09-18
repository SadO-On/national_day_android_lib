package studio.s98.national_day_android_lib

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import studio.s98.national_day_android_lib.board.BoardScreen
import studio.s98.national_day_android_lib.home.HomeScreen
import studio.s98.national_day_android_lib.result.ResultScreen
import viewmodel.models.UserLevel
import java.lang.reflect.Type


sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Board : Screen("board")
    data object Result : Screen("result")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}

@Composable
fun GameNavigation() {
    val navController = rememberNavController()
    val gson = remember { Gson() }
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            route = "${Screen.Board.route}/{tutorial}", arguments = listOf(
                navArgument("tutorial") {
                    type = NavType.StringType
                },
            )
        ) { entry ->
            val isShowTutorial = gson.fromJson(entry.arguments?.getString("tutorial"), UserLevel::class.java)
          Log.i("isShowTutorial", isShowTutorial.toString())
            BoardScreen(
                navController,
                isFirst = isShowTutorial.isFirstTime,
                toResult = { starsCount, missingWords ->
                    navController.navigate(
                        Screen.Result.withArgs(
                            starsCount.toString(),
                            gson.toJson(missingWords)
                        )
                    )
                })
        }

        composable(
            route = "${Screen.Result.route}/{stars}/{words}", arguments = listOf(
                navArgument("stars") {
                    type = NavType.StringType
                },
                navArgument("words") {
                    type = NavType.StringType
                },
            )
        ) { entry ->
            val stars = entry.arguments?.getString("stars")
            val listType: Type = object : TypeToken<List<String?>?>() {}.type
            val stringList =
                gson.fromJson<List<String>>(entry.arguments?.getString("words"), listType)

            ResultScreen(
                navHostController = navController,
                isWin = stars?.toInt() != 0,
                starCount = stars?.toInt() ?: 0,
                missingWords = stringList,
            )
        }
    }
}