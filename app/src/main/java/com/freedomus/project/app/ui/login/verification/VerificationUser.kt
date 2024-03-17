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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.freedomus.project.R
import com.freedomus.project.app.core.routes.Routes
import com.freedomus.project.app.ui.login.login.component.TopAppBarLogin

@Composable
fun VerificationUser(
    verificationViewModel: VerificationViewModel,
    navigationController: NavHostController,
) {

    Body(verificationViewModel, navigationController)

}

//@Preview(showSystemUi = true)
@Composable
fun Body(verificationViewModel: VerificationViewModel, navigationController: NavHostController) {


    Scaffold(
        topBar = {
            TopAppBarLogin(
                "Iniciar Sesion",
                Color.White,
                { backNavigation(navigationController) },
                { backNavigation(navigationController) })
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

                HeaderView()
                ImgVerification()

            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFB900))
                    .padding(top = 24.dp)

            ) {
                BtnVerification() { verificationViewModel.EmailVerification() }
            }

        }

    }//Scaffold

}

@Composable
fun HeaderView() {

    Text(
        text = "Verficacion",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp
    )

    Spacer(modifier = Modifier.size(12.dp))

    Text(
        text = "Se te ha enviado a tu correo un mensaje de verifiacion con el cual podremos validar tu cuenta. ",
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Start,
        letterSpacing = 2.sp
    )

    Spacer(modifier = Modifier.size(28.dp))

    Text(
        text = "Valida tu cuenta y vuelve a iniciar sesion.",
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Start,
        letterSpacing = 2.sp
    )

}

@Composable
private fun BtnVerification(
    send: () -> Unit,
) {

    val countdownViewModel = viewModel<CountdownViewModel>()
    var isButtonEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = countdownViewModel) {
        countdownViewModel.startCountdown()
        countdownViewModel.counter.collect { count ->
            if (count == 0) {
                isButtonEnabled = true
            }
        }
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        onClick = {
            send()
            isButtonEnabled = false
            countdownViewModel.startCountdown()
        },
        enabled = isButtonEnabled
    ) {
        Text(
            text = if (countdownViewModel.counter.collectAsState().value != 0) "${countdownViewModel.counter.collectAsState().value} Volver a enviar" else "Volver a enviar",
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

private fun backNavigation(
    navigationController: NavHostController,
) {
    navigationController.popBackStack(Routes.Login.route, false)

}
