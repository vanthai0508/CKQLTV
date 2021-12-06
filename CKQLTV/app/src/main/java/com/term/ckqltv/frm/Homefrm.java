package com.term.ckqltv.frm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.term.ckqltv.Adapter.Adapder_RVLS;
import com.term.ckqltv.Adapter.Adapter_RVTK;
import com.term.ckqltv.DAO.DONDATDAO;
import com.term.ckqltv.DAO.LSDAO;
import com.term.ckqltv.DTO.DONDATDTO;
import com.term.ckqltv.DTO.LSDTO;
import com.term.ckqltv.R;
import com.term.ckqltv.Vieww.AllLS;
import com.term.ckqltv.Vieww.Home;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Homefrm extends Fragment implements View.OnClickListener {

    RecyclerView rcv_displayhome_LoaiMon, rcv_displayhome_DonTrongNgay;
    RelativeLayout layout_home_tk, layout_home_Xemloai, layout_home_XemKH;
    TextView txt_home_AllCategory, txt_home_AllStatistic;
    LSDAO lsdao;
    DONDATDAO dondatdao;
    List<LSDTO> lsdtoList;
    List<LSDAO> lsdaoList;
    List<DONDATDTO> dondatdtoList;
    Adapder_RVLS adapterrvls;
    Adapter_RVTK adapterrvtk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trangchu,container,false);
        ((Home)getActivity()).getSupportActionBar().setTitle("Trang chủ");
        setHasOptionsMenu(true);

        //region Lấy dối tượng view
        rcv_displayhome_LoaiMon = (RecyclerView)view.findViewById(R.id.rcv_home_LoaiSach);
        rcv_displayhome_DonTrongNgay = (RecyclerView)view.findViewById(R.id.rcv_home_DonTrongNgay);


        layout_home_Xemloai = (RelativeLayout)view.findViewById(R.id.layout_home_XemDS);
        layout_home_XemKH = (RelativeLayout)view.findViewById(R.id.layout_home_XemKH);
        txt_home_AllCategory = (TextView) view.findViewById(R.id.txt_home_ViewAllLoai);
        txt_home_AllStatistic = (TextView) view.findViewById(R.id.txt_home_ViewAllStatistic);
        //endregion

        //khởi tạo kết nối
        lsdao = new LSDAO(getActivity());
        dondatdao = new DONDATDAO(getActivity());

        HienThiDSLoai();
        HienThiDonTrongNgay();

        layout_home_tk.setOnClickListener(this);

        layout_home_Xemloai.setOnClickListener(this);
        layout_home_XemKH.setOnClickListener(this);
        txt_home_AllCategory.setOnClickListener(this);
        txt_home_AllStatistic.setOnClickListener(this);

        return view;
    }

    private void HienThiDSLoai(){
        rcv_displayhome_LoaiMon.setHasFixedSize(true);
        rcv_displayhome_LoaiMon.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        lsdtoList = lsdao.LayDSLoaiSach();
        adapterrvls = new Adapder_RVLS(getActivity(),R.layout.loai,lsdtoList);
        rcv_displayhome_LoaiMon.setAdapter(adapterrvls);
        adapterrvls.notifyDataSetChanged();
    }

    private void HienThiDonTrongNgay(){
        rcv_displayhome_DonTrongNgay.setHasFixedSize(true);
        rcv_displayhome_DonTrongNgay.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String ngaydat= dateFormat.format(calendar.getTime());

        dondatdtoList = dondatdao.LayDSDonDatNgay(ngaydat);
        adapterrvtk = new Adapter_RVTK(getActivity(),R.layout.thongke,dondatdtoList);
        rcv_displayhome_DonTrongNgay.setAdapter(adapterrvtk);
        adapterrvtk.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.navigation_view_trangchu);
        switch (id){
         //   case R.id.lay:

            case R.id.txt_home_ViewAllStatistic:
                FragmentTransaction tranDisplayStatistic = getActivity().getSupportFragmentManager().beginTransaction();
            //    tranDisplayStatistic.replace(R.id.contentView,new DisplayStatisticFragment());
                tranDisplayStatistic.addToBackStack(null);
                tranDisplayStatistic.commit();
                navigationView.setCheckedItem(R.id.nav_statistic);

                break;



            case R.id.layout_home_XemDS:
                Intent iAddCategory = new Intent(getActivity(), AllLS.class);
                startActivity(iAddCategory);
                navigationView.setCheckedItem(R.id.nav_category);

                break;
            case R.id.layout_home_XemKH:
                FragmentTransaction tranDisplayStaff= getActivity().getSupportFragmentManager().beginTransaction();
            //    tranDisplayStaff.replace(R.id.contentView,new DisplayStaffFragment());
                tranDisplayStaff.addToBackStack(null);
                tranDisplayStaff.commit();
                navigationView.setCheckedItem(R.id.nav_staff);

                break;

            case R.id.txt_home_ViewAllLoai:
                FragmentTransaction tranDisplayCategory = getActivity().getSupportFragmentManager().beginTransaction();
             //   tranDisplayCategory.replace(R.id.contentView,new DisplayCategoryFragment());
                tranDisplayCategory.addToBackStack(null);
                tranDisplayCategory.commit();
                navigationView.setCheckedItem(R.id.nav_category);

                break;

        }
    }
}
