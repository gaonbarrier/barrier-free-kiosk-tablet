package io.github.gaonbarrier.easykiosk.tablet.db;

import android.provider.BaseColumns;

public final class ingredientDB{
    public static final class CreateDB implements BaseColumns{
        //얘를 추가한 목적
        /*-> 재료 정보들은 어디에 저장해둘것인가?
        얘들을 기본적으로 리소스를 제공해줄 것인가 점주가 이 부분까지 커스터마이징 하게 시킬것인가?
        * */
        public static final String NAME = "name";
        public static final String IMAGE = "image";
        public static final String _TABLENAME2 = "ingredients";
        public static final String _CREATE2 ="create table if not exists "+_TABLENAME2+"("
                +_ID+" integer primary key autoincrement, "
                +NAME+" text not null , "
                +IMAGE+" blob not null );";
    }
}

