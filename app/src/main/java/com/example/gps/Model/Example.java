package com.example.gps.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {


    @SerializedName("data")
    @Expose
    private List<Coordenadas> data = null;

    public List<Coordenadas> getData() {
        return data;
    }

    public void setData(List<Coordenadas> data) {
        this.data = data;
    }


}
