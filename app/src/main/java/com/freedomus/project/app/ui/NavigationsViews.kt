package com.freedomus.project.app.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.freedomus.project.app.core.routes.Routes
import com.freedomus.project.app.ui.home.Home
import com.freedomus.project.app.ui.home.HomeViewModel
import com.freedomus.project.app.ui.login.LoginSingIn
import com.freedomus.project.app.ui.login.LoginViewModel
import com.freedomus.project.app.ui.login.verification.VerificationUser

@Composable
fun NavigationsViews(){

    val navigationController = rememberNavController()
    val loginViewModel = viewModel<LoginViewModel>()
    val homeViewModel = viewModel<HomeViewModel>()

    NavHost(
        navController = navigationController,
        startDestination = Routes.Login.route,
    ) {
        composable(Routes.Login.route) { LoginSingIn(loginViewModel, navigationController) }
        composable(Routes.VerificationUser.route) { VerificationUser(loginViewModel, navigationController) }
        composable(Routes.Home.route) { Home(homeViewModel, navigationController) }
    }//NavHost

}