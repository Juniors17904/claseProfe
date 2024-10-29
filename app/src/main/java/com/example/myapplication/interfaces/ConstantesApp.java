package com.example.myapplication.interfaces;

// Interfaz que define constantes para la aplicación
public interface ConstantesApp {
    // Nombre de la tabla de productos
    String TABLA_PRODUCTO = "producto";

    // Nombre de la base de datos
    String BDD = "negocio.db";

    // Versión de la base de datos
    int VERSION = 1;

    // Definición de la sentencia DDL para crear la tabla de productos
    String TABLA_DDL = "CREATE TABLE producto (\n" +
            "    codigo INTEGER         PRIMARY KEY AUTOINCREMENT\n" + // Código del producto como clave primaria que se auto incrementa
            "                           NOT NULL,\n" +
            "    nombre VARCHAR (60)    UNIQUE\n" + // Nombre del producto, debe ser único y no nulo
            "                           NOT NULL,\n" +
            "    precio NUMERIC (12, 2) NOT NULL\n" + // Precio del producto, con 12 dígitos en total y 2 después del punto decimal
            ");\n";
}
