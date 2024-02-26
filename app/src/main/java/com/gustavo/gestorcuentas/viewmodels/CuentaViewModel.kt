package com.gustavo.gestorcuentas.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavo.gestorcuentas.dao.CuentaDatabaseDAO
import com.gustavo.gestorcuentas.states.CuentaState
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.gustavo.gestorcuentas.model.Cuenta

class CuentaViewModel (
    private val dao: CuentaDatabaseDAO
): ViewModel(){
    var state by mutableStateOf(CuentaState())
        private set
    init {
        viewModelScope.launch {
            dao.obtenerCuentas().collectLatest {
                state = state.copy(
                    listaCuentas = it
                )
            }
        }
    }

    fun agregarCuenta(cuenta: Cuenta) = viewModelScope.launch {
        dao.agregarCuenta(ct = cuenta)
    }
    fun modificarCuenta(cuenta: Cuenta) = viewModelScope.launch {
        dao.actualizaCuenta(ct = cuenta)
    }
    fun borrarCuenta(cuenta: Cuenta) = viewModelScope.launch {
        dao.borrarCuenta(ct = cuenta)
    }
    fun buscarPorSitio(nomSitio: String) {
        viewModelScope.launch {
            dao.obtenerCuentasPorSitio(nameSitio = nomSitio)
        }
    }
}