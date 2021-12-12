package com.term.ckqltv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.term.ckqltv.DTO.KHDTO;
import com.term.ckqltv.Database.database;

import java.util.ArrayList;
import java.util.List;

public class KHDAO {

    SQLiteDatabase data;
    public KHDAO(Context context){
        database createDatabase = new database(context);
        data = createDatabase.open();
    }
    public long ThemKH(KHDTO khdto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.TT_KH_HOTENKH,khdto.getHOTENKH());
        contentValues.put(database.TT_KH_TENDN,khdto.getTENDN());
        contentValues.put(database.TT_KH_MATKHAU,khdto.getMATKHAU());
        contentValues.put(database.TT_KH_EMAIL,khdto.getEMAIL());
        contentValues.put(database.TT_KH_SDT,khdto.getSDT());
        contentValues.put(database.TT_KH_GIOITINH,khdto.getGIOITINH());
        contentValues.put(database.TT_KH_NGAYSINH,khdto.getNGAYSINH());
        contentValues.put(database.TT_KH_MAQUYEN,khdto.getMAQUYEN());

        long ktra = data.insert(database.TT_KHACHHANG,null,contentValues);
        return ktra;
    }

    public long SuaKH(KHDTO khdto,int makh){
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.TT_KH_HOTENKH,khdto.getHOTENKH());
        contentValues.put(database.TT_KH_TENDN,khdto.getTENDN());
        contentValues.put(database.TT_KH_MATKHAU,khdto.getMATKHAU());
        contentValues.put(database.TT_KH_EMAIL,khdto.getEMAIL());
        contentValues.put(database.TT_KH_SDT,khdto.getSDT());
        contentValues.put(database.TT_KH_GIOITINH,khdto.getGIOITINH());
        contentValues.put(database.TT_KH_NGAYSINH,khdto.getNGAYSINH());
       contentValues.put(database.TT_KH_MAQUYEN,khdto.getMAQUYEN());

        long ktra = data.update(database.TT_KHACHHANG,contentValues,
                database.TT_KH_MAKH+" = "+makh,null);
        return ktra;
    }

    public int KiemTraDN(String tenDN, String matKhau){
        String query = "SELECT * FROM " +database.TT_KHACHHANG+ " WHERE "
                +database.TT_KH_TENDN +" = '"+ tenDN+"' AND "+database.TT_KH_MATKHAU +" = '" +matKhau +"'";
        int makh = 0;
        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            makh = cursor.getInt(cursor.getColumnIndex(database.TT_KH_MAKH)) ;
            cursor.moveToNext();
        }
        return makh;
    }

    public boolean KtraTonTaiKH(){
        String query = "SELECT * FROM "+database.TT_KHACHHANG;
        Cursor cursor =data.rawQuery(query,null);
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }

    public List<KHDTO> LayDSKH(){
        List<KHDTO> khdtos = new ArrayList<KHDTO>();
        String query = "SELECT * FROM "+database.TT_KHACHHANG;

        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            KHDTO khdto = new KHDTO();
            khdto.setHOTENKH(cursor.getString(cursor.getColumnIndex(database.TT_KH_HOTENKH)));
            khdto.setEMAIL(cursor.getString(cursor.getColumnIndex(database.TT_KH_EMAIL)));
            khdto.setGIOITINH(cursor.getString(cursor.getColumnIndex(database.TT_KH_GIOITINH)));
            khdto.setNGAYSINH(cursor.getString(cursor.getColumnIndex(database.TT_KH_NGAYSINH)));
            khdto.setSDT(cursor.getString(cursor.getColumnIndex(database.TT_KH_SDT)));
            khdto.setTENDN(cursor.getString(cursor.getColumnIndex(database.TT_KH_TENDN)));
            khdto.setMATKHAU(cursor.getString(cursor.getColumnIndex(database.TT_KH_MATKHAU)));
            khdto.setMAKH(cursor.getInt(cursor.getColumnIndex(database.TT_KH_MAKH)));
            khdto.setMAQUYEN(cursor.getInt(cursor.getColumnIndex(database.TT_KH_MAQUYEN)));

            khdtos.add(khdto);
            cursor.moveToNext();
        }
        return khdtos;
    }

    public boolean XoaKH(int makh){
        long ktra = data.delete(database.TT_KHACHHANG,database.TT_KH_MAKH+ " = " +makh
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }
    public void SuaTK(KHDTO khdto, int makh){
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.TT_KH_HOTENKH,khdto.getHOTENKH());
        contentValues.put(database.TT_KH_TENDN,khdto.getTENDN());

        contentValues.put(database.TT_KH_EMAIL,khdto.getEMAIL());
        contentValues.put(database.TT_KH_SDT,khdto.getSDT());
        contentValues.put(database.TT_KH_MATKHAU,khdto.getMATKHAU());


         data.update(database.TT_KHACHHANG,contentValues,
                database.TT_KH_MAKH+" = "+makh,null);
    //    return ktra;
    }

    public KHDTO LayKHTheoMa(int makh){
        KHDTO khdto = new KHDTO();
        String query = "SELECT * FROM "+database.TT_KHACHHANG+" WHERE "+database.TT_KH_MAKH+" = "+makh;
        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            khdto.setHOTENKH(cursor.getString(cursor.getColumnIndex(database.TT_KH_HOTENKH)));
            khdto.setEMAIL(cursor.getString(cursor.getColumnIndex(database.TT_KH_EMAIL)));
            khdto.setGIOITINH(cursor.getString(cursor.getColumnIndex(database.TT_KH_GIOITINH)));
            khdto.setNGAYSINH(cursor.getString(cursor.getColumnIndex(database.TT_KH_NGAYSINH)));
            khdto.setSDT(cursor.getString(cursor.getColumnIndex(database.TT_KH_SDT)));
            khdto.setTENDN(cursor.getString(cursor.getColumnIndex(database.TT_KH_TENDN)));
            khdto.setMATKHAU(cursor.getString(cursor.getColumnIndex(database.TT_KH_MATKHAU)));
            khdto.setMAKH(cursor.getInt(cursor.getColumnIndex(database.TT_KH_MAKH)));
           khdto.setMAQUYEN(cursor.getInt(cursor.getColumnIndex(database.TT_KH_MAQUYEN)));

            cursor.moveToNext();
        }
        return khdto;
    }

    public int LayQuyenKH(int makh){
        int maquyen = 0;
        String query = "SELECT * FROM "+database.TT_KHACHHANG+" WHERE "+database.TT_KH_MAKH+" = "+makh;
        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            maquyen = cursor.getInt(cursor.getColumnIndex(database.TT_QUYEN_MAQUYEN));

            cursor.moveToNext();
        }
        return maquyen;
    }

}
