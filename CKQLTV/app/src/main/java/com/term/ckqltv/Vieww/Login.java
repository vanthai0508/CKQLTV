package com.term.ckqltv.Vieww;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.term.ckqltv.DAO.KHDAO;
import com.term.ckqltv.DTO.KHDTO;
import com.term.ckqltv.R;

public class Login extends AppCompatActivity {
    Button BTN_login_DangNhap, BTN_login_DangKy;
    TextInputLayout TXTL_login_TenDN, TXTL_login_MatKhau;
    KHDAO khdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //thuộc tính view
        TXTL_login_TenDN = (TextInputLayout)findViewById(R.id.txtl_login_TenDN);
        TXTL_login_MatKhau = (TextInputLayout)findViewById(R.id.txtl_login_MatKhau);
        BTN_login_DangNhap = (Button)findViewById(R.id.btn_login_DangNhap);
        BTN_login_DangKy = (Button)findViewById(R.id.btn_login_DangKy);

        khdao = new KHDAO(this);    //khởi tạo kết nối csdl

        BTN_login_DangNhap.setOnClickListener(new View.OnClickListener() {

        @Override
            public void onClick(View v) {
                if(!validateUserName() | !validatePassWord()){
                    return;
                }

                String tenDN = TXTL_login_TenDN.getEditText().getText().toString();
                String matKhau = TXTL_login_MatKhau.getEditText().getText().toString();
                int ktra = khdao.KiemTraDN(tenDN,matKhau);
                int maquyen = khdao.LayQuyenKH(ktra);

                if( ktra!=0){
     //                lưu mã quyền vào shareprefer
                    SharedPreferences sharedPreferences = getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.putInt("maquyen",maquyen);
                    editor.commit();

                    //gửi dữ liệu user qua trang chủ

                    Intent intent = new Intent(Login.this,Homes.class);
                    intent.putExtra("tendn",tenDN);
                    intent.putExtra("makh",ktra);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    //Hàm quay lại màn hình chính
    public void backFromLogin(View view)
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        //tạo animation cho thành phần
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.layoutLogin),"transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }

    //Hàm chuyển qua trang đăng ký
    public void callRegisterFromLogin(View view)
    {
        Intent intent = new Intent(getApplicationContext(),DangKy.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    //region Validate field
    private boolean validateUserName(){
        String val = TXTL_login_TenDN.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            TXTL_login_TenDN.setError("Khong duoc de trong");
            return false;
        }else {
            TXTL_login_TenDN.setError(null);
            TXTL_login_TenDN.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassWord(){
        String val = TXTL_login_MatKhau.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            TXTL_login_MatKhau.setError("khong duoc de trong");
            return false;
        }else{
            TXTL_login_MatKhau.setError(null);
            TXTL_login_MatKhau.setErrorEnabled(false);
            return true;
        }
    }
    //endregion

}