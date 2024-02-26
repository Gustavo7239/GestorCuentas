package com.gustavo.gestorcuentas.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gustavo.gestorcuentas.model.Cuenta

@Database(
    entities = [Cuenta::class],
    version = 1,
    exportSchema = false
)
abstract class CuentaDataBase: RoomDatabase(){

    abstract fun cuentaDao(): CuentaDatabaseDAO
}