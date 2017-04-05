package com.example.pc_6.webservices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ProgressBar cargador;
    Button boton;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargador = (ProgressBar) findViewById(R.id.cargador);
        boton = (Button) findViewById(R.id.boton);
        texto = (TextView) findViewById(R.id.texto);
    }

    public Boolean isOnline(){
        //Método para validar la conexón a Internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();

        if(network != null && network.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

    public void onButton(View view){
        if(isOnline()){
            MyTask task = new MyTask();
            task.execute("http://186.116.10.48/zeusacad/img/usuarios.xml");
        }else{
            Toast.makeText(this, "Sin conexión", Toast.LENGTH_SHORT).show();
        }

    }

    public void cargarDatos(String dato){
        texto.append(dato + "\n");
    }

    public class MyTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cargador.setVisibility(View.VISIBLE);
            cargarDatos("Iniciar Tarea");
        }

        @Override
        protected String doInBackground(String... params) {
            String contend = "";
            try {
                contend = HttpManager.getData(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contend;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            cargador.setVisibility(View.GONE);
            cargarDatos(s);
        }
    }
}
