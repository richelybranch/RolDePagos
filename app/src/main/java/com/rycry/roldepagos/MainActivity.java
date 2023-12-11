package com.rycry.roldepagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edNombre, edHorasExtras, edNumeroHijos, edAniosEmpresa;
    private Spinner spnCargo;
    private Button btningresarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        edNombre = findViewById(R.id.edNombre);
        edHorasExtras = findViewById(R.id.edHorasExtras);
        edNumeroHijos = findViewById(R.id.edNumeroHijos);
        edAniosEmpresa = findViewById(R.id.edAniosEmpresa);
        spnCargo = findViewById(R.id.spnCargo);
        btningresarDatos = findViewById(R.id.btnIngresarDatos);

        // Configurar Spinner
        String[] opciones = {"Junior", "Semi-Senior", "Senior"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCargo.setAdapter(adapter);

        // Manejar la selección del Spinner
        spnCargo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Aquí puedes realizar acciones adicionales según la selección del cargo si es necesario
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Manejar la falta de selección si es necesario
            }
        });

        // Configurar el botón de ingreso de datos
        btningresarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularYMostrarResultados();
            }
        });
    }

    private void calcularYMostrarResultados() {
        // Obtener datos de entrada
        String nombre = edNombre.getText().toString();
        int horasExtras = Integer.parseInt(edHorasExtras.getText().toString());
        int numeroHijos = Integer.parseInt(edNumeroHijos.getText().toString());
        int aniosEmpresa = Integer.parseInt(edAniosEmpresa.getText().toString());
        String cargoSeleccionado = spnCargo.getSelectedItem().toString();

        // Lógica de cálculos según las condiciones mencionadas
        double sueldoFijo;
        switch (cargoSeleccionado) {
            case "Junior":
                sueldoFijo = 680;
                break;
            case "Semi-Senior":
                sueldoFijo = 980;
                break;
            case "Senior":
                sueldoFijo = 1200;
                break;
            default:
                sueldoFijo = 0; // Manejar caso no esperado
        }

        double subsidioAntiguedad = (aniosEmpresa * 0.08) * sueldoFijo;
        double porcentajeSeguroSocial = 0.0891;
        double seguroSocial = porcentajeSeguroSocial * sueldoFijo;
        double pagoHorasExtras = (horasExtras / 8.0) * sueldoFijo;
        double ingresoPorHijo = (numeroHijos * 0.02) * sueldoFijo;

        double ingresosTotales = sueldoFijo + subsidioAntiguedad + ingresoPorHijo + pagoHorasExtras;
        double egresosTotales = seguroSocial;
        double totalRecibir = ingresosTotales - egresosTotales;

        // Enviar datos a la actividad Resultado
        Intent intent = new Intent(MainActivity.this, Resultado.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("cargo", cargoSeleccionado);
        intent.putExtra("sueldoFijo", sueldoFijo);
        intent.putExtra("subsidioAntiguedad", subsidioAntiguedad);
        intent.putExtra("seguroSocial", seguroSocial);
        intent.putExtra("pagoHorasExtras", pagoHorasExtras);
        intent.putExtra("ingresoPorHijo", ingresoPorHijo);
        intent.putExtra("egresosTotales", egresosTotales);
        intent.putExtra("ingresosTotales", ingresosTotales);
        intent.putExtra("totalRecibir", totalRecibir);

        startActivity(intent);
        }
}