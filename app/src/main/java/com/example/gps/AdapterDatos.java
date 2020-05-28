package com.example.gps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {
    ArrayList<String> listDatos;

    public AdapterDatos(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{


        TextView datoUno, datoDos, datoTres;

        public ViewHolderDatos(View itemView){
            super(itemView);
            datoUno = (TextView) itemView.findViewById(R.id.idNombre);
            datoDos = (TextView) itemView.findViewById(R.id.idLatitud);
            datoTres = (TextView) itemView.findViewById(R.id.idLongitud);
        }

        public void asignarDatos(String datos) {
            datoUno.setText(datos);
            datoDos.setText(datos);
            datoTres.setText(datos);
        }
    }

    @NonNull
    @Override
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDatos.ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }
}

