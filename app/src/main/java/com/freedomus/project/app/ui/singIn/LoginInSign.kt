package com.freedomus.project.app.ui.singIn

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freedomus.project.app.ui.singIn.component.EditTextLogin
import com.freedomus.project.app.ui.singIn.component.OtherAccounts
import com.freedomus.project.app.ui.singIn.component.TopAppBarLogin

@Preview(showSystemUi = true)
@Composable
fun LoginSingIn() {

    Scaffold(topBar = { TopAppBarLogin("Registro") }, containerColor = Color(0xFFF5B201)) {
        Column(
            Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            TitleSingIn()
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            BodySingIn()
        }

    }//Scaffold

}

@Composable
fun BodySingIn() {
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

            EditTextLogin()

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
                onClick = { /*TODO*/ }) {
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