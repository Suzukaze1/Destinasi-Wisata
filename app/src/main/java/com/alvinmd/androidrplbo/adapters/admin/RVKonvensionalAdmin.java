package com.alvinmd.androidrplbo.adapters.admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alvinmd.androidrplbo.R;
import com.alvinmd.androidrplbo.activity.admin.konvensional.AktifkanKonvensionalActivity;
import com.alvinmd.androidrplbo.activity.admin.konvensional.UbahKonvensionalActivity;
import com.alvinmd.androidrplbo.activity.admin.konvensional.NonAktifKonvensionalActivity;
import com.alvinmd.androidrplbo.model.WisataKonvensional;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class RVKonvensionalAdmin extends RecyclerView.Adapter<RVKonvensionalAdmin.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<WisataKonvensional> mData;
    RequestOptions option;
    private int i;

    public RVKonvensionalAdmin(Context mContext, List<WisataKonvensional> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //Request option for glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    public RVKonvensionalAdmin(List<WisataKonvensional> mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_konvensional_admin, parent, false);

        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, UbahKonvensionalActivity.class);
                i.putExtra("tvHomestay", mData.get(viewHolder.getAdapterPosition()).getNama_wisata());
                i.putExtra("tvDeskripsi", mData.get(viewHolder.getAdapterPosition()).getDeskripsi_wisata());
                i.putExtra("imgThumb", mData.get(viewHolder.getAdapterPosition()).getThumb_wisata());
                i.putExtra("tvNavigasii", mData.get(viewHolder.getAdapterPosition()).getNavigasi_wisata());
                i.putExtra("email", mData.get(viewHolder.getAdapterPosition()).getEmail_wisata());
                i.putExtra("alamat", mData.get(viewHolder.getAdapterPosition()).getAlamat_wisata());
                i.putExtra("notelp", mData.get(viewHolder.getAdapterPosition()).getNotelp_wisata());
                i.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
                mContext.startActivity(i);
            }
        });

        viewHolder.btnNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(mContext, NonAktifKonvensionalActivity.class);
                j.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
                j.putExtra("nonaktif", mData.get(viewHolder.getAdapterPosition()).getNon_aktif());
                mContext.startActivity(j);
            }
        });

        viewHolder.btnAktif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(mContext, AktifkanKonvensionalActivity.class);
                k.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
                k.putExtra("nonaktif", mData.get(viewHolder.getAdapterPosition()).getNon_aktif());
                mContext.startActivity(k);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String A = mData.get(position).getNon_aktif();

        if (A.equals("1")){
            holder.tvNamaWisata.setText(mData.get(position).getNama_wisata());
            holder.tvDeskripsi.setText(mData.get(position).getDeskripsi_wisata());
            holder.tv_navigasi.setText(mData.get(position).getNavigasi_wisata());
            holder.tvNoTelp.setText(mData.get(position).getNotelp_wisata());
            holder.tvEmail.setText(mData.get(position).getEmail_wisata());
            holder.tvAlamat.setText(mData.get(position).getAlamat_wisata());
            holder.tvId.setText(mData.get(position).getId());
            holder.tvNon.setText(mData.get(position).getNon_aktif());
            //Load Image
            Glide.with(mContext).load(mData.get(position).getThumb_wisata()).apply(option).into(holder.img_thumb);
        }else {
            holder.btnEdit.setVisibility(View.GONE);
            holder.btnNon.setVisibility(View.GONE);
            holder.llHide.setVisibility(View.VISIBLE);
            holder.tvNamaWisata.setText(mData.get(position).getNama_wisata());
            holder.btnAktif.setVisibility(View.VISIBLE);
            //Load Image
            Glide.with(mContext).load(mData.get(position).getThumb_wisata()).apply(option).into(holder.img_thumb);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaWisata, tvDeskripsi, tv_navigasi, tvEmail, tvNoTelp, tvAlamat, tvId, tvNon;
        ImageView img_thumb;
        Button btnEdit,btnNon,btnAktif;
        RelativeLayout view_container;
        LinearLayout llHide;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaWisata = itemView.findViewById(R.id.tvHomestay);
            tvDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
            img_thumb = itemView.findViewById(R.id.imgThumb);
            view_container = itemView.findViewById(R.id.container1);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            tv_navigasi = itemView.findViewById(R.id.tvNavigasii);
            tvEmail = itemView.findViewById(R.id.email);
            tvAlamat = itemView.findViewById(R.id.alamat);
            tvNoTelp = itemView.findViewById(R.id.notelp);
            tvId = itemView.findViewById(R.id.tvIDKE);
            btnNon = itemView.findViewById(R.id.btnNonAktif);
            tvNon = itemView.findViewById(R.id.tvNon1);
            llHide = itemView.findViewById(R.id.llManipulasi);
            btnAktif = itemView.findViewById(R.id.aktifkan12);
        }
    }

    @Override
    public Filter getFilter() {
        return pariwisataFilter;
    }

    private Filter pariwisataFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<WisataKonvensional> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mData);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (WisataKonvensional item : mData){
                    if (item.getNama_wisata().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values= filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
