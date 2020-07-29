package io.github.gaonbarrier.easykiosk.tablet.db;

import android.provider.BaseColumns;

public class optionDB {
    static final String DB_KIOSK = "kiosk.db";
    static final String Table_Options = "Options";

    public static final class CreateDB implements BaseColumns{
        public static final String NAME = "option";
        public static final String AMOUNT = "amount";
        public static final String _TABLENAME1 = "options";
        public static final String _CREATE1 = "create table if not exists "+_TABLENAME1+"("
                +_ID+" integer primary key autoincrement, "
                +NAME+" text not null, "
                +AMOUNT+" integer not null );";
    }
}
