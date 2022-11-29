package com.example.subjectstudent_207ct65664;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentDB.db";
    private static final String TABLE_STUDENTS = "students";
    private static final String COLUMN_ID = "StudentID";
    private static final String COLUMN_HO = "StudentHo";
    private static final String COLUMN_TEN = "StudentTen";
    private static final String COLUMN_CLASS = "StudentClass";

    DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_STUDENTS
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_HO + " TEXT, " + COLUMN_TEN + " TEXT, " + COLUMN_CLASS + " TEXT " +")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(sqLiteDatabase);
    }

    public long addHandler(Student student) {
        long id;
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_HO, student.getStudentHo());
        values.put(COLUMN_TEN, student.getStudentTen());
        values.put(COLUMN_CLASS, student.getStudentClass());
        SQLiteDatabase db = this.getWritableDatabase();
        id = db.insert(TABLE_STUDENTS, null, values);

        db.close();
        return id;
    }

    public String loadHandler() {
        String result = "";
        String query = "SELECT * FROM "+ TABLE_STUDENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            String result_3 = cursor.getString(3);
            result += String.valueOf(result_0) + " " + result_1 + " " + result_2 + " " + result_3 + " " + System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        if(result.equals("")) {
            result = "No record found";
        }
        return result;
    }

    public boolean updateHandler(int ID, String Ho, String Ten, String Class) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_HO, Ho);
        args.put(COLUMN_TEN, Ten);
        args.put(COLUMN_CLASS, Class);
        return db.update(TABLE_STUDENTS, args, COLUMN_ID + "=" + ID, null) > 0;
    }

    public boolean removeHandler(int ID) {
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " + COLUMN_ID + " = '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if(cursor.moveToFirst()) {
            student.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_STUDENTS, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(student.getId())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}

