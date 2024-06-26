package com.freedomus.project.app.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.freedomus.project.app.core.routes.Routes
import com.freedomus.project.app.ui.home.Home
import com.freedomus.project.app.ui.introduction.Introduction
import com.freedomus.project.app.ui.login.login.LoginSingIn
import com.freedomus.project.app.ui.login.register.RegisterUser
import com.freedomus.project.app.ui.login.verification.VerificationUser
import com.freedomus.project.app.ui.login.verification.VerificationViewModel

@Composable
fun NavigationsViews(){

    val navigationController = rememberNavController()
    val verificationViewModel = viewModel<VerificationViewModel>()

    NavHost(
        navController = navigationController,
        startDestination = Routes.Introduction.route,
    ) {
        composable(Routes.Introduction.route) { Introduction(navigationController) }
        composable(Routes.Login.route) { LoginSingIn(navigationController) }
        composable(Routes.VerificationUser.route) { VerificationUser(verificationViewModel, navigationController) }
        composable(Routes.RegisterUser.route) { RegisterUser(navigationController) }
        composable(Routes.Home.route) { Home(navigationController) }
    }//NavHost

}