package io.github.gaonbarrier.easykiosk.tablet.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import io.github.gaonbarrier.easykiosk.tablet.menu.item;
import java.util.ArrayList;

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


}
