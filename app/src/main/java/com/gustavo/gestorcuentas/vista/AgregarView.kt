package com.gustavo.gestorcuentas.vista

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gustavo.gestorcuentas.model.Cuenta
import com.gustavo.gestorcuentas.viewmodels.CuentaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarView(navController: NavHostController,
                viewModel: CuentaViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Agregar View",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors= TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }
                    ) {
                        Icon(imageVector= Icons.Default.ArrowBack, contentDescription ="Volver", tint= Color.White)
                    }
                })
        }
    ){
        ContentAgregarView(it, navController, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentAgregarView(it: PaddingValues, navController: NavHostController, viewModel: CuentaViewModel) {
    var sitioName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        OutlinedTextField(value = sitioName, onValueChange = { sitioName = it},
            label = {Text(text ="sitioName")},
            modifier = Modifier.fillMaxWidth()
                .padding (horizontal = 30.dp)
                .padding (bottom = 15.dp)
        )
        OutlinedTextField(value = email, onValueChange = { email = it},
            label = {Text(text ="email")},
            modifier = Modifier.fillMaxWidth()
                .padding (horizontal = 30.dp)
                .padding (bottom = 15.dp)
        )
        OutlinedTextField(value = contraseña, onValueChange = { contraseña = it},
            label = {Text(text ="contraseña")},
            modifier = Modifier.fillMaxWidth()
                .padding (horizontal = 30.dp)
                .padding (bottom = 15.dp)
        )
        Button (
            onClick = {
                val ct = Cuenta (nombreSitio =  sitioName, email = email, contraseña = contraseña)
                viewModel.agregarCuenta(ct)
                navController.popBackStack()
            }
        ){
            Text(text = "Agregar")
        }
    }
}
