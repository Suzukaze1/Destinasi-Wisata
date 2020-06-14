package com.alvinmd.androidrplbo.adapters.pengguna;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alvinmd.androidrplbo.activity.pengguna.konvensional.DetailKonvensionalActivity;
import com.alvinmd.androidrplbo.R;
import com.alvinmd.androidrplbo.model.WisataKonvensional;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class RVKonvensional extends RecyclerView.Adapter<RVKonvensional.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<WisataKonvensional> mData;

    RequestOptions option;

    public RVKonvensional(Context mContext, List<WisataKonvensional> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //Request option for glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    public RVKonvensional(List<WisataKonvensional> mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_konvensional, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailKonvensionalActivity.class);
                i.putExtra("tvHomestay", mData.get(viewHolder.getAdapterPosition()).getNama_wisata());
                i.putExtra("tvDeskripsi", mData.get(viewHolder.getAdapterPosition()).getDeskripsi_wisata());
                i.putExtra("imgThumb", mData.get(viewHolder.getAdapterPosition()).getThumb_wisata());
                i.putExtra("tvNavigasii", mData.get(viewHolder.getAdapterPosition()).getNavigasi_wisata());
                i.putExtra("email", mData.get(viewHolder.getAdapterPosition()).getEmail_wisata());
                i.putExtra("alamat", mData.get(viewHolder.getAdapterPosition()).getAlamat_wisata());
                i.putExtra("notelp", mData.get(viewHolder.getAdapterPosition()).getNotelp_wisata());

                mContext.startActivity(i);
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

            //Load Image

            Glide.with(mContext).load(mData.get(position).getThumb_wisata()).apply(option).into(holder.img_thumb);
        }else {
            holder.tvNamaWisata.setText(mData.get(position).getNama_wisata());
            holder.manipulasiKonvensional.setVisibility(View.VISIBLE);

            Glide.with(mContext).load(mData.get(position).getThumb_wisata()).apply(option).into(holder.img_thumb);
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaWisata, tvDeskripsi, tv_navigasi, tvEmail, tvNoTelp, tvAlamat;
        ImageView img_thumb;
        RelativeLayout view_container;
        LinearLayout manipulasiKonvensional;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaWisata = itemView.findViewById(R.id.tvHomestay);
            tvDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
            img_thumb = itemView.findViewById(R.id.imgThumb);
            view_container = itemView.findViewById(R.id.container1);
            tv_navigasi = itemView.findViewById(R.id.tvNavigasii);
            tvEmail = itemView.findViewById(R.id.email);
            tvAlamat = itemView.findViewById(R.id.alamat);
            tvNoTelp = itemView.findViewById(R.id.notelp);
            manipulasiKonvensional = itemView.findViewById(R.id.llManipulasi001);
        }
    }

    @Override
    public Filter getFilter() {
        return pariwsataFilter;
    }

    private Filter pariwsataFilter = new Filter() {
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
