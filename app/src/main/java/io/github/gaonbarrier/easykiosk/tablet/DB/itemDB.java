package io.github.gaonbarrier.easykiosk.tablet.DB;

import android.provider.BaseColumns;

public final class itemDB {
    public static final class CreateDB implements BaseColumns{
        public static final String NAME = "name";
        public static final String CATEGORY = "category";
        public static final String PRICEHOT = "pricehot";
        public static final String PRICECOLD = "pricecold";
        public static final String IMAGE = "image";
        public static final String _TABLENAME0 = "items";
        public static final String _CREATE0 ="create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +NAME+" text not null , "
                +CATEGORY+" text not null , "
                +PRICEHOT+" integer not null , "
                +PRICECOLD+" integer not null ,"
                +IMAGE+" blob not null );";
    }
}
