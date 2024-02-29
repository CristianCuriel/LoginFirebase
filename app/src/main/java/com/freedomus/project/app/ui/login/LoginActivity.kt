package com.freedomus.project.app.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.navigation.NavHostController
import com.freedomus.project.app.core.routes.Routes
import com.freedomus.project.app.ui.login.component.EditTextLogin
import com.freedomus.project.app.ui.login.component.OtherAccounts
import com.freedomus.project.app.ui.login.component.TopAppBarLogin

//@Preview(showSystemUi = true)
@Composable
fun LoginSingIn(loginViewModel: LoginViewModel, navigationController: NavHostController) {

    val uiState by loginViewModel.viewState.collectAsState()
    val navigateToVerifyAccount by loginViewModel.navigateToVerifyAccount.collectAsState()



    Scaffold(topBar = { TopAppBarLogin("Registro", Color(0xFFF5B201) , {}, {}) }, containerColor = Color(0xFFF5B201)) {
        Column(
            Modifier
                .padding(it)
                .fillMaxWidth()
        ) {

/*            Box(Modifier.fillMaxSize().background(Color(0x1EC9B507)), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(strokeWidth = 8.dp,color = Color.Black)

            }*/

            TitleSingIn()
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            BodySingIn(loginViewModel, uiState)
        }

    }//Scaffold

    initVerificationUser(navigateToVerifyAccount, navigationController)
    initHomeUser(navigateToVerifyAccount,navigationController )

}

@Composable
fun BodySingIn(loginViewModel: LoginViewModel, uiState: LoginViewState) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(
            topStart = 36.dp,
            topEnd = 36.dp
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            EditTextLogin(loginViewModel, uiState)

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Olvidaste tu contraseña?", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                onClick = { loginViewModel.onLoginSelected() }
            )
            {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Iniciar sesion",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            OtherAccounts()

        } // Column
    }
}


@Composable
fun TitleSingIn() {
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


private fun initVerificationUser(
    navigateToVerifyAccount: Boolean,
    navigationController: NavHostController
) {
    if (!navigateToVerifyAccount){navigationController.navigate(Routes.VerificationUser.route)}
}

private fun initHomeUser(
    navigateToVerifyAccount: Boolean,
    navigationController: NavHostController
) {
    if (navigateToVerifyAccount){navigationController.navigate(Routes.Home.route)}
}
