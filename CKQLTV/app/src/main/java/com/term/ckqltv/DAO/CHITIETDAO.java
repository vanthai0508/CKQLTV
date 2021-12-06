package com.term.ckqltv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.term.ckqltv.DTO.CHITIETDTO;
import com.term.ckqltv.Database.database;

public class CHITIETDAO {

    SQLiteDatabase data;
    public CHITIETDAO(Context context) {
        database createDatabase = new database(context);
        data = createDatabase.open();

    }
    public boolean KiemTraMonTonTai(int madondat, int masach){
        String query = "SELECT * FROM " +database.TT_CHITIET+ " WHERE " +database.TT_CHITIET_MASACH+
                " = " +masach+ " AND " +database.TT_CHITIET_MADONDAT+ " = "+madondat;
        Cursor cursor = data.rawQuery(query,null);
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }

    public int LaySLMonTheoMaDon(int madondat, int masach){
        int soluong = 0;
        String query = "SELECT * FROM " +database.TT_CHITIET+ " WHERE " +database.TT_CHITIET_MASACH+
                " = " +masach+ " AND " +database.TT_CHITIET_MADONDAT+ " = "+madondat;
        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            soluong = cursor.getInt(cursor.getColumnIndex(database.TT_CHITIET_SOLUONG));
            cursor.moveToNext();
        }
        return soluong;
    }

    public boolean CapNhatSL(CHITIETDTO chitietdto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.TT_CHITIET_SOLUONG, chitietdto.getSoLuong());

        long ktra = data.update(database.TT_CHITIET,contentValues,database.TT_CHITIET_MADONDAT+ " = "
                +chitietdto.getMaDonDat()+ " AND " +database.TT_CHITIET_MASACH+ " = "
                +chitietdto.getMaSach(),null);
        if(ktra !=0){
            return true;
        }else {
            return false;
        }
    }

    public boolean ThemChiTietDonDat(CHITIETDTO chitietdto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.TT_CHITIET_SOLUONG,chitietdto.getSoLuong());
        contentValues.put(database.TT_CHITIET_MADONDAT,chitietdto.getMaDonDat());
        contentValues.put(database.TT_CHITIET_MASACH,chitietdto.getMaSach());

        long ktra = data.insert(database.TT_CHITIET,null,contentValues);
        if(ktra !=0){
            return true;
        }else {
            return false;
        }
    }
}
