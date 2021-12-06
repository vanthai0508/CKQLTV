package com.term.ckqltv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.term.ckqltv.DAO.KHDAO;
import com.term.ckqltv.DTO.DONDATDTO;
import com.term.ckqltv.DTO.KHDTO;
import com.term.ckqltv.R;

import java.util.List;

public class Adapter_RVTK extends RecyclerView.Adapter<Adapter_RVTK.ViewHolder>{
    Context context;
    int layout;
    List<DONDATDTO> dondatdtoList;
    KHDAO khdao;

    public Adapter_RVTK(Context context, int layout, List<DONDATDTO> dondatdtoList) {
        this.context = context;
        this.layout = layout;
        this.dondatdtoList = dondatdtoList;
        this.khdao = khdao;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter_RVTK.ViewHolder holder, int position) {
        DONDATDTO dondatdto = dondatdtoList.get(position);
        holder.txt_tk_MaDon.setText("Mã đơn: "+dondatdto.getMaDonDat());
        holder.txt_tk_NgayDat.setText(dondatdto.getNgayDat());
        if(dondatdto.getTongTien().equals("0"))
        {
            holder.txt_tk_TongTien.setVisibility(View.INVISIBLE);
        }else {
            holder.txt_tk_TongTien.setVisibility(View.VISIBLE);
        }

        if (dondatdto.getTinhTrang().equals("true"))
        {
            holder.txt_tk_TrangThai.setText("Đã thanh toán");
        }else {
            holder.txt_tk_TrangThai.setText("Chưa thanh toán");
        }
        KHDTO khdto = khdao.LayKHTheoMa(dondatdto.getMaKH());
        holder.txt_tk_TenKH.setText(khdto.getHOTENKH());
   //     holder.txt_customstatistic_BanDat.setText(banAnDAO.LayTenBanTheoMa(donDatDTO.getMaBan()));
    }

    @Override
    public int getItemCount() {
        return dondatdtoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_tk_MaDon, txt_tk_NgayDat, txt_tk_TenKH,
                txt_tk_BanDat, txt_tk_TongTien,txt_tk_TrangThai;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txt_tk_MaDon = itemView.findViewById(R.id.txt_tk_MaDon);
            txt_tk_NgayDat = itemView.findViewById(R.id.txt_tk_NgayDat);
            txt_tk_TenKH = itemView.findViewById(R.id.txt_tk_TenKH);
      //      txt_customstatistic_BanDat = itemView.findViewById(R.id.txt_customstatistic_BanDat);
            txt_tk_TongTien = itemView.findViewById(R.id.txt_tk_TongTien);
            txt_tk_TrangThai = itemView.findViewById(R.id.txt_tk_TrangThai);
        }
    }

}
