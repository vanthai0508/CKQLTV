package com.term.ckqltv.Vieww;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.term.ckqltv.Adapter.Adapter_TT;
import com.term.ckqltv.DAO.KHDAO;
import com.term.ckqltv.DAO.TTDAO;
import com.term.ckqltv.DTO.KHDTO;
import com.term.ckqltv.DTO.TTDTO;
import com.term.ckqltv.R;

import java.util.List;

public class SuaTK extends AppCompatActivity {
    ImageView img_suatk_backbtn;
    TextView txt_suatk_MaDon,txt_suatk_NgayDat
            ,txt_suatk_TenKH,txt_suatk_TongTien;
    GridView gvDetailStatistic;
    int madon, makh;
    String ngaydat, tongtien;
    KHDAO khdao;

    List<TTDTO> ttdtoList;
    TTDAO ttdao;
    Adapter_TT adapter_tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_tk);



        //Lấy thông tin từ display statistic
        Intent intent = getIntent();
        madon = intent.getIntExtra("madon",0);
        makh = intent.getIntExtra("manv",0);

        ngaydat = intent.getStringExtra("ngaydat");
        tongtien = intent.getStringExtra("tongtien");

        //region Thuộc tính bên view
        img_suatk_backbtn = (ImageView)findViewById(R.id.img_suatk_backbtn);
        txt_suatk_MaDon = (TextView)findViewById(R.id.txt_suatk_MaDon);
        txt_suatk_NgayDat = (TextView)findViewById(R.id.txt_suatk_NgayDat);

        txt_suatk_TenKH = (TextView)findViewById(R.id.txt_suatk_TenKH);
        txt_suatk_TongTien = (TextView)findViewById(R.id.txt_suatk_TongTien);
        gvDetailStatistic = (GridView)findViewById(R.id.gvDetailStatistic);
        //endregion

        //khởi tạo lớp dao mở kết nối csdl
        khdao = new KHDAO(this);

        ttdao = new TTDAO(this);

        //chỉ hiển thị nếu lấy đc mã đơn đc chọn
        if (madon !=0){
            txt_suatk_MaDon.setText("Mã đơn: "+madon);
            txt_suatk_NgayDat.setText(ngaydat);
            txt_suatk_TongTien.setText(tongtien+" VNĐ");

            KHDTO khdto = khdao.LayKHTheoMa(makh);
            txt_suatk_TenKH.setText(khdto.getHOTENKH());


            HienThiDSCTDD();
        }

        img_suatk_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }

    private void HienThiDSCTDD(){
        ttdtoList = ttdao.LayDSSachTheoMaDon(madon);
        adapter_tt = new Adapter_TT(this,R.layout.thanhtoan,ttdtoList);
        gvDetailStatistic.setAdapter(adapter_tt);
        adapter_tt.notifyDataSetChanged();
    }
}