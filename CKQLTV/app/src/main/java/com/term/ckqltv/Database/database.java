package com.term.ckqltv.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    public static String TT_KHACHHANG = "KHACHHANG";
    public static String TT_SACH = "SACH";
    public static String TT_LS = "LOAISACH";
    public static String TT_DONDAT = "DONDAT";
    public static String TT_CHITIET = "CHITIETDONDAT";
    public static String TT_QUYEN = "QUYEN";

    //Bảng nhân viên
    public static String TT_KH_MAKH = "MAKH";
    public static String TT_KH_HOTENKH = "HOTENKH";
    public static String TT_KH_TENDN = "TENDN";
    public static String TT_KH_MATKHAU = "MATKHAU";
    public static String TT_KH_EMAIL = "EMAIL";
    public static String TT_KH_SDT = "SDT";
    public static String TT_KH_GIOITINH = "GIOITINH";
    public static String TT_KH_NGAYSINH = "NGAYSINH";
    public static String TT_KH_MAQUYEN= "MAQUYEN";

//    Bảng quyền
    public static String TT_QUYEN_MAQUYEN = "MAQUYEN";
    public static String TT_QUYEN_TENQUYEN = "TENQUYEN";

    //Bảng món
    public static String TT_SACH_MASACH = "MASACH";
    public static String TT_SACH_TENSACH = "TENSACH";
    public static String TT_SACH_GIATIEN = "GIATIEN";
    public static String TT_SACH_TINHTRANG = "TINHTRANG";
    public static String TT_SACH_HINHANH = "HINHANH";
    public static String TT_SACH_MALOAI = "MALOAI";

    //Bảng loại món
    public static String TT_LS_MALOAI = "MALOAI";
    public static String TT_LS_TENLOAI = "TENLOAI";
    public static String TT_LS_HINHANH = "HINHANH";

    //Bảng bàn
 //   public static String TBL_BAN_MABAN = "MABAN";
 //   public static String TBL_BAN_TENBAN = "TENBAN";
 //   public static String TBL_BAN_TINHTRANG = "TINHTRANG";

    //Bảng đơn đặt
    public static String TT_DONDAT_MADONDAT = "MADONDAT";
    public static String TT_DONDAT_MAKH = "MANV";
    public static String TT_DONDAT_NGAYDAT = "NGAYDAT";
    public static String TT_DONDAT_TINHTRANG = "TINHTRANG";
    public static String TT_DONDAT_TONGTIEN = "TONGTIEN";
 //   public static String TT_DONDAT_MABAN = "MABAN";

    //Bảng chi tiết đơn đặt
    public static String TT_CHITIET_MADONDAT = "MADONDAT";
    public static String TT_CHITIET_MASACH = "MAMON";
    public static String TT_CHITIET_SOLUONG = "SOLUONG";


    public database(Context context) {
        super(context, "QLTV", null, 1);
    }

    //thực hiện tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        String ttKHACHHANH = "CREATE TABLE " +TT_KHACHHANG+ " ( " +TT_KH_MAKH+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TT_KH_HOTENKH+ " TEXT, " +TT_KH_TENDN+ " TEXT, " +TT_KH_MATKHAU+ " TEXT, " +TT_KH_EMAIL+ " TEXT, "
                +TT_KH_SDT+ " TEXT, " +TT_KH_GIOITINH+ " TEXT, " +TT_KH_NGAYSINH+ " TEXT ,"+ TT_KH_MAQUYEN+" INTEGER)";

        String ttQUYEN = "CREATE TABLE " +TT_QUYEN+ " ( " +TT_QUYEN_MAQUYEN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TT_QUYEN_TENQUYEN+ " TEXT)" ;

//        String tblBAN = "CREATE TABLE " +TT_BAN+ " ( " +TBL_BAN_MABAN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                +TBL_BAN_TENBAN+ " TEXT, " +TBL_BAN_TINHTRANG+ " TEXT )";

        String ttSACH = "CREATE TABLE " +TT_SACH+ " ( " +TT_SACH_MASACH+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TT_SACH_TENSACH+ " TEXT, " +TT_SACH_GIATIEN+ " TEXT,"+TT_SACH_HINHANH+ " BLOB, " +TT_SACH_TINHTRANG+ " TEXT, "+TT_SACH_MALOAI+ " INTEGER )";

        String ttLS = "CREATE TABLE " +TT_LS+ " ( " +TT_LS_MALOAI+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +TT_LS_TENLOAI+ " TEXT)" ;

        String ttDONDAT = "CREATE TABLE " +TT_DONDAT+ " ( " +TT_DONDAT_MADONDAT+ " INTEGER PRIMARY KEY AUTOINCREMENT,  "+TT_DONDAT_MAKH+ " INTEGER,"+TT_LS_HINHANH+ " BLOB, " +TT_DONDAT_NGAYDAT+ " TEXT, "+TT_DONDAT_TONGTIEN+" TEXT,"
                +TT_DONDAT_TINHTRANG+ " TEXT )" ;

        String ttCHITIET = "CREATE TABLE " +TT_CHITIET+ " ( " +TT_CHITIET_MADONDAT+ " INTEGER, "
                +TT_CHITIET_MASACH+ " INTEGER, " +TT_CHITIET_SOLUONG+ " INTEGER, "
                + " PRIMARY KEY ( " +TT_CHITIET_MADONDAT+ "," +TT_CHITIET_MASACH+ "))";

        db.execSQL(ttKHACHHANH);
        db.execSQL(ttQUYEN);
     //   db.execSQL(tblBAN);
        db.execSQL(ttSACH);
        db.execSQL(ttLS);
        db.execSQL(ttDONDAT);
        db.execSQL(ttCHITIET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //mở kết nối csdl
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }

}
