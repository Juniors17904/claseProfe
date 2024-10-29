package com.example.myapplication.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.myapplication.interfaces.ConstantesApp;
import com.example.myapplication.modelo.dto.Producto;
import com.example.myapplication.servicios.ConectaDB;

import java.util.ArrayList;
import java.util.List;

// Clase para manejar operaciones sobre la tabla de productos
public class ProductoDAO {
    private SQLiteDatabase db; // Objeto de base de datos

    // Constructor que inicializa la conexión a la base de datos
    public ProductoDAO(Context context) {
        db = new ConectaDB(context,
                ConstantesApp.BDD, // Nombre de la base de datos
                null,
                ConstantesApp.VERSION).getWritableDatabase(); // Obtiene una base de datos en modo escritura
    }

    // Método para insertar un nuevo producto en la base de datos
    public String insertar(Producto p) {
        String resp = ""; // Variable para almacenar la respuesta
        ContentValues registro = new ContentValues(); // Contenedor para los valores del producto
        registro.put("codigo", p.getCodigo()); // Agrega el código del producto
        registro.put("nombre", p.getNombre()); // Agrega el nombre del producto
        registro.put("precio", p.getPrecio()); // Agrega el precio del producto

        try {
            // Intenta insertar el registro en la tabla de productos
            db.insertOrThrow(ConstantesApp.TABLA_PRODUCTO, null, registro);
        } catch (SQLException ex) {
            // Captura cualquier excepción y almacena el mensaje de error
            resp = ex.getMessage().toString();
        }
        return resp; // Retorna la respuesta
    }

    // Método para obtener una lista de productos de la base de datos
    public List<Producto> getList() {
        List<Producto> lista = new ArrayList<>(); // Lista para almacenar los productos
        String cadSQL = "SELECT codigo, nombre, precio FROM producto;"; // Consulta SQL
        Cursor c = db.rawQuery(cadSQL, null); // Ejecuta la consulta

        if (c != null) {
            // Verifica si el cursor tiene resultados
            if (c.moveToFirst()) {
                do {
                    Producto p = new Producto(); // Crea un nuevo objeto Producto
                    // Asigna los valores del cursor a las propiedades del producto
                    p.setCodigo(c.getInt(c.getColumnIndexOrThrow("codigo")));
                    p.setNombre(c.getString(c.getColumnIndexOrThrow("nombre")));
                    p.setPrecio(c.getDouble(c.getColumnIndexOrThrow("precio")));
                    lista.add(p); // Agrega el producto a la lista
                } while (c.moveToNext()); // Continúa mientras haya más resultados
            }
            c.close(); // Cierra el cursor
        }
        return lista; // Retorna la lista de productos
    }
}
