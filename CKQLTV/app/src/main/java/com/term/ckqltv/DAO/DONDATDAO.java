package com.term.ckqltv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.term.ckqltv.DTO.DONDATDTO;
import com.term.ckqltv.Database.database;

import java.util.ArrayList;
import java.util.List;

public class DONDATDAO {

    SQLiteDatabase data;

    public DONDATDAO(Context context) {
        database createDatabase = new database(context);
        data = createDatabase.open();
    }
    public long ThemDonDat(DONDATDTO dondatdto){
        ContentValues contentValues = new ContentValues();

        contentValues.put(database.TT_DONDAT_MAKH,dondatdto.getMaKH());
        contentValues.put(database.TT_DONDAT_NGAYDAT,dondatdto.getNgayDat());
        contentValues.put(database.TT_DONDAT_TINHTRANG,dondatdto.getTinhTrang());
        contentValues.put(database.TT_DONDAT_TONGTIEN,dondatdto.getTongTien());

        long madondat = data.insert(database.TT_DONDAT,null,contentValues);

        return madondat;
    }

    public List<DONDATDTO> LayDSDonDat(){
        List<DONDATDTO> dondatdtos = new ArrayList<DONDATDTO>();
        String query = "SELECT * FROM "+database.TT_DONDAT;
        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DONDATDTO dondatdto = new DONDATDTO();
            dondatdto.setMaDonDat(cursor.getInt(cursor.getColumnIndex(database.TT_DONDAT_MADONDAT)));

            dondatdto.setTongTien(cursor.getString(cursor.getColumnIndex(database.TT_DONDAT_TONGTIEN)));
            dondatdto.setTinhTrang(cursor.getString(cursor.getColumnIndex(database.TT_DONDAT_TINHTRANG)));
            dondatdto.setNgayDat(cursor.getString(cursor.getColumnIndex(database.TT_DONDAT_NGAYDAT)));
            dondatdto.setMaKH(cursor.getInt(cursor.getColumnIndex(database.TT_DONDAT_MAKH)));
            dondatdtos.add(dondatdto);

            cursor.moveToNext();
        }
        return dondatdtos;
    }

    public List<DONDATDTO> LayDSDonDatNgay(String ngaythang){
        List<DONDATDTO> dondatdtos = new ArrayList<DONDATDTO>();
        String query = "SELECT * FROM "+database.TT_DONDAT+" WHERE "+database.TT_DONDAT_NGAYDAT+" like '"+ngaythang+"'";
        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DONDATDTO dondatdto = new DONDATDTO();
            dondatdto.setMaDonDat(cursor.getInt(cursor.getColumnIndex(database.TT_DONDAT_MADONDAT)));

            dondatdto.setTongTien(cursor.getString(cursor.getColumnIndex(database.TT_DONDAT_TONGTIEN)));
            dondatdto.setTinhTrang(cursor.getString(cursor.getColumnIndex(database.TT_DONDAT_TINHTRANG)));
            dondatdto.setNgayDat(cursor.getString(cursor.getColumnIndex(database.TT_DONDAT_NGAYDAT)));
            dondatdto.setMaKH(cursor.getInt(cursor.getColumnIndex(database.TT_DONDAT_MAKH)));
            dondatdtos.add(dondatdto);

            cursor.moveToNext();
        }
        return dondatdtos;
    }


    public long LayMaDonTheoKH(int makh, String tinhtrang){
        String query = "SELECT * FROM " +database.TT_DONDAT+ " WHERE " +database.TT_DONDAT_MAKH+ " = '" +makh+ "' AND "
                +database.TT_DONDAT_TINHTRANG+ " = '" +tinhtrang+ "' ";
        long madatsach = 0;
        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            madatsach = cursor.getInt(cursor.getColumnIndex(database.TT_DONDAT_MADONDAT));

            cursor.moveToNext();
        }
        return madatsach;
    }

    public boolean UpdateTongTienDonDat(int madondat,String tongtien){
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.TT_DONDAT_TONGTIEN,tongtien);
        long ktra  = data.update(database.TT_DONDAT,contentValues,
                database.TT_DONDAT_MADONDAT+" = "+madondat,null);
        if(ktra != 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean UpdateTThaiDonTheoKH(int makh,String tinhtrang){
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.TT_DONDAT_TINHTRANG,tinhtrang);
        long ktra = data.update(database.TT_DONDAT,contentValues,database.TT_DONDAT_MAKH+
                " = '"+makh+"'",null);
        if(ktra !=0){
            return true;
        }else {
            return false;
        }
    }

}
