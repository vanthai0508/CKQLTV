package com.term.ckqltv.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.term.ckqltv.DTO.TTDTO;
import com.term.ckqltv.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_TT extends BaseAdapter {

    Context context;
    int layout;
    List<TTDTO> ttdtoList;
    ViewHolder viewHolder;

    public Adapter_TT(Context context, int layout, List<TTDTO> ttdtoList){
        this.context = context;
        this.layout = layout;
        this.ttdtoList = ttdtoList;
    }

    @Override
    public int getCount() {
        return ttdtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return ttdtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolder.img_tt_HinhSach = (CircleImageView)view.findViewById(R.id.img_tt_HinhSach);
            viewHolder.txt_tt_TenSach = (TextView)view.findViewById(R.id.txt_tt_TenSach);
            viewHolder.txt_tt_SoLuong = (TextView)view.findViewById(R.id.txt_tt_SoLuong);
            viewHolder.txt_tt_GiaTien = (TextView)view.findViewById(R.id.txt_tt_GiaTien);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }
        TTDTO ttdto = ttdtoList.get(position);


        viewHolder.txt_tt_TenSach.setText(ttdto.getTenSach());
        viewHolder.txt_tt_SoLuong.setText(String.valueOf(ttdto.getSoLuong()));
        viewHolder.txt_tt_GiaTien.setText(String.valueOf(ttdto.getGiaTien())+" đ");

        byte[] paymentimg = ttdto.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(paymentimg,0,paymentimg.length);
        viewHolder.img_tt_HinhSach.setImageBitmap(bitmap);

        return view;
    }

    public class ViewHolder{
        CircleImageView img_tt_HinhSach;
        TextView txt_tt_TenSach, txt_tt_SoLuong, txt_tt_GiaTien;
    }
}
