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
import com.alvinmd.androidrplbo.activity.pengguna.religi.DetailReligiActivity;
import com.alvinmd.androidrplbo.model.WisataReligi;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class RVReligi extends RecyclerView.Adapter<RVReligi.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<WisataReligi> mData;
    RequestOptions option;

    public RVReligi(Context mContext, List<WisataReligi> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    public RVReligi(List<WisataReligi> mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public RVReligi.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_religi, parent, false);
        final RVReligi.MyViewHolder viewHolder = new RVReligi.MyViewHolder(view);
        viewHolder.view_containerR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailReligiActivity.class);
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
    public void onBindViewHolder(@NonNull RVReligi.MyViewHolder holder, int position) {

        String b = mData.get(position).getNon_aktif();

        if (b.equals("1")){
            holder.tvNamaWisataR.setText(mData.get(position).getNama_wisata());
            holder.tvDeskripsiR.setText(mData.get(position).getDeskripsi_wisata());
            holder.tv_navigasiR.setText(mData.get(position).getNavigasi_wisata());
            holder.tvNoTelpR.setText(mData.get(position).getNotelp_wisata());
            holder.tvEmailR.setText(mData.get(position).getEmail_wisata());
            holder.tvAlamatR.setText(mData.get(position).getAlamat_wisata());

            //Load Image

            Glide.with(mContext).load(mData.get(position).getThumb_wisata()).apply(option).into(holder.img_thumbR);
        }else {
            holder.tvNamaWisataR.setText(mData.get(position).getNama_wisata());
            holder.llmanupulasi.setVisibility(View.VISIBLE);

            Glide.with(mContext).load(mData.get(position).getThumb_wisata()).apply(option).into(holder.img_thumbR);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaWisataR, tvDeskripsiR, tv_navigasiR, tvEmailR, tvNoTelpR, tvAlamatR;
        ImageView img_thumbR;
        RelativeLayout view_containerR;
        LinearLayout llmanupulasi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaWisataR = itemView.findViewById(R.id.tvHomestayR);
            tvDeskripsiR = itemView.findViewById(R.id.tvDeskripsiR);
            img_thumbR = itemView.findViewById(R.id.imgThumbR);
            tv_navigasiR = itemView.findViewById(R.id.tvNavigasiiR);
            tvEmailR = itemView.findViewById(R.id.emailR);
            tvAlamatR = itemView.findViewById(R.id.alamatR);
            tvNoTelpR = itemView.findViewById(R.id.notelpR);
            view_containerR = itemView.findViewById(R.id.container1R);
            llmanupulasi = itemView.findViewById(R.id.llManipulasi002);
        }
    }

    @Override
    public Filter getFilter() {
        return pariwisataFilter;
    }

    private Filter pariwisataFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<WisataReligi> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mData);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (WisataReligi item : mData){
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
