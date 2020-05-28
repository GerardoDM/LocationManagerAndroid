package com.example.gps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gps.Interface.Api;
import com.example.gps.Model.Coordenadas;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mjsonText;


    Button btnLocation;
    TextView latitud, longitud;

    private LocationManager ubicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        longitud = findViewById(R.id.txtUno);
        latitud = findViewById(R.id.txtDos);
        mjsonText = findViewById(R.id.mJsonText);


        localizacion();
        registerLocation();
        Button botonCambiar = (Button) findViewById(R.id.btnChange);
        botonCambiar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Recicler.class));
            }
        });

        Button botonDos = (Button) findViewById(R.id.btnSend);
        botonDos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getDistanciaDos();
            }
        });


    }

    private void localizacion() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1000);
        }
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (ubicacion != null) {
        //    longitud.setText(String.valueOf(loc.getLongitude()));
          //  latitud.setText(String.valueOf(loc.getLatitude()));
        }


    }

    private void registerLocation() {
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        ubicacion.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, new mLocalizacion());


    }

    public class mLocalizacion implements LocationListener {


        @Override
        public void onLocationChanged(Location location) {
            String lat = "Latitud: " + location.getLatitude();
            String lon = "Longitud: " + location.getLongitude();
            longitud.setText(lon);
            latitud.setText(lat);



        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }


    private void getDistanciaDos() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ramiro174.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Api api = retrofit.create(Api.class);

            Coordenadas example = new Coordenadas("Gera", String.valueOf(loc.getLatitude()) , String.valueOf(loc.getLongitude()) );

            Call<Coordenadas> call = api.postData(example);

            call.enqueue(new Callback<Coordenadas>() {
                @Override
                public void onResponse(Call<Coordenadas> call, Response<Coordenadas> response) {
                    if (!response.isSuccessful()){
                        mjsonText.setText("Codigo: " + response.body());
                        return ;
                    }

                   // Toast.makeText(MainActivity.this,response.body().getValue(),Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(Call<Coordenadas> call, Throwable t) {
                   mjsonText.setText(t.getMessage());
                    Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }








