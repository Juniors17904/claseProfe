package com.example.myapplication.servicios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.myapplication.interfaces.ConstantesApp;

// Clase que extiende SQLiteOpenHelper para gestionar la conexión a la base de datos
public class ConectaDB extends SQLiteOpenHelper {

    // Constructor de la clase
    public ConectaDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version); // Llama al constructor de la clase padre
    }

    // Método llamado cuando se crea la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Ejecuta la sentencia DDL para crear las tablas definidas en ConstantesApp
        db.execSQL(ConstantesApp.TABLA_DDL);
    }

    // Método llamado cuando se actualiza la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí se pueden manejar las actualizaciones de la base de datos
        // Por ejemplo, se pueden agregar nuevas tablas o modificar las existentes
    }
}
