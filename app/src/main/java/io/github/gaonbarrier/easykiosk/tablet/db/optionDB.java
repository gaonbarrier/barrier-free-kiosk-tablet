package io.github.gaonbarrier.easykiosk.tablet.db;

import android.provider.BaseColumns;

public class optionDB {
    public static final class CreateDB implements BaseColumns{
        public static final String NAME = "name";
        public static final String OPTION = "option";
        public static final String AMOUNT = "amount";
        public static final String _TABLENAME1 = "options";
        public static final String _CREATE1 = "create table if not exists "+_TABLENAME1+"("
                +_ID+" integer primary key autoincrement, "
                +NAME+" text not null, "
                +OPTION+" text not null, "
                +AMOUNT+" integer not null );";
    }
}
