package com.example.ejerciciolistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button button;
    Button toJsonBtn;
    RecyclerView rv;
    RVAdapter adapter;
    Sitios sitiosList;
    List<Sitio> listado;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefs = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        //Esto comprueba si hay datos en el SharedPreferences para cargalos y si no lo hay lo carga vaccio.
        String name = prefs.getString("names","Name");

        if (!name.equals("Name")){
            sitiosList  = new Sitios ();
            listado = sitiosList.fromJSON(name).getSitiosList();
        }
        sitiosList = new Sitios();
        //RecyclerView
        rv = findViewById(R.id.lista);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        adapter = new RVAdapter(listado);
        rv.setAdapter(adapter);


        sitiosList = new Sitios();
        // no hace falta con rv mList = findViewById(R.id.lista);




        button = findViewById(R.id.siguiente);

        toJsonBtn = findViewById(R.id.tojson);

        toJsonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String innerJson = sitiosList.toJSON();
                Log.i("gsonSitios",innerJson);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("names",innerJson);
                editor.apply();

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Save.class );
                startActivityForResult(intent, 2);
            }

        });



    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            String sitio = data.getStringExtra("sitio");
            Toast.makeText(this, sitio, Toast.LENGTH_SHORT).show();
            listado.add(new Sitio(sitio));
            sitiosList = new Sitios((ArrayList<Sitio>) listado);

            //Esto actualiza el json cada vez que se inserta uno
            String innerJson = sitiosList.toJSON();
            Log.i("gsonSitios",innerJson);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("names",innerJson);
            editor.apply();

            // json = sitiosLIst.toJson--> editor de shareperefen  name --> json
            adapter.notifyDataSetChanged();
        }
    }
}
