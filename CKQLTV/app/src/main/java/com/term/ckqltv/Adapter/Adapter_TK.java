package com.term.ckqltv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.term.ckqltv.DAO.KHDAO;
import com.term.ckqltv.DTO.DONDATDTO;
import com.term.ckqltv.DTO.KHDTO;
import com.term.ckqltv.R;

import java.util.List;

public class Adapter_TK extends BaseAdapter {
    Context context;
    int layout;
    List<DONDATDTO> dondatdtoList;
    ViewHolder viewHolder;
    KHDAO khdao;

    public Adapter_TK(Context context, int layout, List<DONDATDTO> dondatdtoList) {
        this.context = context;
        this.layout = layout;
        this.dondatdtoList = dondatdtoList;
        this.khdao = khdao;
    }

    @Override
    public int getCount() {
        return dondatdtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return dondatdtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dondatdtoList.get(position).getMaDonDat();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolder.txt_tk_MaDon = (TextView)view.findViewById(R.id.txt_tk_MaDon);
            viewHolder.txt_tk_NgayDat = (TextView)view.findViewById(R.id.txt_tk_NgayDat);
            viewHolder.txt_tk_TenKH = (TextView)view.findViewById(R.id.txt_tk_TenKH);
            viewHolder.txt_tk_TongTien = (TextView)view.findViewById(R.id.txt_tk_TongTien);
            viewHolder.txt_tk_TrangThai = (TextView)view.findViewById(R.id.txt_tk_TrangThai);
      //      viewHolder.txt_customstatistic_BanDat = (TextView)view.findViewById(R.id.txt_customstatistic_BanDat);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DONDATDTO dondatdto = dondatdtoList.get(position);

        viewHolder.txt_tk_MaDon.setText("Mã đơn: "+dondatdto.getMaDonDat());
        viewHolder.txt_tk_NgayDat.setText(dondatdto.getNgayDat());
        viewHolder.txt_tk_TongTien.setText(dondatdto.getTongTien()+" VNĐ");
        if (dondatdto.getTinhTrang().equals("true"))
        {
            viewHolder.txt_tk_TrangThai.setText("Đã thanh toán");
        }else {
            viewHolder.txt_tk_TrangThai.setText("Chưa thanh toán");
        }
        KHDTO khdto = khdao.LayKHTheoMa(dondatdto.getMaKH());
        viewHolder.txt_tk_TenKH.setText(khdto.getHOTENKH());
     //   viewHolder.txt_tk_BanDat.setText(banAnDAO.LayTenBanTheoMa(donDatDTO.getMaBan()));

        return view;
    }
    public class ViewHolder{
        TextView txt_tk_MaDon, txt_tk_NgayDat, txt_tk_TenKH
                ,txt_tk_TongTien,txt_tk_TrangThai;

    }
}
