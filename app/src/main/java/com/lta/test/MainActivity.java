package com.lta.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    Button btnClientes;
    Button btnProductos;
    Button btnProovedor;
    Button btnTipoProducto;
    Button btnVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClientes = findViewById(R.id.btnClientes);
        btnProductos = findViewById(R.id.btnProductos);
        btnProovedor = findViewById(R.id.btnProovedor);
        btnTipoProducto = findViewById(R.id.btnTipoProducto);
        btnVenta = findViewById(R.id.btnVenta);

        btnClientes.setOnClickListener(v -> {
            Intent it = new Intent(this, Cliente.class);
            startActivity(it);
        });

        btnProductos.setOnClickListener(v -> {
            Intent it = new Intent(this, Productos.class);
            startActivity(it);
        });

        btnProovedor.setOnClickListener(v -> {
            Intent it = new Intent(this, Proovedor.class);
            startActivity(it);
        });

        btnTipoProducto.setOnClickListener(v -> {
            Intent it = new Intent(this, TipoProducto.class);
            startActivity(it);
        });

        btnVenta.setOnClickListener(v -> {
            Intent it = new Intent(this, Cliente.class);
            startActivity(it);
        });
    }

}
