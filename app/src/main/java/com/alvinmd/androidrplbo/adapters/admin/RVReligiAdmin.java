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
import com.alvinmd.androidrplbo.activity.admin.religi.AktifkanReligiActivity;
import com.alvinmd.androidrplbo.activity.admin.religi.UbahReligiActivity;
import com.alvinmd.androidrplbo.activity.admin.religi.NonAktifReligiActivity;
import com.alvinmd.androidrplbo.model.WisataReligi;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class RVReligiAdmin extends RecyclerView.Adapter<RVReligiAdmin.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<WisataReligi> mData;
    RequestOptions option;
    private int i;

    public RVReligiAdmin(Context mContext, List<WisataReligi> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //Request option for glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    public RVReligiAdmin(List<WisataReligi> mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public RVReligiAdmin.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_religi_admin, parent, false);

        final RVReligiAdmin.MyViewHolder viewHolder = new RVReligiAdmin.MyViewHolder(view);
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, UbahReligiActivity.class);
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
                Intent j = new Intent(mContext, NonAktifReligiActivity.class);
                j.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
                j.putExtra("nonaktif", mData.get(viewHolder.getAdapterPosition()).getNon_aktif());
                mContext.startActivity(j);
            }
        });

        viewHolder.btnAktif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(mContext, AktifkanReligiActivity.class);
                k.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
                k.putExtra("nonaktif", mData.get(viewHolder.getAdapterPosition()).getNon_aktif());
                mContext.startActivity(k);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVReligiAdmin.MyViewHolder holder, int position) {
        String A = mData.get(position).getNon_aktif();

        if (A.equals("1")){
            holder.tvNamaWisata.setText(mData.get(position).getNama_wisata());
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

            tvNamaWisata = itemView.findViewById(R.id.tvHomestay01);
            img_thumb = itemView.findViewById(R.id.imgThumb01);
            btnEdit = itemView.findViewById(R.id.btnEdit01);
            btnNon = itemView.findViewById(R.id.btnNonAktif01);
            llHide = itemView.findViewById(R.id.llManipulasi0001);
            btnAktif = itemView.findViewById(R.id.aktifkan125);
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
