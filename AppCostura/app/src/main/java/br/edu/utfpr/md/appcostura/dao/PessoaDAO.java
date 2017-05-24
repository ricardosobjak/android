package br.edu.utfpr.md.appcostura.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.utfpr.md.appcostura.model.Endereco;
import br.edu.utfpr.md.appcostura.model.Pessoa;

public class PessoaDAO extends GenericDAO<Pessoa> {
    public static class SQL {
        public static class COLUMNS {
            public static final String ID = "id";
            public static final String NOME = "nome";
            public static final String NASCIMENTO = "nascimento";
            public static final String FOTO = "foto";
            public static final String TELEFONE = "telefone";
            public static final String CELULAR = "celular";
            public static final String CPF = "cpf";
            public static final String EMAIL = "email";
            public static final String SENHA = "senha";
            public static final String COSTUREIRO = "costureiro";
            public static final String END_LOGRADOURO = "end_logradouro";
            public static final String END_NUMERO = "end_numero";
            public static final String END_CEP = "end_cep";
            public static final String END_BAIRRO = "end_bairro";
            public static final String END_CIDADE = "end_cidade";
            public static final String END_UF = "end_uf";

            public static String[] getColumnsName() {
                return new String[] {ID,NOME,NASCIMENTO,FOTO,TELEFONE,CELULAR,CPF,EMAIL,SENHA,
                        COSTUREIRO,END_LOGRADOURO,END_NUMERO,END_CEP,END_BAIRRO,
                        END_CIDADE,END_UF};
            }
        }
        public static final String TABLE = "tb_pessoa";

        public static final String CREATE = "CREATE TABLE " + TABLE + " ( " +
                COLUMNS.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNS.NOME + " TEXT NOT NULL, " +
                COLUMNS.NASCIMENTO + " DATETIME, " +
                COLUMNS.TELEFONE + " TEXT, " +
                COLUMNS.CELULAR + " TEXT, " +
                COLUMNS.CPF + " TEXT, " +
                COLUMNS.EMAIL + " TEXT, " +
                COLUMNS.SENHA + " TEXT, " +
                COLUMNS.COSTUREIRO + " INTEGER, " +
                COLUMNS.END_LOGRADOURO + " TEXT, " +
                COLUMNS.END_NUMERO + " TEXT, " +
                COLUMNS.END_CEP + " TEXT, " +
                COLUMNS.END_BAIRRO + " TEXT, " +
                COLUMNS.END_CIDADE + " TEXT, " +
                COLUMNS.END_UF + " TEXT, " +
                COLUMNS.FOTO + " BLOB" +
                ");";

        public static final String DROP = "DROP TABLE IF EXISTS " + TABLE;
    }

    public PessoaDAO(Context context) {
        super(context);
    }

    @Override
    public String[] getColumnsName() {
        return SQL.COLUMNS.getColumnsName();
    }

    @Override
    public String getTableName() {
        return SQL.TABLE;
    }

    @Override
    public int getId(Pessoa obj) {
        return obj.getId();
    }

    @Override
    public Pessoa cursorToObject(Cursor cursor) {
        /*
            cursor.getTIPODADO( índice da coluna )
            cursor.getColumnIndex( nome da coluna )
         */
        Pessoa p = new Pessoa();
        p.setId( cursor.getInt(cursor.getColumnIndex(SQL.COLUMNS.ID)));
        p.setNome( cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.NOME)));
        p.setCelular(cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.CELULAR)));
        p.setCpf(cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.CPF)));
        p.setEmail(cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.EMAIL)));
        p.setSenha(cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.SENHA)));
        p.setTelefone(cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.TELEFONE)));
        /*
        try {
            p.setLastUpdate(new Date(cursor.getInt(cursor.getColumnIndex(SQL.COLUMNS.LAST_UPDATE))));
        } catch (Exception e){
            p.setLastUpdate(null);
            e.printStackTrace();
        }
        */

        Endereco e = new Endereco();
        e.setBairro(cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.END_BAIRRO)));
        e.setCep(cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.END_CEP)));
        e.setCidade(cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.END_CIDADE)));
        e.setLogradouro(cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.END_LOGRADOURO)));
        e.setNumero(cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.END_NUMERO)));
        e.setUf(cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.END_UF)));
        p.setEndereco(e);

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(
                    cursor.getString(cursor.getColumnIndex(SQL.COLUMNS.NASCIMENTO)));
            p.setDataNascimento(date);
        }catch (ParseException ex) {
            p.setDataNascimento(null);
        }

        p.setFoto( cursor.getBlob(cursor.getColumnIndex(SQL.COLUMNS.FOTO)));

        return p;
    }

    @Override
    public ContentValues createContentValues(Pessoa p) {
        ContentValues cv = new ContentValues();

        cv.put(SQL.COLUMNS.NOME, p.getNome());
        cv.put(SQL.COLUMNS.CPF, p.getCpf());
        cv.put(SQL.COLUMNS.EMAIL, p.getEmail());
        cv.put(SQL.COLUMNS.CELULAR, p.getCelular());
        cv.put(SQL.COLUMNS.TELEFONE, p.getTelefone());
        cv.put(SQL.COLUMNS.SENHA, p.getSenha());

        try {
            cv.put(SQL.COLUMNS.END_BAIRRO, p.getEndereco().getBairro());
            cv.put(SQL.COLUMNS.END_CEP, p.getEndereco().getCep());
            cv.put(SQL.COLUMNS.END_CIDADE, p.getEndereco().getCidade());
            cv.put(SQL.COLUMNS.END_UF, p.getEndereco().getUf());
            cv.put(SQL.COLUMNS.END_LOGRADOURO, p.getEndereco().getLogradouro());
            cv.put(SQL.COLUMNS.END_NUMERO, p.getEndereco().getNumero());
        } catch (NullPointerException ex) {
            cv.put(SQL.COLUMNS.END_BAIRRO, "");
            cv.put(SQL.COLUMNS.END_CEP, "");
            cv.put(SQL.COLUMNS.END_CIDADE, "");
            cv.put(SQL.COLUMNS.END_UF, "");
            cv.put(SQL.COLUMNS.END_LOGRADOURO, "");
            cv.put(SQL.COLUMNS.END_NUMERO, "");
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            cv.put(SQL.COLUMNS.NASCIMENTO, dateFormat.format(p.getDataNascimento()));
        } catch (NullPointerException ex) {
            cv.put(SQL.COLUMNS.NASCIMENTO, "");
        }
        cv.put(SQL.COLUMNS.FOTO, p.getFoto());

        return cv;
    }
}
