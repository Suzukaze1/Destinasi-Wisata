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
import com.alvinmd.androidrplbo.activity.pengguna.homestay.DetaillHomestayActivity;
import com.alvinmd.androidrplbo.model.Homestay;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class RVHomestay extends RecyclerView.Adapter<RVHomestay.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<Homestay> mData;
    RequestOptions option;

    public RVHomestay(Context mContext, List<Homestay> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    public RVHomestay(List<Homestay> mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public RVHomestay.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_homestay, parent, false);
        final RVHomestay.MyViewHolder viewHolder = new RVHomestay.MyViewHolder(view);
        viewHolder.view_containerR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetaillHomestayActivity.class);
                i.putExtra("tvHomestayH", mData.get(viewHolder.getAdapterPosition()).getNama_homestay());
                i.putExtra("tvDeskripsiH", mData.get(viewHolder.getAdapterPosition()).getDeskripsi_homestay());
                i.putExtra("imgThumbH", mData.get(viewHolder.getAdapterPosition()).getThumb_homestay());
                i.putExtra("tvNavigasiiH", mData.get(viewHolder.getAdapterPosition()).getNavigasi_homestay());
                i.putExtra("emailH", mData.get(viewHolder.getAdapterPosition()).getEmail_homestay());
                i.putExtra("alamatH", mData.get(viewHolder.getAdapterPosition()).getAlamat_homestay());
                i.putExtra("notelpH", mData.get(viewHolder.getAdapterPosition()).getNotelp_homestay());

                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVHomestay.MyViewHolder holder, int position) {

        String d = mData.get(position).getNon_aktif();

        if (d.equals("1")){
            holder.tvNamaH.setText(mData.get(position).getNama_homestay());
            holder.tvDeskripsiH.setText(mData.get(position).getDeskripsi_homestay());
            holder.tv_navigasiH.setText(mData.get(position).getNavigasi_homestay());
            holder.tvEmailH.setText(mData.get(position).getEmail_homestay());
            holder.tvAlamatH.setText(mData.get(position).getAlamat_homestay());

            //Load Image

            Glide.with(mContext).load(mData.get(position).getThumb_homestay()).apply(option).into(holder.img_thumbH);
        }else {
            holder.tvNamaH.setText(mData.get(position).getNama_homestay());
            holder.llManipulasi.setVisibility(View.VISIBLE);

            Glide.with(mContext).load(mData.get(position).getThumb_homestay()).into(holder.img_thumbH);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return pariwisataFilter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaH, tvDeskripsiH, tv_navigasiH, tvEmailH, tvNoTelH, tvAlamatH;
        ImageView img_thumbH;
        RelativeLayout view_containerR;
        LinearLayout llManipulasi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaH = itemView.findViewById(R.id.tvHomestayH);
            tvDeskripsiH = itemView.findViewById(R.id.tvDeskripsiH);
            tv_navigasiH = itemView.findViewById(R.id.tvNavigasiiH);
            tvEmailH = itemView.findViewById(R.id.emailH);
            tvNoTelH = itemView.findViewById(R.id.notelpH);
            tvAlamatH = itemView.findViewById(R.id.alamatH);
            img_thumbH = itemView.findViewById(R.id.imgThumbH);
            view_containerR = itemView.findViewById(R.id.container1H);
            llManipulasi = itemView.findViewById(R.id.llManipulasi003);
        }
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
