package com.example.cosmin.course3homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cosmin.course3homework.Model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public static final String STUDENT_COLUM_CUNOSTINTE = "cunostinte";
    private static final String TAG ="tag" ;
    private HashMap hp;

    private static DBHelper sInstance;


    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }


    public static synchronized DBHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( "create table studenti " +
        "id integer primary key autoincrement, nume text , prenume text , email text , universitate text , telefon text , cunostinte text");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS studenti");
        onCreate(db);
    }

    public boolean insertStudent (Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try{
        ContentValues contentValues = new ContentValues();
        contentValues.put("nume", student.getNume());
        contentValues.put("prenume", student.getPrenume());
        contentValues.put("email", student.getMail());
        contentValues.put("telefon", student.getTelefon());
        contentValues.put("universitate", student.getuniversitate());
        contentValues.put("cunostinte", student.getCunostinte().toString());
        db.insert(STUDENTS_TABLE_NAME, null, contentValues);
        }  catch (Exception e) {
            Log.d(TAG, "Error while trying to add country to database");
        } finally {
            db.endTransaction();
        }

        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from studenti where id="+id+"", null );
        return res;
    }



    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, STUDENTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateStudent (Integer id, Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nume", student.getNume());
        contentValues.put("prenume", student.getPrenume());
        contentValues.put("email", student.getMail());
        contentValues.put("telefon", student.getTelefon());
        contentValues.put("universitate", student.getuniversitate());
        contentValues.put("cunostinte", student.getCunostinte().toString());
        db.update(STUDENTS_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteStudent (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("studenti",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + STUDENTS_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Student student = null;
        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    student = new Student();
                    student.setId(Integer.parseInt(cursor.getString(0)));
                    student.setNume(cursor.getString(1));
                    student.setPrenume(cursor.getString(2));
                    student.setMail(cursor.getString(3));
                    student.setuniversitate(cursor.getString(4));
                    student.setTelefon(cursor.getString(5));
                    student.setCunostinte(new StringBuilder().append(cursor.getString(6)));

                    // Adding Country to list
                    studentList.add(student);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get countries from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        // return Country list
        return studentList;
    }


}
