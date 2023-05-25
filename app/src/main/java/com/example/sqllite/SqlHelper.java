package com.example.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SqlHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="comentarios.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME="tabla_comentario";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_COMENTARIO="comentario";


    public SqlHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE "+ TABLE_NAME + " (" + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_COMENTARIO + " text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),"onUpgrade version antigua" + oldVersion + " version nueva " + newVersion );
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
