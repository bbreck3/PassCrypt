package com.passcrypt.passcrypt;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by breck on 12/16/2017.
 */

public class DBPinHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "pins_db.db";
    public static final String TABLE_NAME = "pins";
    //public static final String COLUMN_ID = "id";
    public static final String COLUMN_PIN = "pin";
    //public static final String COLUMN_COMPANY = "company";
    public static final String COLUMN_KEY = "key";

    public DBPinHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text," + COLUMN_KEY + " text" +")"
        );*/
        /*db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text)"
        );*/
        db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_PIN + " text primary key)"// + COLUMN_PASSWORD + " text)"
        );

    } public void createDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        /*db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text," + COLUMN_KEY + " text" +")"
        );*/
        /*db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text)"
        );*/

        db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_PIN + " text primary key)"// + COLUMN_PASSWORD + " text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertPin( String pin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("id", id);
        contentValues.put("pin", pin);
        //contentValues.put("password", password);
        //contentValues.put("key", key);
        db.insert("pins","null", contentValues);
        if (contentValues != null) {
            return true;
        } else return false;
    }
    public boolean updatePin ( String pin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pin", pin);
        //contentValues.put("password", password);
        db.update("pins", contentValues, "company = ? ", new String[] { pin } );

        if (contentValues!= null){
            return true;
        }
        else{
            db.close();

            return false;
        }
    }
    public String getPin(String pin){
        String userPin="";
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_NAME, new String[]{
                            COLUMN_PIN}, COLUMN_PIN + "=?",
                    new String[]{pin/*String.valueOf(company)*/}, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            userPin = cursor.getString(0);
            //password = new Password(cursor.getString(0), cursor.getString(1));


        } catch (Exception e) {
            e.printStackTrace();
        }
        // return password
        return userPin;
    }

    /* public Password getKey(String key){
         Password password = null;
         try {
             SQLiteDatabase db = this.getReadableDatabase();

             Cursor cursor = db.query(TABLE_NAME, new String[]{
                             COLUMN_COMPANY, COLUMN_PASSWORD}, COLUMN_KEY + "=?",
                     new String[]{key*//*String.valueOf(company)*//*}, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();


            password = new Password(cursor.getString(0), cursor.getString(1));


        } catch (Exception e) {
            e.printStackTrace();
        }
        // return password
        return password;
    }*/
    public String getPin(){
        //ArrayList<String> passwordList = new ArrayList<String>();
        String pin="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from pins", null);// +company +"",new String[] {company});
        if (result.moveToFirst()) {
            do {
                pin = result.getString(0);
                /*Password password = new Password();

                password.setCompany(result.getString(0));
                password.setPassword(result.getString(1));
                // Adding contact to list
                passwordList.add(password.getCompany());*/
            } while (result.moveToNext());
        }
        db.close();
        result.close();
        return pin;
    }
    public void deleteAllPasswords(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public Integer deletePassword(String company){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("passwords", "company=? ",new String[]{company});

    }
}
