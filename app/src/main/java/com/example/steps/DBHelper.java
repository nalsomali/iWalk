package com.example.steps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "users.db";

    public DBHelper( Context context ){
        super( context , "users.db" , null , 1 );

    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("create Table emails(email TEXT primary key , password TEXT )");
    }

    public void onUpgrade(SQLiteDatabase mydb , int i , int il){
        mydb.execSQL("drop Table if exists emails");

    }

    public boolean insertData( String email , String password ){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues() ;
        contentValues.put("email" , email);
        contentValues.put("password" , password); // column name
        long result = mydb.insert("emails" , null ,contentValues );
        if(result == -1 ) return false ; // insertion fails
        else return true;

    }

    public Boolean checkemail( String email){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from emails where email = ?" , new String[] {email}) ;
        if ( cursor.getCount() > 0 ) // user exists
            return true ;
        else
            return false;

    }

    public boolean checkpassword(String email , String password ){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from emails where email = ? and password = ? "  , new String[] {email , password}) ;
        if ( cursor.getCount() > 0 ) // user exists
            return true ;
        else
            return false;

    }
}
