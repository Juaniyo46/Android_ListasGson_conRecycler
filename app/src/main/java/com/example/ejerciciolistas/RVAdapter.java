package com.example.ejerciciolistas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.SitioViewHolder> {


    public static class SitioViewHolder extends RecyclerView.ViewHolder {
        TextView sitio;

        public SitioViewHolder(View view) {
            super(view);
            sitio = (TextView)view.findViewById(R.id.inserccion);
        }
    }

    List<Sitio> sitios;

    RVAdapter(List<Sitio> sitios){
        this.sitios = sitios;
    }

    @NonNull
    @Override
    public SitioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,
                viewGroup, false);
        final SitioViewHolder sitioViewHolder = new SitioViewHolder(v);



        return sitioViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SitioViewHolder sitioViewHolder, int i) {
        sitioViewHolder.sitio.setText(sitios.get(i).getSitio());
    }

    @Override
    public int getItemCount() {
        return sitios.size();
    }
}


