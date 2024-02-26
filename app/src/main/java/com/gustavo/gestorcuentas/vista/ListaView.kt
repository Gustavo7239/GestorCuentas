package com.gustavo.gestorcuentas.vista

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import com.gustavo.gestorcuentas.viewmodels.CuentaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaView (navController: NavHostController,
               viewModel:CuentaViewModel){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lista View",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
    ) {
        ContentInicioView (it, navController,viewModel)
    }
}

@Composable
fun ContentInicioView(it: PaddingValues,
                      navController: NavHostController,
                      viewModel: CuentaViewModel) {
    val state = viewModel.state
    LazyColumn(
        modifier = Modifier.padding(it)
    ) {
        items(state.listaCuentas) { cuenta ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(text = cuenta.nombreSitio, fontWeight = FontWeight.Bold)
                    Text(text = "Email: ${cuenta.email} ")
                    Text(text = "Contraseña: ${cuenta.contraseña} ")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick ={
                                navController.navigate("editar/${cuenta.id}/${cuenta.nombreSitio}/${cuenta.email}/${cuenta.contraseña}")
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                        }
                        IconButton(
                            onClick = { viewModel.borrarCuenta(cuenta) }
                        ) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar")
                        }
                    }
                }
            }
        }
    }
}
