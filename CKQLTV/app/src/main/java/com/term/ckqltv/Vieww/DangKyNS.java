package com.term.ckqltv.Vieww;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.term.ckqltv.DAO.KHDAO;
import com.term.ckqltv.DAO.QUYENDAO;
import com.term.ckqltv.DTO.KHDTO;
import com.term.ckqltv.R;
import java.util.Calendar;

public class DangKyNS extends AppCompatActivity {
    RadioGroup RG_signup_GioiTinh;
    DatePicker DT_signup_NgaySinh;
    Button BTN_signup_next;
    String hoTen,tenDN,eMail,sDT,matKhau,gioiTinh;
    KHDAO khdao;
    QUYENDAO quyendao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_ns);

        RG_signup_GioiTinh = (RadioGroup)findViewById(R.id.rg_signup_GioiTinh);
        DT_signup_NgaySinh = (DatePicker)findViewById(R.id.dt_signup_NgaySinh);
        BTN_signup_next = (Button)findViewById(R.id.btn_signup_next);

        //lấy dữ liệu từ bundle của register1
        Bundle bundle = getIntent().getBundleExtra(DangKy.BUNDLE);
        if(bundle != null) {
            hoTen = bundle.getString("hoten");
            tenDN = bundle.getString("tendn");
            eMail = bundle.getString("email");
            sDT = bundle.getString("sdt");
            matKhau = bundle.getString("matkhau");
        }
        //khởi tạo kết nối csdl
        khdao = new KHDAO(this);
        quyendao = new QUYENDAO(this);

        BTN_signup_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateAge() | !validateGender()){
                    return;
                }

                //lấy các thông tin còn lại
                switch (RG_signup_GioiTinh.getCheckedRadioButtonId()){
                    case R.id.rd_signup_Nam:
                        gioiTinh = "Nam"; break;
                    case R.id.rd_signup_Nu:
                        gioiTinh = "Nữ"; break;
                    case R.id.rd_signup_Khac:
                        gioiTinh = "Khác"; break;
                }
                String ngaySinh = DT_signup_NgaySinh.getDayOfMonth() + "/" + (DT_signup_NgaySinh.getMonth() + 1)
                        +"/"+DT_signup_NgaySinh.getYear();

                //truyền dữ liệu vào obj nhanvienDTO
                KHDTO khdto = new KHDTO();
                khdto.setHOTENKH(hoTen);
                khdto.setTENDN(tenDN);
                khdto.setEMAIL(eMail);
                khdto.setSDT(sDT);
                khdto.setMATKHAU(matKhau);
                khdto.setGIOITINH(gioiTinh);
                khdto.setNGAYSINH(ngaySinh);

                //nếu nhân viên đầu tiên đăng ký sẽ có quyền quản lý
                if(!khdao.KtraTonTaiKH()){
                    quyendao.ThemQuyen("Quản lý");
                    quyendao.ThemQuyen("Nhân viên");
                    khdto.setMAQUYEN(1);
                }else {
                    khdto.setMAQUYEN(2);
                }

                //Thêm nv dựa theo obj nhanvienDTO
                long ktra = khdao.ThemKH(khdto);
                if(ktra != 0){
                    Toast.makeText(DangKyNS.this,"Them thanh cong",Toast.LENGTH_SHORT).show();
                    callLoginFromRegister();
                }else{
                    Toast.makeText(DangKyNS.this,"Them that bai",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //Hàm quay về màn hình trước
    public void backFromRegister2nd(View view){
        Intent intent = new Intent(getApplicationContext(),DangKy.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }

    //Hàm chuyển màn hình khi hoàn thành
    public void callLoginFromRegister(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    //region Validate field
    private boolean validateGender(){
        if(RG_signup_GioiTinh.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"Hãy chọn giới tính",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    private boolean validateAge(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = DT_signup_NgaySinh.getYear();
        int isAgeValid = currentYear - userAge;

        if(isAgeValid < 10){
            Toast.makeText(this,"Bạn không đủ tuổi đăng ký!",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}