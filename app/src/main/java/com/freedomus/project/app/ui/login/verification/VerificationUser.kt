package com.freedomus.project.app.ui.login.verification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.freedomus.project.R
import com.freedomus.project.app.core.routes.Routes
import com.freedomus.project.app.ui.login.LoginViewModel
import com.freedomus.project.app.ui.login.component.TopAppBarLogin

@Composable
fun VerificationUser(loginViewModel: LoginViewModel, navigationController: NavHostController) {

    Body(loginViewModel, navigationController)

}

@OptIn(ExperimentalComposeUiApi::class)
//@Preview(showSystemUi = true)
@Composable
fun Body(loginViewModel: LoginViewModel, navigationController: NavHostController) {

    var keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBarLogin(
                "Iniciar Sesion",
                Color.White,
                { backNavigation(loginViewModel, navigationController) },
                { backNavigation(loginViewModel, navigationController) })
        },
        containerColor = Color.White
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxWidth()
                .background(Color.White)
        ) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Verficacion",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    letterSpacing = 2.sp
                )

                Spacer(modifier = Modifier.size(12.dp))

                Text(
                    text = "Se te ha enviado a tu correo un mensaje de verifiacion con el cual podremos validar tu cuenta.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    letterSpacing = 2.sp
                )

                ImgVerification()

            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFB900))
                    .padding(top = 24.dp)

            ) {

                BtnVerification() {}
            }

        }

    }//Scaffold

}


private fun backNavigation(
    loginViewModel: LoginViewModel,
    navigationController: NavHostController,
) {
    loginViewModel.changedNavigateToVerifyAccount(true)
    navigationController.navigate(Routes.Login.route)
}

@Composable
private fun BtnVerification(send: () -> Unit) {
    Button(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        onClick = { send() }
    ) {
        Text(
            "Volver a enviar",
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(4.dp)
        )
    }
}


@Composable
private fun ImgVerification() {
    Image(
        painter = painterResource(R.drawable.verificationlogin),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(300.dp, 300.dp)
    )
}

