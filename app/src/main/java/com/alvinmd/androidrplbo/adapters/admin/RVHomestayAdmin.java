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
import com.alvinmd.androidrplbo.activity.admin.homestay.UbahHomestayActivity;
import com.alvinmd.androidrplbo.activity.admin.homestay.NonAktifHomestay;
import com.alvinmd.androidrplbo.activity.admin.konvensional.AktifkanKonvensionalActivity;
import com.alvinmd.androidrplbo.model.Homestay;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class RVHomestayAdmin extends RecyclerView.Adapter<RVHomestayAdmin.MyViewHolder> implements Filterable {
    private Context mContext;
    private List<Homestay> mData;
    RequestOptions option;
    private int i;

    public RVHomestayAdmin(Context mContext, List<Homestay> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //Request option for glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    public RVHomestayAdmin(List<Homestay> mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public RVHomestayAdmin.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_homestay_admin, parent, false);

        final RVHomestayAdmin.MyViewHolder viewHolder = new RVHomestayAdmin.MyViewHolder(view);
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, UbahHomestayActivity.class);
                i.putExtra("tvHomestay", mData.get(viewHolder.getAdapterPosition()).getNama_homestay());
                i.putExtra("tvDeskripsi", mData.get(viewHolder.getAdapterPosition()).getDeskripsi_homestay());
                i.putExtra("imgThumb", mData.get(viewHolder.getAdapterPosition()).getThumb_homestay());
                i.putExtra("tvNavigasii", mData.get(viewHolder.getAdapterPosition()).getNavigasi_homestay());
                i.putExtra("email", mData.get(viewHolder.getAdapterPosition()).getEmail_homestay());
                i.putExtra("alamat", mData.get(viewHolder.getAdapterPosition()).getAlamat_homestay());
                i.putExtra("notelp", mData.get(viewHolder.getAdapterPosition()).getNotelp_homestay());
                i.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
                i.putExtra("nonaktif", mData.get(viewHolder.getAdapterPosition()).getNon_aktif());
                mContext.startActivity(i);
            }
        });

        viewHolder.btnNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(mContext, NonAktifHomestay.class);
                j.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
                j.putExtra("nonaktif", mData.get(viewHolder.getAdapterPosition()).getNon_aktif());
                mContext.startActivity(j);
            }
        });

        viewHolder.btnAktif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(mContext, AktifkanHomestayActivity.class);
                k.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
                k.putExtra("nonaktif", mData.get(viewHolder.getAdapterPosition()).getNon_aktif());
                mContext.startActivity(k);
            }
        });

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RVHomestayAdmin.MyViewHolder holder, int position) {
        String A = mData.get(position).getNon_aktif();

        if (A.equals("1")){
            holder.tvNamaWisata.setText(mData.get(position).getNama_homestay());
            //Load Image
            Glide.with(mContext).load(mData.get(position).getThumb_homestay()).apply(option).into(holder.img_thumb);
        }else {
            holder.btnEdit.setVisibility(View.GONE);
            holder.btnNon.setVisibility(View.GONE);
            holder.llHide.setVisibility(View.VISIBLE);
            holder.tvNamaWisata.setText(mData.get(position).getNama_homestay());
            holder.btnAktif.setVisibility(View.VISIBLE);
            //Load Image
            Glide.with(mContext).load(mData.get(position).getThumb_homestay()).apply(option).into(holder.img_thumb);
        }

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaWisata, tvDeskripsi, tv_navigasi, tvEmail, tvNoTelp, tvAlamat, tvId, tvNon, tvIdNon;
        ImageView img_thumb;
        Button btnEdit,btnNon, btnAktif;
        RelativeLayout view_container;
        LinearLayout llHide;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaWisata = itemView.findViewById(R.id.tvHomestay02);
            img_thumb = itemView.findViewById(R.id.imgThumb02);
            btnEdit = itemView.findViewById(R.id.btnEdit03);
            btnNon = itemView.findViewById(R.id.btnNonAktif03);
            llHide = itemView.findViewById(R.id.llManipulasi004);
            btnAktif = itemView.findViewById(R.id.aktifkan);
            tvIdNon = itemView.findViewById(R.id.tvNon1);
        }
    }

    @Override
    public Filter getFilter() {
        return pariwisataFilter;
    }

    private Filter pariwisataFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Homestay> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mData);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Homestay item : mData){
                    if (item.getNama_homestay().toLowerCase().contains(filterPattern)){
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
