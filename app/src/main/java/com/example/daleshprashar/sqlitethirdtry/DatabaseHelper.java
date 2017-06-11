package com.example.daleshprashar.sqlitethirdtry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DALESH PRASHAR on 2/19/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT  , NAME TEXT , SURNAME TEXT , MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
// method that is gonna insert data when called with enteries of edittexts.
    // it has a writable call to sqlitedatabase and that insierts the values
    // of contentvalues.
    public  boolean insertData(String name , String surname , String marks)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2 , name);
        contentValues.put(COL_3 , surname);
        contentValues.put(COL_4 , marks);
        sqLiteDatabase.insert(TABLE_NAME ,null ,contentValues);
        long result = sqLiteDatabase.insert(TABLE_NAME ,null ,contentValues);
        if (result == -1)
            return  false;
        else
            return true;
    }
//TO VIEW the stuff. cursor moves from pt. a to z.
    //select all rows and columns  from tablename and get all info in cursor.
    // again this method is called in mainactivity..
    //above we have edit texts.
    // its not called straightaway , a further method is made and then its called.
    // further two methods are made for this method.. one ---- , second the dialog.
    //
    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME , null);
        return  res;

    }
    public  boolean updateData(String id , String name , String surname , String marks)
    {
        // ID we are using because updation is gonna be done on basis of
        //      id , because rahul name can be same for row 2 and 7.
        // INSTANCE OF SQLiteDatabase
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // INSTANCE OF ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1 , id);
        contentValues.put(COL_2 , name);
        contentValues.put(COL_3 , surname);
        contentValues.put(COL_4 , marks);
       // 4 ARGUMENTS table name  , contentvalues , condition which we passe : here we have : on basis of id.
        //fourth value of basis on which we are gonna update data.or i guess where we are gonna put in.
        sqLiteDatabase.update(TABLE_NAME ,contentValues ,"id = ?",new String[]{id});
        return  true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME ,"id = ?",new String[]{id});


    }



}
