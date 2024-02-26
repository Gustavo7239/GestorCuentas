package com.gustavo.gestorcuentas.vista

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gustavo.gestorcuentas.model.Cuenta
import com.gustavo.gestorcuentas.viewmodels.CuentaViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscarView(navController: NavHostController, viewModel: CuentaViewModel) {
    var nombreSitio by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Gestor de contraseñas",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(
                    text = "Gestor Contraseñas",
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(vertical = 40.dp) // Añade un espacio vertical entre el texto y la imagen
                )
                OutlinedTextField(
                    value = nombreSitio,
                    onValueChange = { nombreSitio = it },
                    label = { Text(text = "Nombre del Sitio") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )

                Button(
                    onClick = {
                        // Aquí deberías implementar la lógica para buscar las cuentas por nombre de sitio
                        // viewModel.buscarCuentasPorSitio(nombreSitio)
                        val ct = nombreSitio
                        val cuentas: Unit = viewModel.buscarPorSitio(ct)
                        navController.popBackStack()
                    }
                ) {
                    Text(text = "Buscar")
                }
            }
        }
    }
}
