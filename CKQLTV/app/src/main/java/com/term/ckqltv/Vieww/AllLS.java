package com.term.ckqltv.Vieww;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.term.ckqltv.DAO.LSDAO;
import com.term.ckqltv.DTO.LSDTO;
import com.term.ckqltv.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AllLS extends AppCompatActivity implements View.OnClickListener {

    Button BTN_addls_TaoLoai;
    ImageView IMG_addls_back, IMG_addls_ThemHinh;
    TextView TXT_addls_title;
    TextInputLayout TXTL_addls_TenLoai;
    LSDAO lsdao;
    int maloai = 0;
    Bitmap bitmapold;   //Bitmap dạng ảnh theo ma trận các pixel

    //dùng result launcher do activityforresult ko dùng đc nữa
    ActivityResultLauncher<Intent> resultLauncherOpenIMG = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Uri uri = result.getData().getData();
                        try{
                            InputStream inputStream = getContentResolver().openInputStream(uri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            IMG_addls_ThemHinh.setImageBitmap(bitmap);
                        }catch (FileNotFoundException e){
                            e.printStackTrace();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ls);

        lsdao = new LSDAO(this);  //khởi tạo đối tượng dao kết nối csdl

        //region Lấy đối tượng view
        BTN_addls_TaoLoai = (Button)findViewById(R.id.btn_addLS_TaoLoai);
        TXTL_addls_TenLoai = (TextInputLayout)findViewById(R.id.txtl_addLS_TenLoai);
        IMG_addls_back = (ImageView)findViewById(R.id.img_addLS_back);
        IMG_addls_ThemHinh = (ImageView)findViewById(R.id.img_addLS_ThemHinh);
        TXT_addls_title = (TextView)findViewById(R.id.txt_addLS_title);
        //endregion

        BitmapDrawable olddrawable = (BitmapDrawable)IMG_addls_ThemHinh.getDrawable();
        bitmapold = olddrawable.getBitmap();

        //region Hiển thị trang sửa nếu được chọn từ context menu sửa
        maloai = getIntent().getIntExtra("maloai",0);
        if(maloai != 0){
            TXT_addls_title.setText("Sua loai");
            LSDTO lsdto = lsdao.LayLoaiSachTheoMa(maloai);

            //Hiển thị lại thông tin từ csdl
            TXTL_addls_TenLoai.getEditText().setText(lsdto.getTenLoai());

            byte[] categoryimage = lsdto.getHinhAnh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(categoryimage,0,categoryimage.length);
            IMG_addls_ThemHinh.setImageBitmap(bitmap);

            BTN_addls_TaoLoai.setText("Sửa loại");
        }
        //endregion

        IMG_addls_back.setOnClickListener(this);
        IMG_addls_ThemHinh.setOnClickListener(this);
        BTN_addls_TaoLoai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        boolean ktra;
        String chucnang;
        switch (id){
            case R.id.img_addLS_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right); //animation
                break;

            case R.id.img_addLS_ThemHinh:
                Intent iGetIMG = new Intent();
                iGetIMG.setType("image/*"); //lấy những mục chứa hình ảnh
                iGetIMG.setAction(Intent.ACTION_GET_CONTENT);   //lấy mục hiện tại đang chứa hình
                resultLauncherOpenIMG.launch(Intent.createChooser(iGetIMG,"Chon anh"));    //mở intent chọn hình ảnh
                break;

            case R.id.btn_addLS_TaoLoai:
                if(!validateImage() | !validateName()){
                    return;
                }

                String sTenLoai = TXTL_addls_TenLoai.getEditText().getText().toString();
                LSDTO lsdto = new LSDTO();
                lsdto.setTenLoai(sTenLoai);
                lsdto.setHinhAnh(imageViewtoByte(IMG_addls_ThemHinh));
                if(maloai != 0){
                    ktra = lsdao.SuaLoaiSach(lsdto,maloai);
                    chucnang = "sualoai";
                }else {
                    ktra = lsdao.ThemLoaiSach(lsdto);
                    chucnang = "themloai";
                }

                //Thêm, sửa loại dựa theo obj loaimonDTO
                Intent intent = new Intent();
                intent.putExtra("ktra",ktra);
                intent.putExtra("chucnang",chucnang);
                setResult(RESULT_OK,intent);
                finish();
                break;

        }
    }

    //Chuyển ảnh bitmap về mảng byte lưu vào csdl
    private byte[] imageViewtoByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    //region validate fields
    private boolean validateImage(){
        BitmapDrawable drawable = (BitmapDrawable)IMG_addls_ThemHinh.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        if(bitmap == bitmapold){
            Toast.makeText(getApplicationContext(),"Xin chọn hình ảnh",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    private boolean validateName(){
        String val = TXTL_addls_TenLoai.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            TXTL_addls_TenLoai.setError("Khong duoc de trong");
            return false;
        }else {
            TXTL_addls_TenLoai.setError(null);
            TXTL_addls_TenLoai.setErrorEnabled(false);
            return true;
        }
    }
}