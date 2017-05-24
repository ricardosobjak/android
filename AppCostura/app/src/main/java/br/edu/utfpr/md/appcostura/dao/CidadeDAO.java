package br.edu.utfpr.md.appcostura.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import br.edu.utfpr.md.appcostura.model.Pessoa;

public class CidadeDAO extends GenericDAO<String> {

    public CidadeDAO(Context context) {
        super(context);
    }

    @Override
    public String[] getColumnsName() {
        return new String[] {PessoaDAO.SQL.COLUMNS.END_CIDADE} ;
    }

    @Override
    public String getTableName() {
        return PessoaDAO.SQL.TABLE;
    }

    @Override
    public int getId(String obj) {
        return 0;
    }

    @Override
    public String cursorToObject(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(PessoaDAO.SQL.COLUMNS.END_CIDADE));
    }

    @Override
    public List<String> getAll(String orderBy) {
        Cursor c = database.query(getTableName(), getColumnsName(), null, null,
                PessoaDAO.SQL.COLUMNS.END_CIDADE,null,orderBy);
        return processarResultadoQuery(c);
    }

    @Override
    public ContentValues createContentValues(String obj) {
        return null;
    }


}
