package br.edu.utfpr.md.appcostura.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.edu.utfpr.md.appcostura.CadastroFormActivity;
import br.edu.utfpr.md.appcostura.model.Pessoa;

public class DBHelper extends SQLiteOpenHelper {
    // Nome do Bando de Dados
    protected static final String DATABASE_NAME = "appcostura.db";
    // Controla a versão do banco de dados do APP
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(getClass().getName(), "Criando as tabelas do AppCostura");
        db.execSQL(PessoaDAO.SQL.CREATE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(getClass().getName(), "Atualizando o DB da versão " +
        oldVersion + " para " + newVersion + ".");

        db.execSQL(PessoaDAO.SQL.DROP);
        onCreate(db);
    }
}
