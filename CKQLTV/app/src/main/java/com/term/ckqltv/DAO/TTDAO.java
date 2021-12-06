package com.term.ckqltv.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import com.term.ckqltv.DTO.TTDTO;
import com.term.ckqltv.Database.database;

import java.util.ArrayList;
import java.util.List;

public class TTDAO extends AppCompatActivity {

    SQLiteDatabase data;

    public TTDAO(Context context) {
        database createDatabase = new database(context);
        data = createDatabase.open();
    }
    public List<TTDTO> LayDSSachTheoMaDon(int madondat){
        List<TTDTO> ttdtoList = new ArrayList<TTDTO>();
        String query = "SELECT * FROM "+database.TT_CHITIET+" ctdd,"+database.TT_SACH+" sach WHERE "
                +"ctdd."+database.TT_CHITIET_MASACH+" = mon."+database.TT_SACH_MASACH+" AND "
                +database.TT_CHITIET_MADONDAT+" = '"+madondat+"'";

        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            TTDTO ttdto = new TTDTO();
            ttdto.setSoLuong(cursor.getInt(cursor.getColumnIndex(database.TT_CHITIET_SOLUONG)));
            ttdto.setGiaTien(cursor.getInt(cursor.getColumnIndex(database.TT_SACH_GIATIEN)));
            ttdto.setTenSach(cursor.getString(cursor.getColumnIndex(database.TT_SACH_TENSACH)));
            ttdto.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(database.TT_SACH_HINHANH)));
            ttdtoList.add(ttdto);

            cursor.moveToNext();
        }

        return ttdtoList;
    }
}
