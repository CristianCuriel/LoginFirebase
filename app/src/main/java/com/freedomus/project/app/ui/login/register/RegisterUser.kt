package com.freedomus.project.app.ui.login.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.freedomus.project.R
import com.freedomus.project.app.core.routes.Routes
import com.freedomus.project.app.ui.login.login.component.TopAppBarLogin
import com.freedomus.project.app.ui.login.register.component.BtnRegister
import com.freedomus.project.app.ui.login.register.component.DialogInfo
import com.freedomus.project.app.ui.login.register.component.FormRegister

@Composable
fun RegisterUser(navigationController: NavHostController) {

    val registerViewModel = viewModel<RegisterViewModel>()
    val nombre: String by registerViewModel.nombre.observeAsState(initial = "")
    val apellido: String by registerViewModel.apellido.observeAsState(initial = "")
    val contra: String by registerViewModel.contra.observeAsState(initial = "")
    val correo: String by registerViewModel.correo.observeAsState(initial = "")
    val contraConfir: String by registerViewModel.contraConfir.observeAsState(initial = "")
    val textErrorRegister:String by registerViewModel.textErrorRegister.observeAsState(initial = "")
    val viewStateVerifi by registerViewModel.viewStateVerifi.collectAsState()


    val navigateVerificationEmail by registerViewModel.navigateVerificationEmail.collectAsState()
    val ShowDialogError by registerViewModel.ShowDialogError.collectAsState()

    Scaffold(topBar = {
        TopAppBarLogin(
            "Iniciar Sesion",
            Color(0xFFF5B201),
            { navigationController.navigate(Routes.Login.route) },
            { navigationController.popBackStack(Routes.Introduction.route, false) })
    }, containerColor = Color.White) {

        LaunchedEffect(navigateVerificationEmail) {
            if (navigateVerificationEmail) {
                navigationController.navigate(Routes.VerificationUser.route)
                registerViewModel.changedStateNavigateVerification()
            }
        }

        Column(
            Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Registro de usuarios",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            FormRegister(nombre, apellido, contra, contraConfir, correo, registerViewModel, viewStateVerifi)
            BtnRegister(viewStateVerifi) { registerViewModel.registerUser() }
            DialogInfo(ShowDialogError, textErrorRegister, R.drawable.deniedregister){registerViewModel.onDimissDialogError()}

        }
    }
}






