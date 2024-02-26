package com.gustavo.gestorcuentas.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gustavo.gestorcuentas.viewmodels.CuentaViewModel
import com.gustavo.gestorcuentas.vista.AgregarView
import com.gustavo.gestorcuentas.vista.BuscarView
import com.gustavo.gestorcuentas.vista.EditarView
import com.gustavo.gestorcuentas.vista.ListaView

@Composable
fun NavManager (viewModel: CuentaViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "buscar") {

        composable("lista") {
            ListaView(navController = navController, viewModel = viewModel)
        }
        composable("agregar") {
            AgregarView(navController = navController, viewModel = viewModel)
        }
        composable("buscar") {
            BuscarView(navController = navController, viewModel = viewModel)
        }

        composable("editar/{id}/{nomSitio}/{email}/{contraseña}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            navArgument("nomSitio") { type = NavType.StringType },
            navArgument("email") { type = NavType.StringType },
            navArgument("contraseña") { type = NavType.StringType }
        )) {
            EditarView(
                navController,
                viewModel,
                it.arguments!!.getInt("id"),
                it.arguments?.getString("nomSitio"),
                it.arguments?.getString("email"),
                it.arguments?.getString("contraseña")
            )
        }
    }
}