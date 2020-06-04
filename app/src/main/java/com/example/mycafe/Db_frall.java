package com.example.mycafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Locale;

public class Db_frall extends SQLiteOpenHelper {
    public ByteArrayOutputStream byteArrayOutputStream;
    public byte[] imageinbtye;
    public String imgname;

       public static final String DATABASE_NAME = "db_cafe1.db";
    public static final String TABLE_NAME = "userregd";
    public static final String UCOL_1 = "ID";
    public static final String UCOL_2 = "FNAME";
    public static final String UCOL_3 = "LNAME";
    public static final String UCOL_4 = "EMAIL";
    public static final String UCOL_5 = "MOBILENO";
    public static final String UCOL_6 = "PASSWORD";
    public static final String UCOL_7 = "EXTRA";

    public static final String ADM_TABLE_NAME = "admindetails";
    public static final String ADM_COL_1 = "ID";
    public static final String ADM_COL_2 = "NAME";
    public static final String ADM_COL_3 = "EMAIL";
    public static final String ADM_COL_4 = "PASSWORD";
    public static final String ADM_COL_5 = "EXTRA";

    public static final String ITEM_TABLE_NAME = "itemdetails";
    public static final String ITEM_COL_1 = "ID";
    public static final String ITEM_COL_2 = "CATEGORY";
    public static final String ITEM_COL_3 = "ITEMNAME";
    public static final String ITEM_COL_4 = "ITEMIMAGE";
    public static final String ITEM_COL_5 = "COST";
    public static final String ITEM_COL_6 = "STATUS";
    public static final String ITEM_COL_7 = "EXTRA";

     public Db_frall(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,FNAME TEXT,LNAME TEXT,EMAIL TEXT, MOBILENO TEXT, PASSWORD TEXT, EXTRA TEXT)");
        db.execSQL("create table " + ADM_TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,PASSWORD TEXT, EXTRA TEXT)");
        db.execSQL("create table " + ITEM_TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,CATEGORY TEXT,ITEMNAME TEXT,ITEMIMAGE BLOB,COST TEXT,STATUS TEXT, EXTRA TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ADM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ITEM_TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String fname,String lname,String email, String mobileno,String password, String extra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UCOL_2,fname);
        contentValues.put(UCOL_3,lname);
        contentValues.put(UCOL_4,email);
        contentValues.put(UCOL_5,mobileno);
        contentValues.put(UCOL_6,password);
        contentValues.put(UCOL_7,extra);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }



    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();



        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public Cursor checkLogin(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        String q;//= "select * from user where USERNAME = '"+username+"' and PASSWORD = '"+password+"'";
        q= "select * from userregd where EMAIL = '"+email+"' and PASSWORD = '"+password+"'";
        Cursor res = db.rawQuery(q, null);
        return res;

    }
    public Cursor recoverPass(String email) {

        SQLiteDatabase db = this.getWritableDatabase();

        String q;//= "select * from user where USERNAME = '"+username+"' and PASSWORD = '"+password+"'";
        q= "select fname,Password from userregd where EMAIL = '"+email+"' ";
        Cursor res = db.rawQuery(q, null);
        return res;

    }
    public Cursor alreadyExist(String email) {

        SQLiteDatabase db = this.getWritableDatabase();

        String q;//= "select * from user where USERNAME = '"+username+"' and PASSWORD = '"+password+"'";
        q= "select Email from userregd where EMAIL = '"+email+"' ";
        Cursor res = db.rawQuery(q, null);
        return res;

    }
    public Cursor userLoginID(String email) {

        SQLiteDatabase db = this.getWritableDatabase();

        String q;//= "select * from user where USERNAME = '"+username+"' and PASSWORD = '"+password+"'";
        q= "select ID from userregd where EMAIL = '"+email+"' ";
        Cursor res2 = db.rawQuery(q, null);
        return res2;

    }
//------------------------------------------------------------------------------------------------------------------------------------

//============================================================================================================
// 11111111111111111-----------start admin details data contains   insertion etc

    public void deladmdata(){
        try{
            SQLiteDatabase db = getWritableDatabase();
            //  db = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
            db.execSQL("DELETE FROM  " + ADM_TABLE_NAME);

            db.close();
        }catch(Exception e){
            //Toast.makeText((), "Error encountered while dropping.", Toast.LENGTH_LONG);
        }
    }

    public void  ADMdata()
    { SQLiteDatabase db = this.getWritableDatabase();
        String dta = "insert into "+ ADM_TABLE_NAME +" values (null, 'arzu', 'admin@gmail.com', 'admin','0')";
        db.execSQL(dta);
    }


    public boolean admInsertData(String name,String email,String password, String extra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ADM_COL_2,name);
        contentValues.put(ADM_COL_3,email);
        contentValues.put(ADM_COL_4,password);
        contentValues.put(ADM_COL_5,extra);
        long result = db.insert(ADM_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }



    public Cursor checkLogin2(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        String q;//= "select * from user where USERNAME = '"+username+"' and PASSWORD = '"+password+"'";
        q= "select * from admindetails where EMAIL = '"+email+"' and PASSWORD = '"+password+"'";
        Cursor res = db.rawQuery(q, null);
        return res;

    }


    public Cursor admGetAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ADM_TABLE_NAME,null);
        return res;
    }

    ////////ITEM DETAILS //////////////////

   public boolean insertUploadData(String category,String itemname,byte[] itemimage,String cost,String status, String extra){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put(ITEM_COL_2, category);
       contentValues.put(ITEM_COL_3,itemname);
       contentValues.put(ITEM_COL_4,itemimage);
       contentValues.put(ITEM_COL_5,cost);
       contentValues.put(ITEM_COL_6,status);
       contentValues.put(ITEM_COL_7,extra);

       long result = db.insert(ITEM_TABLE_NAME,null ,contentValues);

       if(result == -1)
           return false;
       else
           return true;
   }

    public ArrayList<Model> getAllImagesData(String s, String b)
    {
        try {
            SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            ArrayList<Model> modelArrayList=new ArrayList<>();
            Cursor cursor=sqLiteDatabase.rawQuery("select * from itemdetails where category=? and status=?", new String[] {s, b});
            if (cursor.getCount()!=0){
                while (cursor.moveToNext()){
                    String nameofimage =cursor.getString(2);
                    byte[] imageByte =cursor.getBlob(3);
                    String cost =cursor.getString(4);
                    String category =cursor.getString(1);
                    String status =cursor.getString(5);
                    String extra =cursor.getString(6);
                    Integer faltu=cursor.getInt(0);
                    Bitmap bitmap= BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                    //int ID, String CATEGORY, String ITEMNAME, Bitmap ITEMIMAGE, String COST, String STATUS, String EXTRA
                    modelArrayList.add(new Model(faltu,category,nameofimage,bitmap,cost,status,extra));

                }
                return modelArrayList;
            }
            else {
                Toast.makeText(faltu_context.context,"No values in database",Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(faltu_context.context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return null;
        }

    }

















}
