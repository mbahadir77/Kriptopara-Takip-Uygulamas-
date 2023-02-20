package com.yildirimtechnologies.kriptoparatakip.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yildirimtechnologies.kriptoparatakip.R;
import com.yildirimtechnologies.kriptoparatakip.model.KriptoModeli;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {
    private ArrayList<KriptoModeli> kriptolistesi;
    private String[] colors = {"#151B54","#046307","#EAC117","#7E191B","#004225","#16E2F5","#806517"};

    public RecyclerViewAdapter(ArrayList<KriptoModeli> kriptolistesi) {
        this.kriptolistesi = kriptolistesi;

    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(kriptolistesi.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return kriptolistesi.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textname;
        TextView textprice;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(KriptoModeli kriptoModeli,String[] colors,Integer position){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 7]));
            textname = itemView.findViewById(R.id.textName);
            textprice = itemView.findViewById(R.id.textprice);
            textname.setText(kriptoModeli.parabirimi);
            textprice.setText(kriptoModeli.fiyat);

        }
    }
}
