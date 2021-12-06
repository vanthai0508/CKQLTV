package com.term.ckqltv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.term.ckqltv.Database.database;

public class QUYENDAO {
    SQLiteDatabase data;

    public QUYENDAO(Context context) {
        database createDatabase = new database(context);
        data = createDatabase.open();
    }
    public void ThemQuyen(String tenquyen){
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.TT_QUYEN_TENQUYEN,tenquyen);
        data.insert(database.TT_QUYEN,null,contentValues);
    }


    public String LayTenQuyenTheoMa(int maquyen){
        String tenquyen ="";
        String query = "SELECT * FROM "+database.TT_QUYEN+" WHERE "+database.TT_QUYEN_MAQUYEN+" = "+maquyen;
        Cursor cursor = data.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tenquyen = cursor.getString(cursor.getColumnIndex(database.TT_QUYEN_TENQUYEN));
            cursor.moveToNext();
        }
        return tenquyen;
    }
}
