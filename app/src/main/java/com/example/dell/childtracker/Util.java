package com.example.dell.childtracker;

import android.net.Uri;

/**
 * Created by Dell on 11-08-2017.
 */

public class Util
{
    public static final String DB_NAME="Database.db";
    public static final int DB_VERSION=1;
    public static final String TAB_NAME="TableData";

    public static final String COL_ID="_ID";          // Autoincrement (primary key)
    public static final String COL_DATE="DATE";
    public static final String COL_TIME="TIME";
    public static final String COL_LATITUDE="LATITUDE";
    public static final String COL_LONGITUDE="LONGITUDE";
    public static final String COL_LOCATION="LOCATION";
    public static final String COL_SPEED="SPEED";


    public static final String CREATE_TAB_QUERY="create table TableData("+
            "_ID integer primary key autoincrement,"+
            "DATE text,"+
            "TIME text,"+
            "LATITUDE double,"+
            "LONGITUDE double,"+
            "LOCATION text,"+
            "SPEED float"+
            ")";

    public static final Uri USER_URI=Uri.parse("content://com.example.dell.childtracker.datacontentprovider/"+TAB_NAME);

}
