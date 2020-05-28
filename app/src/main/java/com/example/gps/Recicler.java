package com.example.gps;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gps.Interface.Api;
import com.example.gps.Model.Coordenadas;
import com.example.gps.Model.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Recicler extends AppCompatActivity {

    ArrayList<String> listDatos;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicler);
        getDistanciaU();

        recycler = (RecyclerView) findViewById(R.id.recyclerId);
        recycler.setLayoutManager(new LinearLayoutManager(this));

     //   listDatos = new ArrayList<String>();




       // for (int i = 0; i < 3; i++){
         //   listDatos.add("Dato: " + i );
       // }

        //AdapterDatos adapter = new AdapterDatos(listDatos);
        //recycler.setAdapter(adapter);


    }

    private void getDistanciaU() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ramiro174.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<Example> call = api.traerData();

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {


                if (!response.isSuccessful()){


                    Toast.makeText(Recicler.this, "Si trae", Toast.LENGTH_LONG).show();

                    // textViewResult.setText("Codigo: " + response.code());
                    return ;
                }


               // ArrayList<String> array;
               // array = new ArrayList<String>(response.body().getData().toString());


             //   Toast.makeText(Recicler.this,response.body().getData().toString() ,Toast.LENGTH_SHORT).show();



                listDatos = new ArrayList<String>();

                List<Coordenadas> lista = response.body().getData();

                for(Coordenadas post : lista ){

                        listDatos.add(post.getNombre());
                        listDatos.add(post.getLatitud());
                        listDatos.add(post.getLongitud());
                }










                AdapterDatos adapter = new AdapterDatos(listDatos);
                recycler.setAdapter(adapter);






              //  Toast.makeText(Recicler.this,response.toString(),Toast.LENGTH_LONG).show();





             //   for (Coordenadas dato : datosU){

               //     Toast.makeText(Recicler.this, "A", Toast.LENGTH_SHORT).show();

                //    listDatos.add(dato.getNombre());
                 //   listDatos.add(dato.getLongitud());
                 //   listDatos.add(dato.getLatitud());
             //   }






            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                //textViewResult.setText(t.getMessage());

                Toast.makeText(Recicler.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
