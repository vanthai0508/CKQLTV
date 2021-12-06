package com.term.ckqltv.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.term.ckqltv.DTO.LSDTO;
import com.term.ckqltv.R;

import java.util.List;

public class Adapter_LS extends BaseAdapter {
    Context context;
    int layout;
    List<LSDTO> lsdtoList;
    ViewHolder viewHolder;

    public Adapter_LS(Context context, int layout, List<LSDTO> loaisachdtos){
        this.context = context;
        this.layout = layout;
        this.lsdtoList = loaisachdtos;
    }
    @Override
    public int getCount() {
        return lsdtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return lsdtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lsdtoList.get(position).getMaLoai();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        //nếu lần đầu gọi view
        if(view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            //truyền component vào viewholder để ko gọi findview ở những lần hiển thị khác
            viewHolder.img_Loai_HinhLoai = (ImageView)view.findViewById(R.id.img_loai_HinhLoai);
            viewHolder.txt_Loai_TenLoai = (TextView)view.findViewById(R.id.txt_loai_TenLoai);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        LSDTO loaisachdto = lsdtoList.get(position);

        viewHolder.txt_Loai_TenLoai.setText(loaisachdto.getTenLoai());

        byte[] loaiimage = loaisachdto.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(loaiimage,0,loaiimage.length);
        viewHolder.img_Loai_HinhLoai.setImageBitmap(bitmap);

        return view;
    }

    //tạo viewholer lưu trữ component
    public class ViewHolder{
        TextView txt_Loai_TenLoai;
        ImageView img_Loai_HinhLoai;
    }
}
