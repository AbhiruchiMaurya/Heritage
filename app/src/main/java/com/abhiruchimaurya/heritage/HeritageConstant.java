package com.abhiruchimaurya.heritage;

/**
 * Created by Abhiruchi Maurya on 22-07-2016.
 */
public class HeritageConstant {
    public static final String DATABASE_NAME="heritage";
    public static final String DATABASE_VERSION="1";
    public static final String TABLE_NAME_CITY="city";
    public static final String TABLE_NAME_MONUMENT="monument";
    public static final String COLUMN_CITY_ID="city_id";
    public static final String COLUMN_CITY_NAME="city_name";
    public static final String COLUMN_MONU_NAME="monu_name";
    public static final String COLUMN_MONU_CNAME="creator_name";
    public static final String COLUMN_MONU_PIC="img";
    public static final String COLUMN_MONU_DESCRIPTION="description";

    public static final String QUERY_CITY="create table "+TABLE_NAME_CITY+"" +
                                            "("+COLUMN_CITY_ID+" integer primary key autoincrement," +
                                            ""+COLUMN_CITY_NAME+" text)";


   public static final String QUERY_MONUMENT="create table "+TABLE_NAME_MONUMENT+"("+COLUMN_MONU_NAME+" text,"+COLUMN_MONU_CNAME+" text,"+COLUMN_CITY_NAME+" text,"+COLUMN_MONU_PIC+" blob,"+COLUMN_MONU_DESCRIPTION+" text,foreign key("+COLUMN_CITY_NAME+") references "+TABLE_NAME_CITY+"("+COLUMN_CITY_NAME+"))";
}
