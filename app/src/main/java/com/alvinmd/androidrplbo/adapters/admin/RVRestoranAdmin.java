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
import com.alvinmd.androidrplbo.activity.admin.homestay.AktifkanHomestayActivity;
import com.alvinmd.androidrplbo.activity.admin.restoran.AktifkanRestoranActivity;
import com.alvinmd.androidrplbo.activity.admin.restoran.UbahRestoranActivity;
import com.alvinmd.androidrplbo.activity.admin.restoran.NonAktifRestoranActivity;
import com.alvinmd.androidrplbo.model.Restoran;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;


public class RVRestoranAdmin extends RecyclerView.Adapter<RVRestoranAdmin.MyViewHolder> implements Filterable {
    private Context mContext;
    private List<Restoran> mData;
    RequestOptions option;
    private int i;

    public RVRestoranAdmin(Context mContext, List<Restoran> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //Request option for glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    public RVRestoranAdmin(List<Restoran> mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public RVRestoranAdmin.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_restoran_admin, parent, false);

        final RVRestoranAdmin.MyViewHolder viewHolder = new RVRestoranAdmin.MyViewHolder(view);
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, UbahRestoranActivity.class);
                i.putExtra("tvHomestay", mData.get(viewHolder.getAdapterPosition()).getNama_restoran());
                i.putExtra("tvDeskripsi", mData.get(viewHolder.getAdapterPosition()).getDeskripsi_restoran());
                i.putExtra("imgThumb", mData.get(viewHolder.getAdapterPosition()).getThumb_restoran());
                i.putExtra("tvNavigasii", mData.get(viewHolder.getAdapterPosition()).getNavigasi_restoran());
                i.putExtra("email", mData.get(viewHolder.getAdapterPosition()).getEmail_restoran());
                i.putExtra("alamat", mData.get(viewHolder.getAdapterPosition()).getAlamat_restoran());
                i.putExtra("notelp", mData.get(viewHolder.getAdapterPosition()).getNotelp_restoran());
                i.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
                mContext.startActivity(i);
            }
        });

        viewHolder.btnNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(mContext, NonAktifRestoranActivity.class);
                j.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
                j.putExtra("nonaktif", mData.get(viewHolder.getAdapterPosition()).getNon_aktif());
                mContext.startActivity(j);
            }
        });

        viewHolder.btnAktif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(mContext, AktifkanRestoranActivity.class);
                k.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
                k.putExtra("nonaktif", mData.get(viewHolder.getAdapterPosition()).getNon_aktif());
                mContext.startActivity(k);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVRestoranAdmin.MyViewHolder holder, int position) {
        String A = mData.get(position).getNon_aktif();

        if (A.equals("1")){
            holder.tvNamaWisata.setText(mData.get(position).getNama_restoran());
            //Load Image
            Glide.with(mContext).load(mData.get(position).getThumb_restoran()).apply(option).into(holder.img_thumb);
        }else {
            holder.btnEdit.setVisibility(View.GONE);
            holder.btnNon.setVisibility(View.GONE);
            holder.llHide.setVisibility(View.VISIBLE);
            holder.tvNamaWisata.setText(mData.get(position).getNama_restoran());
            holder.btnAktif.setVisibility(View.VISIBLE);
            //Load Image
            Glide.with(mContext).load(mData.get(position).getThumb_restoran()).apply(option).into(holder.img_thumb);
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

            tvNamaWisata = itemView.findViewById(R.id.tvRestoran01);
            img_thumb = itemView.findViewById(R.id.imgThumb03);
            btnEdit = itemView.findViewById(R.id.btnEdit012);
            btnNon = itemView.findViewById(R.id.btnNonAktif012);
            llHide = itemView.findViewById(R.id.llManipulasi00011);
            btnAktif = itemView.findViewById(R.id.aktifkan129);
        }
    }

    @Override
    public Filter getFilter() {
        return pariwisataFilter;
    }

    private Filter pariwisataFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Restoran> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mData);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Restoran item : mData){
                    if (item.getNama_restoran().toLowerCase().contains(filterPattern)){
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
