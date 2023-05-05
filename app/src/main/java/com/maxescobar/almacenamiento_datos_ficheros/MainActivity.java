package com.maxescobar.almacenamiento_datos_ficheros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText etBitacora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBitacora = (EditText) findViewById(R.id.ettMBitacora);
        //Permite recuperar ficheros atravez de un arreglo
        String archivos [] = fileList();

        if (ArchivoExiste(archivos, "bitacora.txt")){
            try {
                //Recuerdos de curso.... Archivo con JAVA...
                InputStreamReader file = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br = new BufferedReader(file);

                String linea = br.readLine();
                String bitacoraCompleta = "";

                while (linea != null){
                    bitacoraCompleta = bitacoraCompleta + linea + "\n";
                    linea = br.readLine();
                }

                br.close();
                archivos.clone();
                etBitacora.setText(bitacoraCompleta);

            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this,"Error interno del sistema", Toast.LENGTH_LONG).show();
        }
    }

    private boolean ArchivoExiste(String ar[], String nombreArchivo){
        for (int i = 0; i < ar.length; i++) {
            if (nombreArchivo.equals(ar[i])) {
                return true;
            }
        }
        return false;
    }

    //Metodo para Guardar
    public void btnGuardar(View vista){
        try {
            OutputStreamWriter file = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            file.write(etBitacora.getText().toString());
            file.flush();
            file.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        Toast.makeText(this,"Bitacora guardad correctamente",Toast.LENGTH_SHORT).show();
    }
}