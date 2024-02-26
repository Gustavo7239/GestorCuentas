package com.gustavo.gestorcuentas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.gustavo.gestorcuentas.dao.CuentaDataBase
import com.gustavo.gestorcuentas.model.Cuenta
import com.gustavo.gestorcuentas.navigation.NavManager
import com.gustavo.gestorcuentas.view.BottomBar
import com.gustavo.gestorcuentas.viewmodels.CuentaViewModel
import com.gustavo.gestorcuentas.vista.AgregarView
import com.gustavo.gestorcuentas.vista.BuscarView
import com.gustavo.gestorcuentas.vista.EditarView
import com.gustavo.gestorcuentas.vista.ListaView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
    @Composable
    fun MainScreen() {
        //navController - navHost -
        val navController = rememberNavController()
        ElementosMenu(navController)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ElementosMenu(navController: NavHostController) {
        Scaffold(
            bottomBar = {
                BottomBar(navController = navController)
            }
        ) { paddingValues ->
            NavHost(navController = navController, startDestination = "segundo") {
                val database =
                    Room.databaseBuilder(
                        this@MainActivity,
                        CuentaDataBase::class.java,
                        "db_cuentas"
                    )
                        .build()
                val dao = database.cuentaDao()
                val viewModel = CuentaViewModel(dao)
                composable("primero") {
                    AgregarView(navController = navController, viewModel = viewModel)
                }
                composable("segundo") {
                    BuscarView(navController = navController, viewModel = viewModel)
                }
                composable("tercero") {
                    ListaView(navController = navController, viewModel = viewModel)
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
    }
}