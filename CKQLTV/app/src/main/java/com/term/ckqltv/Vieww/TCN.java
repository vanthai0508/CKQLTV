package com.term.ckqltv.Vieww;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.term.ckqltv.DAO.KHDAO;
import com.term.ckqltv.DTO.KHDTO;
import com.term.ckqltv.R;
import com.term.ckqltv.databinding.ActivityTcnBinding;

public class TCN extends AppCompatActivity {
    private ActivityTcnBinding binding;
   KHDTO khdto;
    KHDAO khdao;
    //public int makh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcn);

        khdao=new KHDAO(this);



        binding = ActivityTcnBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        Intent intent = getIntent();
        int makh;
        makh=intent.getIntExtra("makh",0);

        khdto=new KHDTO();
        khdto=khdao.LayKHTheoMa(makh);

        binding.etHoten.setText(khdto.getHOTENKH().toString());
        binding.etTendangnhap.setText(khdto.getTENDN().toString());
        binding.etEmail.setText(khdto.getEMAIL().toString());
        binding.etSdt.setText(khdto.getSDT().toString());
        binding.etMk.setText(khdto.getMATKHAU().toString());

        binding.btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   khdto=new KHDTO();
                khdto.setHOTENKH(binding.etHoten.getText().toString());
                khdto.setTENDN(binding.etTendangnhap.getText().toString());
                khdto.setEMAIL(binding.etEmail.getText().toString());
                khdto.setSDT(binding.etSdt.getText().toString());
                khdto.setMATKHAU(binding.etMk.getText().toString());

               khdao.SuaTK(khdto,makh);
            }
        });


    }

    public void backtrangchu(View view){

        Intent intent = new Intent(getApplicationContext(),Homes.class);
        startActivity(intent);
        finish();



    }
}