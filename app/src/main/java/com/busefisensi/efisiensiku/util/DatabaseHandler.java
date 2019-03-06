package com.busefisensi.efisiensiku.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import com.busefisensi.efisiensiku.Model.PassengerModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Efisiensiku";

    private static String TABLE_PASSENGER = "passenger";

    private static String KEY_ID = "id";
    private static String KEY_NAMA = "nama";
    private static final String KEY_HANDPHONE = "handphone";

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PASSENGER_TABLE = "CREATE TABLE " + TABLE_PASSENGER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAMA + " TEXT,"
                + KEY_HANDPHONE + " TEXT" + ")";
        db.execSQL(CREATE_PASSENGER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSENGER);
        onCreate(db);
    }

    public void addRecord(PassengerModel passengerModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, passengerModel.getNama());
        values.put(KEY_HANDPHONE, passengerModel.getHandhphone());

        db.insert(TABLE_PASSENGER, null, values);
        db.close();
    }

    public List<PassengerModel> getAllDataPassenger(){
        List<PassengerModel> passengerList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PASSENGER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                PassengerModel passengerModel = new PassengerModel();
                passengerModel.setId(Integer.parseInt(cursor.getString(0)));
                passengerModel.setNama(cursor.getString(1));
                passengerModel.setHandhphone(cursor.getString(2));

                passengerList.add(passengerModel);
            }while (cursor.moveToNext());
        }
        db.close();
        return passengerList;
    }

    public int updatePassenger(PassengerModel passengerModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HANDPHONE, passengerModel.getHandhphone());

        return db.update(TABLE_PASSENGER, values, KEY_ID + " = ?",new String[]{String.valueOf(passengerModel.getId())});

    }

    public void deletePassenger(PassengerModel passengerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PASSENGER, KEY_ID + " = ?",
                new String[]{String.valueOf(passengerModel.getId())});
        db.close();
    }

}
