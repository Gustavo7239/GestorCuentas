package com.gustavo.gestorcuentas.states

import com.gustavo.gestorcuentas.model.Cuenta

data class CuentaState(
    val listaCuentas: List<Cuenta> = emptyList()
)
