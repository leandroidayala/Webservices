package com.example.pc_6.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpManager {

    public static String getData(String uri) throws IOException{
        //Clase para manejar archivos
        BufferedReader reader = null;
        //Clase URL de java(manejar rutas)
        URL url = new URL(uri);
        //Clase para hacer la conexión
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //Declarar objeto de tipo StringBuilder para manejar el tipo de archivo
        StringBuilder stringBuilder = new StringBuilder();

        //Conectar y leer el archivo de Internet
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();

    }
}
