package br.edu.utfpr.md.appcostura;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import br.edu.utfpr.md.appcostura.dao.CidadeDAO;
import br.edu.utfpr.md.appcostura.dao.PessoaDAO;
import br.edu.utfpr.md.appcostura.dialog.ExcluirCadastroDialog;
import br.edu.utfpr.md.appcostura.model.Pessoa;

public class CadastroListActivity extends AppCompatActivity {

    class ViewHolder {
        final RecyclerView list = (RecyclerView)findViewById(R.id.list);
        RecyclerView.Adapter mAdapter;

        ViewHolder() {
            list.setHasFixedSize(true);
            list.setLayoutManager(new LinearLayoutManager(CadastroListActivity.this));
        }
    }

    private ViewHolder viewHolder;
    private PessoaDAO pessoaDAO = new PessoaDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_list);

        viewHolder = new ViewHolder();
    }

    @Override
    protected void onResume() {
        pessoaDAO.open();
        super.onResume();
        updateList();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pessoaDAO.close();
    }

    protected void updateList() {
        List<Pessoa> pessoas = pessoaDAO.getAll(PessoaDAO.SQL.COLUMNS.NOME);

        System.out.println(pessoas);
        viewHolder.mAdapter = new PessoaAdapterRV(pessoas);
        viewHolder.list.setAdapter(viewHolder.mAdapter);
    }


    protected void editCadastro(Pessoa pessoa) {
        Intent i = new Intent(this, CadastroFormActivity.class);
        i.putExtra("id_pessoa", pessoa.getId());
        startActivity(i);
    }

    private AlertDialog createOptionsDialog(final Pessoa pessoa) {
        final CharSequence[] items = {
                "Editar", "Excluir", "Adicionar aos contatos", "Ligar"
        };

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("O que deseja fazer?");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0 : { //Editar
                        editCadastro(pessoa);
                        break;
                    }
                    case 1: { //Excluir
                        final ExcluirCadastroDialog excluirDialog = new ExcluirCadastroDialog(CadastroListActivity.this,
                            pessoa, pessoaDAO);

                        excluirDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                if(excluirDialog.isDeletado())
                                    updateList();
                            }
                        });



                        break;
                    }
                    case 2: { break; }
                    case 3: {
                        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+pessoa.getCelular()));
                        startActivity(i);
                        break;
                    }
                }
            }
        });
        return builder.create();

    }




    // Classe adaptadora do recyclerView
    protected class PessoaAdapterRV extends RecyclerView.Adapter<PessoaAdapterRV.ViewHolder> {
        private List<Pessoa> pessoas;

        public PessoaAdapterRV(List<Pessoa> pessoas) {
            this.pessoas = pessoas;
        }

        @Override
        public PessoaAdapterRV.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(CadastroListActivity.this)
                    .inflate(R.layout.list_pessoa,parent, false);
            PessoaAdapterRV.ViewHolder vh = new PessoaAdapterRV.ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(PessoaAdapterRV.ViewHolder holder, int position) {
            holder.nome.setText(pessoas.get(position).getNome());
            holder.pessoa = pessoas.get(position);
        }

        @Override
        public int getItemCount() {
            return pessoas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements
                View.OnClickListener, View.OnLongClickListener {
            public Pessoa pessoa;
            public TextView nome;

            public ViewHolder(View view) {
                super(view);
                this.nome = (TextView) view.findViewById(R.id.txtNome);
                view.setOnClickListener(this);
                view.setOnLongClickListener(this);
            }

            @Override
            public void onClick(View v) {
                editCadastro(pessoa);
            }

            @Override
            public boolean onLongClick(View v) {
                createOptionsDialog(pessoa).show();
                return true;
            }
        }
    }
}
