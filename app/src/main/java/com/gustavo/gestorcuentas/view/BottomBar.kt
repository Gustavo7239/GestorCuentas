package com.gustavo.gestorcuentas.view

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gustavo.gestorcuentas.R

@Composable
fun BottomBar(navController: NavController) {

    val iconPrimero = painterResource(id = R.drawable.listas)
    val iconSegundo = painterResource(id = R.drawable.agregar)
    val iconTercero = painterResource(id = R.drawable.home)

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val navBackStackEntry1 = navBackStackEntry
    val currentDestination = navBackStackEntry1.value?.destination

    BottomNavigation {

        BottomNavigationItem(
            selected = currentDestination?.route == "primero",
            onClick = {
                navController.navigate("primero") {
                    popUpTo("primero")
                    launchSingleTop = true
                }
            },
            icon = { Icon(painter = iconSegundo, tint = Color.Magenta, contentDescription = null) },
            label = { Text(text = "Lista") }
        )
        BottomNavigationItem(
            selected = currentDestination?.route == "segundo",
            onClick = {
                navController.navigate("segundo") {
                    popUpTo("segundo")
                    launchSingleTop = true
                }
            },
            icon = { Icon(painter = iconTercero, tint = Color.Cyan, contentDescription = null) },
            label = { Text(text = "Insertar") }
        )
        BottomNavigationItem(
            selected = currentDestination?.route == "tercero",
            onClick = {
                navController.navigate("tercero") {
                    popUpTo("tercero")
                    launchSingleTop = true
                }
            },
            icon = { Icon(painter = iconPrimero, tint = Color.Cyan, contentDescription = null) },
            label = { Text(text = "Principal") }
        )

    }
}