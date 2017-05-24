package br.edu.utfpr.md.appcostura.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<T> {
    protected SQLiteDatabase database;
    protected DBHelper sqliteOpenHelper; //vamos criar depois

    public GenericDAO(Context context) {
        sqliteOpenHelper = new DBHelper(context);
    }

    public abstract String[] getColumnsName();
    public abstract String getTableName();
    public abstract int getId(T obj);

    public void open() throws SQLException {
        database = sqliteOpenHelper.getWritableDatabase();
    }
    public void close() {
        sqliteOpenHelper.close();
    }

    public T get(int id) {
        String where = "id = " + id;
        Cursor cursor = database.query(getTableName(), getColumnsName(), where, null,
                null, null, null, "1");
        if(cursor.getCount() == 0) return null;

        cursor.moveToFirst();
        return cursorToObject(cursor);
    }

    public int create(T obj) {
        long insertId = database.insert(getTableName(), null,
                createContentValues(obj));
        return (int)insertId;
    }

    public boolean delete(T obj) {
        int res = database.delete(getTableName(), "id = "+getId(obj), null);
        return res==0 ? false : true;
    }
    public boolean update(T obj) {
        int rows = database.update(getTableName(), createContentValues(obj),
                "id="+getId(obj), null);
        return rows==0 ? false : true;
    }
    public List<T> getAll(String orderBy) {
        Cursor cursor = database.query(getTableName(), getColumnsName(),
                null, null, null, null, orderBy);
        return processarResultadoQuery(cursor);
    }

    //Converter um objeto Cursor em objeto da classe genérica T
    public abstract T cursorToObject(Cursor cursor);

    //Cria um objeto especial para o DB, contendo as informações que
    //são enviadas para serem armazenadas após a execução das queries.
    public abstract ContentValues createContentValues(T obj);

    //Percorre todos os cursores encadeados, e transforma-os em objeto
    // da classe genérica T
    public List<T> processarResultadoQuery(Cursor cursor) {
        List<T> ls = new ArrayList<>();

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            ls.add(cursorToObject(cursor));
            cursor.moveToNext();
        }
        return ls;
    }
}
