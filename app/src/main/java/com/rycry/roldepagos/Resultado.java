package com.rycry.roldepagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        // Obtén los datos pasados desde la MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("nombre", "");
            String cargo = extras.getString("cargo", "");
            double sueldoFijo = extras.getDouble("sueldoFijo", 0);
            double subsidioAntiguedad = extras.getDouble("subsidioAntiguedad", 0);
            double seguroSocial = extras.getDouble("seguroSocial", 0);
            double pagoHorasExtras = extras.getDouble("pagoHorasExtras", 0);
            double ingresoPorHijo = extras.getDouble("ingresoPorHijo", 0);
            double egresosTotales = extras.getDouble("egresosTotales", 0);
            double ingresosTotales = extras.getDouble("ingresosTotales", 0);
            double totalRecibir = extras.getDouble("totalRecibir", 0);

            // Muestra los resultados en los TextViews correspondientes
            TextView tvResultadoNombre = findViewById(R.id.tvResultadoNombre);
            tvResultadoNombre.setText("Nombre: " + nombre);

            TextView tvResultadoCargo = findViewById(R.id.tvResultadoCargo);
            tvResultadoCargo.setText("Cargo: " + cargo);

            TextView edResultadoSueldofijo = findViewById(R.id.edResultadoSueldoFijo);
            edResultadoSueldofijo.setText(String.valueOf(sueldoFijo));

            TextView edResultadoSubsidio = findViewById(R.id.edResultadoSubsidio);
            edResultadoSubsidio.setText(String.valueOf(subsidioAntiguedad));

            TextView edResultadoSeguro = findViewById(R.id.edResultadoSeguro);
            edResultadoSeguro.setText(String.valueOf(seguroSocial));

            TextView edResultadoHoras = findViewById(R.id.edResultadoHoras);
            edResultadoHoras.setText(String.valueOf(pagoHorasExtras));

            TextView edResultadolngresos = findViewById(R.id.edResultadoIngresos);
            edResultadolngresos.setText(String.valueOf(ingresosTotales));

            TextView edResultadoEgresos = findViewById(R.id.edResultadoEgresos);
            edResultadoEgresos.setText(String.valueOf(egresosTotales));

            TextView edResultadoTotal = findViewById(R.id.edResultadoTotal);
            edResultadoTotal.setText(String.valueOf(totalRecibir));

            Button btnVolver = findViewById(R.id.btnVolver);
            btnVolver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Vuelve a la MainActivity
                    Intent intent = new Intent(Resultado.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Cierra la actividad actual
                }
            });

            // Botón "Salir"
            Button btnSalir = findViewById(R.id.btnSalir);
            btnSalir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Sale de la aplicación
                    finishAffinity(); // Cierra todas las actividades en la pila
                    System.exit(0); // Cierra la aplicación por completo
                }
            });

        }
    }
}