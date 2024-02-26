package com.gustavo.gestorcuentas.vista

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gustavo.gestorcuentas.model.Cuenta
import com.gustavo.gestorcuentas.viewmodels.CuentaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarView(
    navController: NavHostController,
    viewModel: CuentaViewModel,
    id: Int,
    nombreSitio: String?,
    email: String?,
    contraseña: String?
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Editar View",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                })
        }
    ) {
        ContentEditarView(it, navController, viewModel, id, nombreSitio, email, contraseña)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentEditarView(
    it: PaddingValues,
    navController: NavHostController,
    viewModel: CuentaViewModel,
    id: Int,
    nombreSitio: String?,
    email: String?,
    contraseña: String?
) {
    var nombreSitio by remember { mutableStateOf(nombreSitio) }
    var email by remember { mutableStateOf(email) }
    var contraseña by remember { mutableStateOf(contraseña) }

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        OutlinedTextField(
            value = nombreSitio ?: "",
            onValueChange = { nombreSitio = it },
            label = { Text(text = "Nombre del sitio") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        OutlinedTextField(
            value = email ?: "",
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        OutlinedTextField(
            value = contraseña ?: "",
            onValueChange = { contraseña = it },
            label = { Text(text = "Contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        Button(
            onClick = {
                val c =
                    Cuenta(id = id, nombreSitio = nombreSitio!!, email = email!!, contraseña = contraseña!!)
                viewModel.modificarCuenta(c)
                navController.popBackStack()
            }
        ) {
            Text(text = "Editar")
        }
    }
}

