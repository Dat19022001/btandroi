package com.example.baith21.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.baith21.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHeper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "quanlyCV.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHeper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title Text, category Text, Nd Text, date Text, ct Integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public long addItem(Item i){
        ContentValues values = new ContentValues();
        values.put("title",i.getTitle());
        values.put("category",i.getCategory());
        values.put("Nd",i.getNd());
        values.put("date",i.getDate());
        values.put("ct",i.isCt());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("items",null,values);
    }
    public List<Item> getAll(){
        List<Item> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "date DESC";
        Cursor rs = st.query("items",null,null,null, null, null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String c = rs.getString(2);
            String Nd = rs.getString(3);
            String date = rs.getString(4);
            boolean a = false;
            if (rs.getInt(5) == 1) a = true;
            list.add(new Item(id,title,c,Nd,date,a));
        }
        return list;
    }
    public int update(Item i){
        ContentValues values = new ContentValues();
        values.put("title",i.getTitle());
        values.put("category",i.getCategory());
        values.put("Nd",i.getNd());
        values.put("date",i.getDate());
        values.put("ct",i.isCt());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id = ?";
        String [] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("items",values,whereClause,whereArgs);
    }
    //delete
    public int delete(int id){
        String whereClause = "id = ?";
        String [] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("items",whereClause,whereArgs);
    }
}
