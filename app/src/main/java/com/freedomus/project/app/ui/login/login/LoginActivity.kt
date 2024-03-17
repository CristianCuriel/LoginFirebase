package com.freedomus.project.app.ui.login.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.freedomus.project.app.core.routes.Routes
import com.freedomus.project.app.data.response.LoginResult
import com.freedomus.project.app.ui.login.login.component.BodySingIn
import com.freedomus.project.app.ui.login.login.component.TopAppBarLogin

@Composable
fun LoginSingIn(navigationController: NavHostController) {

    val loginViewModel = viewModel<LoginViewModel>()

    val uiStateLogin by loginViewModel.viewLoginState.collectAsState()
    val viewStateVerifi by loginViewModel.viewStateVerifi.collectAsState()
    //val isSessionActive by loginViewModel.isSessionActive.collectAsState()

    Scaffold(topBar = {
        TopAppBarLogin(
            "Registro",
            Color(0xFFF5B201),
            {},
            { navigationController.popBackStack(Routes.Introduction.route, false) })
    }, containerColor = Color(0xFFF5B201)) {
        Column(
            Modifier
                .padding(it)
                .fillMaxWidth()
        ) {

            when (uiStateLogin) {
                is LoginResult.Error -> {
                    Log.i(
                        "Cris",
                        "Error al iniciar sesion ${(uiStateLogin as LoginResult.Error).exception}"
                    )
                }

                LoginResult.Loading -> {
                    TitleSingIn()
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    BodySingIn(loginViewModel, viewStateVerifi)
                }

                is LoginResult.Success -> {
                    navigationController.navigate(Routes.Home.route)

                    loginViewModel.resetStateUI()
                }
            }

            /*            LaunchedEffect(isSessionActive ) {
                            if(isSessionActive) {
                                navigationController.navigate(Routes.Home.route)
                            }
                        }*/


        }

    }//Scaffold

}


@Composable
private fun TitleSingIn() {
    Column(
        Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Iniciar Sesion", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            text = "Inicia con tu cuenta FreeDomus o utiliza los metodos adicionales como ingresar.",
            fontSize = 14.sp
        )
    }
}
