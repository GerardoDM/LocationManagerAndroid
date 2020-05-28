package com.example.gps.Interface;

import android.location.Location;

import com.example.gps.Model.Coordenadas;
import com.example.gps.Model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("guardargps")
    Call<Coordenadas> postData(@Body Coordenadas example);

    @GET("recuperargps")
    Call<Example> traerData();
}
