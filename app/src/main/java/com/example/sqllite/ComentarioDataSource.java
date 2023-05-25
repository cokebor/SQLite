package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ComentarioDataSource {
    private SQLiteDatabase database;
    private SqlHelper helper;

    private String[] allColumns = { SqlHelper.COLUMN_ID, SqlHelper.COLUMN_COMENTARIO };

    public ComentarioDataSource(Context context) {
        super();
        helper=new SqlHelper(context);
    }

    public void open() throws SQLiteException {
        database=helper.getWritableDatabase();
    }

    public void close() throws SQLiteException{
        database.close();
    }

    public void delete(Comentario comentario){
        long id= comentario.getId();
        database.delete(SqlHelper.TABLE_NAME,SqlHelper.COLUMN_ID +" = " +id,null);
    }

    public Comentario create(Comentario comentario) {

        ContentValues values = new ContentValues();
        values.put(SqlHelper.COLUMN_COMENTARIO, comentario.getComentario());

        //INSERTAMOS EN LA BD
        long insertId = database.insert(SqlHelper.TABLE_NAME, null,values);


        Cursor cursor = database.query(SqlHelper.TABLE_NAME, allColumns, SqlHelper.COLUMN_ID + " = " + insertId, null,null, null, null);
        cursor.moveToFirst();
        Comentario comentarioFromDb =new Comentario();

        comentarioFromDb.setId(cursor.getLong(0));
        //Columna 1
        comentarioFromDb.setComentario(cursor.getString(1));
        cursor.close();
        return comentarioFromDb;
    }

    public List<Comentario> getAll() {
        List<Comentario> comentarios = new ArrayList<Comentario>();

        Cursor cursor = database.query(SqlHelper.TABLE_NAME,allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comentario comentarioFromDb =new Comentario();
            comentarioFromDb.setId(cursor.getLong(0));
            comentarioFromDb.setComentario(cursor.getString(1));
            comentarios.add(comentarioFromDb);
            cursor.moveToNext();
        }
        cursor.close();
        return comentarios;
    }
}
