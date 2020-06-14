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

import com.alvinmd.androidrplbo.R;
import com.alvinmd.androidrplbo.activity.pengguna.restoran.DetailRestoranActivity;
import com.alvinmd.androidrplbo.model.Restoran;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class RVRestoran extends RecyclerView.Adapter<RVRestoran.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<Restoran> mData;
    RequestOptions option;

    public RVRestoran(Context mContext, List<Restoran> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    public RVRestoran(List<Restoran> mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public RVRestoran.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_restoran, parent, false);
        final RVRestoran.MyViewHolder viewHolder = new RVRestoran.MyViewHolder(view);
        viewHolder.view_containerRR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailRestoranActivity.class);
                i.putExtra("tvHomestay", mData.get(viewHolder.getAdapterPosition()).getNama_restoran());
                i.putExtra("tvDeskripsi", mData.get(viewHolder.getAdapterPosition()).getDeskripsi_restoran());
                i.putExtra("imgThumb", mData.get(viewHolder.getAdapterPosition()).getThumb_restoran());
                i.putExtra("tvNavigasii", mData.get(viewHolder.getAdapterPosition()).getNavigasi_restoran());
                i.putExtra("email", mData.get(viewHolder.getAdapterPosition()).getEmail_restoran());
                i.putExtra("alamat", mData.get(viewHolder.getAdapterPosition()).getAlamat_restoran());
                i.putExtra("notelp", mData.get(viewHolder.getAdapterPosition()).getNotelp_restoran());

                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVRestoran.MyViewHolder holder, int position) {

        String c = mData.get(position).getNon_aktif();

        if (c.equals("1")){
            holder.tvNamaR.setText(mData.get(position).getNama_restoran());
            holder.tvDeskripsiR.setText(mData.get(position).getDeskripsi_restoran());
            holder.tv_navigasiR.setText(mData.get(position).getNavigasi_restoran());
            holder.tvNoTelR.setText(mData.get(position).getNotelp_restoran());
            holder.tvEmailR.setText(mData.get(position).getEmail_restoran());
            holder.tvAlamatR.setText(mData.get(position).getAlamat_restoran());

            //Load Image

            Glide.with(mContext).load(mData.get(position).getThumb_restoran()).apply(option).into(holder.img_thumbR);
        }else {
            holder.tvNamaR.setText(mData.get(position).getNama_restoran());
            holder.llmanipulasi.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(mData.get(position).getThumb_restoran()).apply(option).into(holder.img_thumbR);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaR, tvDeskripsiR, tv_navigasiR, tvEmailR, tvNoTelR, tvAlamatR;
        ImageView img_thumbR;
        RelativeLayout view_containerRR;
        LinearLayout llmanipulasi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaR = itemView.findViewById(R.id.tvHomestayRR);
            tvDeskripsiR = itemView.findViewById(R.id.tvDeskripsiRR);
            tv_navigasiR = itemView.findViewById(R.id.tvNavigasiiRR);
            tvEmailR = itemView.findViewById(R.id.emailRR);
            tvNoTelR = itemView.findViewById(R.id.notelpRR);
            tvAlamatR = itemView.findViewById(R.id.alamatRR);
            img_thumbR = itemView.findViewById(R.id.imgThumbRR);
            view_containerRR = itemView.findViewById(R.id.containerRR);
            llmanipulasi =itemView.findViewById(R.id.llManipulasi004);
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
