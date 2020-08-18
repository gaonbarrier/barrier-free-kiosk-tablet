package io.github.gaonbarrier.easykiosk.tablet.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ingredientDBOpenHelper {
    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private ingredientDBOpenHelper.DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(ingredientDB.CreateDB._CREATE2);
        }
        //Table 생성

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ ingredientDB.CreateDB._TABLENAME2);
            onCreate(db);
        }
    }
    public ingredientDBOpenHelper(Context context){this.mCtx = context;}
    public ingredientDBOpenHelper open() throws SQLException {
        mDBHelper = new ingredientDBOpenHelper.DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }
    public void create(){
        mDBHelper.onCreate(mDB);
        System.out.println("ingredient DB Helper create");
    }
    public void close(){mDB.close();}

    // Insert DB
    public long insertColumn(String name, String ingredient, String image){
        ContentValues values = new ContentValues();
        values.put(ingredientDB.CreateDB.NAME, name);
        values.put(ingredientDB.CreateDB.INGREDIENT,ingredient);
        values.put(ingredientDB.CreateDB.IMAGE, image);

        Cursor c = mDB.query(ingredientDB.CreateDB._TABLENAME2, null, null, null, null, null, null);
        while (c.moveToNext()){
            String Name = c.getString(2);
            if( c.getString(1).equals(name) && c.getString(2).equals(ingredient)){
                Log.d("", "Ingredient:"+ingredient+"가 이미 존재합니다.");
                return 0;
            }
        }

        return mDB.insert(ingredientDB.CreateDB._TABLENAME2, null, values);
    }

    // Update DB
    public boolean updateColumn(long id, String name, String ingredient, String image){
        ContentValues values = new ContentValues();
        values.put(ingredientDB.CreateDB.NAME, name);
        values.put(ingredientDB.CreateDB.INGREDIENT,ingredient);
        values.put(ingredientDB.CreateDB.IMAGE, image);
        Cursor c = mDB.query(ingredientDB.CreateDB._TABLENAME2, null, null, null, null, null, null);
        while (c.moveToNext()){
            String Name = c.getString(2);
            if( c.getString(1).equals(Name) && c.getString(2).equals(ingredient)){
                Log.d("", "Ingredient:"+ingredient+"가 이미 존재합니다.");
                return false;
            }
        }

        return mDB.update(ingredientDB.CreateDB._TABLENAME2, values, "_id=" + id, null) > 0;
    }

    // Delete All
    public void deleteAllColumns() {
        mDB.delete(ingredientDB.CreateDB._TABLENAME2, null, null);
    }

    // Delete DB
    public boolean deleteColumn(long id){
        return mDB.delete(ingredientDB.CreateDB._TABLENAME2, "_id="+id, null) > 0;
    }
    // Select DB
    public Cursor selectColumns(){
        return mDB.query(ingredientDB.CreateDB._TABLENAME2, null, null, null, null, null, null);
    }

    public void SelectAll(){
        open();
        Cursor c = selectColumns();
        while(c.moveToNext()){
            int _id = c.getInt(0);
            String Name = c.getString(1);
            String Ingredient = c.getString(2);
            String Image = c.getString(3);
            Log.d("","_id:"+_id+",Name:"+Name
                    +",Ingredient:"+Ingredient+",Image:"+Image);
        }
        close();
    }



    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM items ORDER BY " + sort + ";", null);
        return c;
    }
}
