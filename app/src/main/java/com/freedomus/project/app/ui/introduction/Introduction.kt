package com.freedomus.project.app.ui.introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.freedomus.project.R
import com.freedomus.project.app.core.routes.Routes


@Composable
fun Introduction(navigationController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3 / 2f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImgVerification()

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            CardBody(navigationController)
        }

    }
}

@Composable
fun CardBody(navigationController: NavHostController) {
    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5B201)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        shape = RoundedCornerShape(
            topStart = 36.dp,
            topEnd = 36.dp
        )
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {

            BodyText()

            Spacer(modifier = Modifier.size(18.dp))
            Btnactions({}, {navigationController.navigate(Routes.Login.route)})

        }

    }
}

@Composable
fun BodyText() {
    Spacer(modifier = Modifier.size(30.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Bienvenido",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start
    )

    Spacer(modifier = Modifier.size(12.dp))

    Text(
        text = "Estas listo!? , Empecemos con lo mejor al alcance de tu mano.",
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Start,
        letterSpacing = 2.sp
    )

    Spacer(modifier = Modifier
        .size(2.dp)
        .fillMaxWidth())
    Text(
        text = "Freedomus",
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        letterSpacing = 2.sp
    )
}

@Composable
private fun Btnactions(registro: () -> Unit, iniciar: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier.padding(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = { registro() }
        )
        {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Registrate",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Button(
            modifier = Modifier.padding(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            onClick = { iniciar() }
        )
        {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Inciar sesion",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }// Row


}


@Composable
private fun ImgVerification() {
    Image(
        painter = painterResource(R.drawable.scooter),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(500.dp, 500.dp)
    )
}
