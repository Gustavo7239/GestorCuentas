package com.gustavo.gestorcuentas.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.gustavo.gestorcuentas.model.Cuenta
import kotlinx.coroutines.flow.Flow

@Dao
interface CuentaDatabaseDAO {

    @Query("SELECT * FROM cuenta")
    fun obtenerCuentas(): Flow<List<Cuenta>>

    @Query("SELECT * FROM cuenta WHERE nombreSitio=:nameSitio")
    suspend fun obtenerCuentasPorSitio(nameSitio: String): List<Cuenta>

    @Insert
    suspend fun agregarCuenta(ct: Cuenta)

    @Update
    suspend fun actualizaCuenta(ct: Cuenta)

    @Delete
    suspend fun borrarCuenta(ct: Cuenta)
}