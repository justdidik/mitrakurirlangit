package com.xeon.mitrakurirlangitf.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xeon.mitrakurirlangitf.Model.DataModel;
import com.xeon.mitrakurirlangitf.R;

import java.util.List;

public class AdapterDAta extends  RecyclerView.Adapter<AdapterDAta.HolderData>{
    private Context ctx ;
    private List<DataModel> listnama;

    public AdapterDAta(Context ctx, List<DataModel> listnama) {
        this.ctx = ctx;
        this.listnama = listnama;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listnama.get(position);

    holder.tvId.setText(String.valueOf(dm.getId()));
    holder.tvNama.setText(dm.getNama());
    holder.tvAlamat.setText(dm.getAlamat());
    holder.tvTelepon.setText(dm.getTelpon());

    }

    @Override
    public int getItemCount() {
        return listnama.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvAlamat, tvTelepon;

        public HolderData(@NonNull View itemView) {
            super(itemView);


            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvTelepon = itemView.findViewById(R.id.tv_telepon);

        }
    }
}
