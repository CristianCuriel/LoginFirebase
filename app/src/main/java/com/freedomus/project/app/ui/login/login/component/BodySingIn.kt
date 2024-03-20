package com.freedomus.project.app.ui.login.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.freedomus.project.R
import com.freedomus.project.app.ui.login.login.LoginViewModel
import com.freedomus.project.app.ui.login.login.LoginViewState

@Composable
fun BodySingIn(loginViewModel: LoginViewModel, uiState: LoginViewState) {

    val viewStateVerifi by loginViewModel.viewStateVerifi.collectAsState()

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

            RucPass()

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            BtnLogin(viewStateVerifi) { loginViewModel.onLoginSelected() }

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            OtherAccounts()

        } // Column
    }
}

@Composable
private fun RucPass() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(14.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Olvidaste tu contraseña?", fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun BtnLogin(viewStateVerifi: LoginViewState, action: () -> Unit) {

    Button(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        onClick = { action() }
    )
    {

        if(viewStateVerifi.isLoading) {
            CircularProgressIndicator(strokeWidth = 2.dp, color = Color.White, modifier = Modifier.size(6.dp))
        }

        Text(
            modifier = Modifier.padding(10.dp),
            text = "Iniciar sesion",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
fun OtherAccounts() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F1F1))
            .padding(top = 24.dp)
    ) {

        Column(Modifier.fillMaxWidth()) {

            Spacer(modifier = Modifier.padding(vertical = 14.dp))

            BtnAccount(R.drawable.google, "Continuar con Google")
            BtnAccount(R.drawable.facebook, "Continuar con Facebook")

        }// Column
        //Spacer(modifier = Modifier.padding(vertical = 10.dp))
    }

}

