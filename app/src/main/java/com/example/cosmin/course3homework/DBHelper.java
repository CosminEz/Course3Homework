package com.example.cosmin.course3homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cosmin on 5/20/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Studenti.db";
    public static final String STUDENTS_TABLE_NAME = "studenti";
    public static final String STUDENTS_COLUMN_ID = "id";
    public static final String STUDENTS_COLUMN_NUME = "nume";
    public static final String STUDENTS_COLUMN_PRENUME = "prenume";
    public static final String STUDENTS_COLUMN_EMAIL = "email";
    public static final String STUDENTS_COLUMN_UNIVERSITY = "universitate";
    public static final String STUDENTS_COLUMN_PHONE = "telefon";
    private HashMap hp;

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( "create table studenti " +
        "id integer primary key autoincrement, nume text , prenume text , email text , universitate text , numar telefon text ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS studenti");
        onCreate(db);
    }

    public boolean insertStudent (String nume, String prenume ,String telefon, String email, String universitate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nume", nume);
        contentValues.put("prenume", prenume);
        contentValues.put("email", email);
        contentValues.put("telefon", telefon);
        contentValues.put("universitate", universitate);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }



    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, STUDENTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateStudent (Integer id, String nume, String prenume ,String telefon, String email, String universitate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nume", nume);
        contentValues.put("prenume", prenume);
        contentValues.put("email", email);
        contentValues.put("telefon", telefon);
        contentValues.put("universitate", universitate);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("studenti",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(STUDENTS_COLUMN_NUME)));
            res.moveToNext();
        }
        return array_list;
    }


}
