package com.ucb.ucbtest.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucb.domain.Movie
import com.ucb.ucbtest.counter.CounterUI
import com.ucb.ucbtest.gitalias.GitaliasUI
import com.ucb.ucbtest.login.LoginUI
import com.ucb.ucbtest.movie.MoviesUI
import com.ucb.ucbtest.moviedetail.MovieDetailUI
import com.ucb.ucbtest.takephoto.TakePhotoUI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import com.ucb.ucbtest.dollar.DollarUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.DollarScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }

    ) {

        composable(Screen.DollarScreen.route) {
            DollarUI()
        }

        composable(Screen.MenuScreen.route) {
            LoginUI(
                onSuccess = {
                    navController.navigate(Screen.GitaliasScreen.route)
                }
            )
        }

        composable(Screen.GitaliasScreen.route) {
            GitaliasUI()
        }

        composable(Screen.TakePhotoScreen.route) {
            TakePhotoUI()
        }

        composable(Screen.LoginScreen.route) {
            LoginUI(
                onSuccess = {
                    navController.navigate(Screen.GitaliasScreen.route)
                }
            )
        }

        composable(Screen.MoviesScreen.route) {

            MoviesUI( onSuccess = {
                movie ->
                    val movieJson = Json.encodeToString(movie)
                    val encodeMovieJson = URLEncoder.encode(movieJson, "UTF-8")
                    navController.navigate("${Screen.MovieDetailScreen.route}/$encodeMovieJson")
            })
        }

        composable(
            route = "${Screen.MovieDetailScreen.route}/{movie}",
            arguments = listOf(
                navArgument("movie") {
                    type = NavType.StringType
                }
            )
        ) {
            val movieJson = it.arguments?.getString("movie") ?: ""
            val movieDecoded = URLDecoder.decode(movieJson, "UTF-8")
            val movie = Json.decodeFromString<Movie>(movieDecoded)

            MovieDetailUI( movie = movie, onBackPressed = { navController.popBackStack() })
        }

        composable(Screen.CounterScreen.route) {
            CounterUI()
        }

    }


}