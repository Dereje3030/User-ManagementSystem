package com.solomon.user_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;


public  class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version=1;
    public String CREATE_QUERY=" CREATE TABLE "+ com.anteneh.user_management_system.TableData.TableInfo.TABLE_NAME +
            "("+ com.anteneh.user_management_system.TableData.TableInfo.FIRST_NAME+" TEXT,"+

            com.anteneh.user_management_system.TableData.TableInfo.SECOND_NAME+" TEXT,"+

            com.anteneh.user_management_system.TableData.TableInfo.LAST_NAME +" TEXT,"+

            com.anteneh.user_management_system.TableData.TableInfo.MOBILE+" TEXT,"+

            com.anteneh.user_management_system.TableData.TableInfo.GENDER+" TEXT,"+

            com.anteneh.user_management_system.TableData.TableInfo.USER_NAME+" TEXT,"+

            com.anteneh.user_management_system.TableData.TableInfo.EMAIL+" TEXT,"+

            com.anteneh.user_management_system.TableData.TableInfo.USER_PASS+" TEXT);";


    public DatabaseOperations(Context context) {
        super(context, com.anteneh.user_management_system.TableData.TableInfo.DATABASE_NAME,
                null,database_version);
        Log.d("Database operations","Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
             sdb.execSQL(CREATE_QUERY);
             Log.d("Database operations", "Table created ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
    public void putInformation(DatabaseOperations dop,String fn,String sn,
                               String ln,String pn,String gender,
                               String username,String email,String pass)

    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(com.anteneh.user_management_system.TableData.TableInfo.FIRST_NAME,fn);
        cv.put(com.anteneh.user_management_system.TableData.TableInfo.SECOND_NAME,sn);
        cv.put(com.anteneh.user_management_system.TableData.TableInfo.LAST_NAME,ln);
        cv.put(com.anteneh.user_management_system.TableData.TableInfo.MOBILE,pn);
        cv.put(com.anteneh.user_management_system.TableData.TableInfo.GENDER,gender);
        cv.put(com.anteneh.user_management_system.TableData.TableInfo.USER_NAME,username);
        cv.put(com.anteneh.user_management_system.TableData.TableInfo.EMAIL,email);
        cv.put(com.anteneh.user_management_system.TableData.TableInfo.USER_PASS,pass);

        long k = SQ.insert(com.anteneh.user_management_system.TableData.TableInfo.TABLE_NAME,null,cv);
        System.out.println("yes it works"+k);
        Log.d("Database operation","one raw inserted");
    }

    public List   getAllUsers() {
          List users = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + com.anteneh.user_management_system.TableData.TableInfo.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

            while (cursor.moveToNext()) {
                com.anteneh.user_management_system.TableModel u = new com.anteneh.user_management_system.TableModel(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7));

                users.add(u);
            }
            cursor.close();



        return users ;
    }
}


