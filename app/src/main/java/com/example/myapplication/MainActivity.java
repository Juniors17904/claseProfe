package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.modelo.dao.ProductoDAO;
import com.example.myapplication.modelo.dto.Producto;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // Declaración de los componentes de la interfaz
    private TextInputLayout txtCodigo, txtNombre, txtPrecio;
    private ListView listado;

    // Método que se ejecuta cuando se crea la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Establece el layout de la actividad
        enlazarControles();  // Enlaza los componentes de la interfaz con el código
        listar();  // Muestra la lista de productos al iniciar
        Log.i("MainActivity", "lee atributos");  // Mensaje de log para confirmar que el onCreate fue ejecutado
    }

    // Método para enlazar los controles de la interfaz con las variables
    private void enlazarControles() {
        txtCodigo = findViewById(R.id.txtCodigo);  // Enlaza el campo de texto para el código
        txtNombre = findViewById(R.id.txtNombre);  // Enlaza el campo de texto para el nombre
        txtPrecio = findViewById(R.id.txtPrecio);  // Enlaza el campo de texto para el precio

        listado = findViewById(R.id.listado);  // Enlaza la lista de productos
        listado.setOnItemClickListener(this);  // Asigna el evento de clic para los elementos de la lista
    }

    // Método que se ejecuta al registrar un nuevo producto
    public void registrar(View v) {
        ProductoDAO pDAO = new ProductoDAO(this);  // Instancia del DAO para acceder a la base de datos
        Producto p = new Producto();  // Crea un nuevo producto

        // Establece los valores del producto tomando los datos de los campos de texto
        p.setCodigo(Integer.parseInt(txtCodigo.getEditText().getText().toString()));
        p.setNombre(txtNombre.getEditText().getText().toString());
        p.setPrecio(Double.parseDouble(txtPrecio.getEditText().getText().toString()));

        // Inserta el producto en la base de datos y obtiene la respuesta
        String resp = pDAO.insertar(p);

        // Muestra un mensaje de éxito o error usando un Snackbar
        if (resp.equals("")) {
            Snackbar.make(v, "Grabado correctamente", Snackbar.LENGTH_LONG).setBackgroundTint(getColor(R.color.azul)).show();
            listar();  // Actualiza la lista de productos
        } else {
            Snackbar.make(v, resp, Snackbar.LENGTH_LONG).setBackgroundTint(getColor(R.color.rojo)).show();
        }

        Log.i("Infox", resp);  // Registra en el log el resultado de la operación
    }

    // Método para listar los productos en el ListView
    private void listar() {
        ProductoDAO pDAO = new ProductoDAO(getBaseContext());  // Instancia del DAO para acceder a la base de datos
        List<Producto> lista = pDAO.getList();  // Obtiene la lista de productos

        // Crea un adaptador para mostrar los productos en el ListView
        ArrayAdapter<Producto> adp = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, lista);

        listado.setAdapter(adp);  // Establece el adaptador en el ListView
    }

    // Evento que se ejecuta cuando se selecciona un ítem de la lista
    @Override
    public void onItemClick(AdapterView<?> adp, View view, int position, long id) {
        Producto p = (Producto) adp.getItemAtPosition(position);  // Obtiene el producto seleccionado
        Log.i("INFOX", p.getNombre());  // Muestra en el log el nombre del producto seleccionado
    }
}
