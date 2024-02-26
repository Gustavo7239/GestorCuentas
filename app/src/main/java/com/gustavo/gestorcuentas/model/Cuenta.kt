package com.gustavo.gestorcuentas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cuenta")
data class Cuenta(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("nombreSitio")
    val nombreSitio: String,

    @ColumnInfo("email")
    val email: String,

    @ColumnInfo("contraseña")
    val contraseña: String
)
