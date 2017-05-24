package br.edu.utfpr.md.appcostura.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import br.edu.utfpr.md.appcostura.dao.GenericDAO;
import br.edu.utfpr.md.appcostura.model.Pessoa;

public class ExcluirCadastroDialog extends AlertDialog {
    private boolean deletado = false;

    public ExcluirCadastroDialog(@NonNull final Context context,
                                    final Pessoa pessoa,
                                    final GenericDAO<Pessoa> dao) {
        super(context);

        setTitle("Excluir");
        setMessage("Deseja excluir " + pessoa.getNome() + "?");

        setButton(BUTTON_POSITIVE, "Sim", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dao.open();
                if(dao.delete(pessoa)) {
                    Toast.makeText(context, "Cadastro excluído", Toast.LENGTH_LONG).show();
                    deletado = true;
                } else
                    Toast.makeText(context, "Falha ao excluir cadastro", Toast.LENGTH_LONG).show();
                //dao.close();
                dismiss(); //fecha o dialog
            }
        });

        setButton(BUTTON_NEGATIVE, "Não", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        show();
    }

    public boolean isDeletado() {
        return deletado;
    }
}
