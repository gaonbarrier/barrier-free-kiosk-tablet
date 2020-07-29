package io.github.gaonbarrier.easykiosk.tablet.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class optionDBOpenHelper {
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
            db.execSQL(optionDB.CreateDB._CREATE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ optionDB.CreateDB._TABLENAME1);
            onCreate(db);
        }
    }

    public optionDBOpenHelper(Context context){ this.mCtx = context; }
    public optionDBOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    // Insert DB
    public long insertColumn(String name, int amount){
        ContentValues values = new ContentValues();
        values.put(optionDB.CreateDB.NAME, name);
        values.put(optionDB.CreateDB.AMOUNT, amount);

        return mDB.insert(optionDB.CreateDB._TABLENAME1, null, values);
    }

    // Update DB
    public boolean updateColumn(long id, String name, int amount){
        ContentValues values = new ContentValues();
        values.put(optionDB.CreateDB.NAME, name);
        values.put(optionDB.CreateDB.AMOUNT, amount);

        return mDB.update(optionDB.CreateDB._TABLENAME1, values, "_id=" + id, null) > 0;
    }

    // Delete All
    public void deleteAllColumns() {
        mDB.delete(optionDB.CreateDB._TABLENAME1, null, null);
    }

    // Delete DB
    public boolean deleteColumn(long id){
        return mDB.delete(optionDB.CreateDB._TABLENAME1, "_id="+id, null) > 0;
    }
    // Select DB
    public Cursor selectColumns(){
        return mDB.query(optionDB.CreateDB._TABLENAME1, null, null, null, null, null, null);
    }

    // sort by column
    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }
}
