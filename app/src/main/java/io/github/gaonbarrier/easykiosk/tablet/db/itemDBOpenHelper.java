package io.github.gaonbarrier.easykiosk.tablet.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import io.github.gaonbarrier.easykiosk.tablet.menu.item;

import java.util.ArrayList;

public class itemDBOpenHelper {
    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(itemDB.CreateDB._CREATE0);
        }
        //Table 생성

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ itemDB.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public itemDBOpenHelper(Context context){
        this.mCtx = context;
    }

    public itemDBOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
        System.out.println("itemDB Open Helper Create");
    }

    public void close(){
        mDB.close();
    }
    //create, close 등등...

    // Insert DB
    public long insertColumn(String name, String category , int pricehot, int pricecold, String image){
        ContentValues values = new ContentValues();
        values.put(itemDB.CreateDB.NAME, name);
        values.put(itemDB.CreateDB.CATEGORY, category);
        values.put(itemDB.CreateDB.PRICEHOT, pricehot);
        values.put(itemDB.CreateDB.PRICECOLD, pricecold);
        values.put(itemDB.CreateDB.IMAGE, image);
        Cursor c = mDB.query(itemDB.CreateDB._TABLENAME0, null, null, null, null, null, null);
        while(c.moveToNext()){
            String Name = c.getString(1);
            if(c.getString(1).equals(name)){
                Log.d("","Name:"+Name+"가 이미 존재합니다.");
                return 0;
            }
        }

        return mDB.insert(itemDB.CreateDB._TABLENAME0, null, values);
    }

    // Update DB
    public boolean updateColumn(long id, String name, String category , int pricehot, int pricecold, String image){
        ContentValues values = new ContentValues();
        values.put(itemDB.CreateDB.NAME, name);
        values.put(itemDB.CreateDB.CATEGORY, category);
        values.put(itemDB.CreateDB.PRICEHOT, pricehot);
        values.put(itemDB.CreateDB.PRICECOLD, pricecold);
        values.put(itemDB.CreateDB.IMAGE, image);
        Cursor c = mDB.query(itemDB.CreateDB._TABLENAME0, null, null, null, null, null, null);
        while(c.moveToNext()){
            String Name = c.getString(1);
            if(c.getString(1).equals(name)){
                Log.d("","Name:"+Name+"가 이미 존재합니다.");
                return false;
            }
        }

        return mDB.update(itemDB.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
    }
    //ID number 찾기
    public long findID(String name){
        Cursor c = mDB.rawQuery("SELECT id FROM items WHERE name = " + name + ";",null);
        return c.getColumnIndex("_id");
    }

    // Delete All
    public void deleteAllColumns() {
        mDB.delete(itemDB.CreateDB._TABLENAME0, null, null);
    }

    // Delete DB
    public boolean deleteColumn(long id){
        return mDB.delete(itemDB.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }
    // Select DB
    public Cursor selectColumns(){
        return mDB.query(itemDB.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }


    public ArrayList<item> selectCategory(String Category){
        Cursor c = selectColumns();
        ArrayList<item> tmp = new ArrayList<>();
        while(c.moveToNext()) {
            if(c.getString(2).equals(Category)){
                item temp = new item(c.getString(1), c.getString(2), c.getInt(3), c.getInt(4));
                tmp.add(temp);
            }
        }
        return tmp;
    }
    //얜 약간 테스트용.
    public void SelectAll(){
        Cursor c = selectColumns();
        while(c.moveToNext()){
            int _id = c.getInt(0);
            String Name = c.getString(1);
            String Category = c.getString(2);
            String PriceHot = c.getString(3);
            String PriceCold = c.getString(4);
            String Image = c.getString(5);
            Log.d("","_id:"+_id+",Name:"+Name
                    +",Category:"+Category+",PriceHot:"+PriceHot+",PriceCold:"+PriceCold+",Image:"+Image);
        }
    }
    // sort by column
    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM items ORDER BY " + sort + ";", null);
        return c;
    }

    //public long insertColumn(String )
}