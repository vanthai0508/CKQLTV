package com.term.ckqltv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.term.ckqltv.DTO.LSDTO;
import com.term.ckqltv.Database.database;

import java.util.ArrayList;
import java.util.List;

public class LSDAO {

    SQLiteDatabase data;
    public LSDAO(Context context){
        database createDatabase = new database(context);
        data = createDatabase.open();
    }

    public boolean ThemLoaiSach(LSDTO lsdto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.TT_LS_TENLOAI,lsdto.getTenLoai());
        contentValues.put(database.TT_LS_HINHANH,lsdto.getHinhAnh());
        long ktra = data.insert(database.TT_LS,null,contentValues);

        if(ktra != 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean XoaLoaiSach(int maloai){
        long ktra = data.delete(database.TT_LS,database.TT_LS_MALOAI+ " = " +maloai
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }

    public boolean SuaLoaiSach(LSDTO lsdto,int maloai){
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.TT_LS_TENLOAI,lsdto.getTenLoai());
        contentValues.put(database.TT_LS_HINHANH,lsdto.getHinhAnh());
        long ktra = data.update(database.TT_LS,contentValues
                ,database.TT_LS_MALOAI+" = "+maloai,null);
        if(ktra != 0){
            return true;
        }else {
            return false;
        }
    }

    public List<LSDTO> LayDSLoaiSach(){
        List<LSDTO> loaiMonDTOList = new ArrayList<LSDTO>();
        String query = "SELECT * FROM " +database.TT_LS;
        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            LSDTO lsdto = new LSDTO();
            lsdto.setMaLoai(cursor.getInt(cursor.getColumnIndex(database.TT_LS_MALOAI)));
            lsdto.setTenLoai(cursor.getString(cursor.getColumnIndex(database.TT_LS_TENLOAI)));
           lsdto.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(database.TT_LS_HINHANH)));
            loaiMonDTOList.add(lsdto);

            cursor.moveToNext();
        }
        return loaiMonDTOList;
    }

    public LSDTO LayLoaiSachTheoMa(int maloai){
        LSDTO lsdto = new LSDTO();
        String query = "SELECT * FROM " +database.TT_LS+" WHERE "+database.TT_LS_MALOAI+" = "+maloai;
        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            lsdto.setMaLoai(cursor.getInt(cursor.getColumnIndex(database.TT_LS_MALOAI)));
           lsdto.setTenLoai(cursor.getString(cursor.getColumnIndex(database.TT_LS_TENLOAI)));
            lsdto.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(database.TT_LS_HINHANH)));

            cursor.moveToNext();
        }
        return lsdto;
    }
}
