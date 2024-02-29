package com.freedomus.project.app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.freedomus.project.R
import com.freedomus.project.app.core.routes.Routes

@Composable
fun Home(homeViewModel: HomeViewModel, navigationController: NavHostController) {

    //val backToNavigateLogin by homeViewModel.backToNavigateLogin.collectAsState()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Bienvenido",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 2.sp
        )
        Spacer(modifier = Modifier.size(28.dp))
        ImgVerification()
        Spacer(modifier = Modifier.size(28.dp))
        homeViewModel.userinfoProfile()?.email?.let {
            Text(
                text = it,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.size(28.dp))

        Button(onClick = {
            navigationController.popBackStack(Routes.Login.route, false)
            homeViewModel.signOutUser()
        }
        ) {
            Text(text = "Cerrar sesion")
        }
    }

    //back(backToNavigateLogin, navigationController, homeViewModel)

}

@Composable
private fun ImgVerification() {
    Image(
        painter = painterResource(R.drawable.mobileapp),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(300.dp, 300.dp)
    )
}

private fun back(
    backToNavigateLogin: Boolean,
    navigationController: NavHostController,
    homeViewModel: HomeViewModel
) {
   if(backToNavigateLogin){
       homeViewModel.changed()
        navigationController.popBackStack(Routes.Login.route, false)
    }
}