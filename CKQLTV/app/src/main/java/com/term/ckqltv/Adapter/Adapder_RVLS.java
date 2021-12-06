package com.term.ckqltv.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.term.ckqltv.DTO.LSDTO;
import com.term.ckqltv.R;

import java.util.List;

public class Adapder_RVLS extends RecyclerView.Adapter<Adapder_RVLS.ViewHolder> {
    Context context;
    int layout;
    List<LSDTO> loaisachdtolist;

    public Adapder_RVLS(Context context, int layout, List<LSDTO> loaisachdtolist) {
        this.context = context;
        this.layout = layout;
        this.loaisachdtolist = loaisachdtolist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapder_RVLS.ViewHolder holder, int position) {
        LSDTO loaisachdto = loaisachdtolist.get(position);
        holder.txt_loai_TenLoai.setText(loaisachdto.getTenLoai());
        byte[] categoryimage = loaisachdto.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(categoryimage,0,categoryimage.length);
        holder.img_loai_HinhLoai.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return loaisachdtolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_loai_TenLoai;
        ImageView img_loai_HinhLoai;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txt_loai_TenLoai = itemView.findViewById(R.id.txt_loai_TenLoai);
            img_loai_HinhLoai = itemView.findViewById(R.id.img_loai_HinhLoai);
        }
    }
}
