package com.term.ckqltv.frm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.term.ckqltv.Adapter.Adapter_TK;
import com.term.ckqltv.DAO.DONDATDAO;
import com.term.ckqltv.DTO.DONDATDTO;
import com.term.ckqltv.R;
import com.term.ckqltv.Vieww.Home;
import com.term.ckqltv.Vieww.SuaTK;

import java.util.List;

public class TKfrm extends Fragment {
    ListView lvtk;
    List<DONDATDTO> dondatdtoList;
    DONDATDAO dondatdao;
    Adapter_TK adapter_tk;
    FragmentManager fragmentManager;
    int madon, makh;
    String ngaydat, tongtien;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loai,container,false);
        ((Home)getActivity()).getSupportActionBar().setTitle("Quản lý thống kê");
        setHasOptionsMenu(true);

        lvtk = (ListView)view.findViewById(R.id.lvtk);
        dondatdao = new DONDATDAO(getActivity());

        dondatdtoList = dondatdao.LayDSDonDat();
        adapter_tk = new Adapter_TK(getActivity(),R.layout.thongke,dondatdtoList);
        lvtk.setAdapter(adapter_tk);
        adapter_tk.notifyDataSetChanged();

        lvtk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                madon = dondatdtoList.get(position).getMaDonDat();
                makh = dondatdtoList.get(position).getMaKH();

                ngaydat = dondatdtoList.get(position).getNgayDat();
                tongtien = dondatdtoList.get(position).getTongTien();

                Intent intent = new Intent(getActivity(), SuaTK.class);
                intent.putExtra("madon",madon);
                intent.putExtra("manv",makh);

                intent.putExtra("ngaydat",ngaydat);
                intent.putExtra("tongtien",tongtien);
                startActivity(intent);
            }
        });

        return view;
    }
}
