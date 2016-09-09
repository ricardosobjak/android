package br.edu.utfpr.android.intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /*
        Exemplo: Abrindo uma atividade.
     */
    public void openExplicita1(View v) {
        Intent intent = new Intent(MainActivity.this, Explicita1Activity.class);
        startActivity(intent);
    }

    /*
        Exemplo: Abrindo uma atividade e passando parâmetros.
     */
    public void openExplicita2(View v) {
        // Criando a Intenção
        Intent intent = new Intent(MainActivity.this, Explicita2Activity.class);

        // Adicionando parâmetros na intenção
        intent.putExtra("nome", "Ana Machado");
        intent.putExtra("idade", 21);

        // Abrindo a atividade com base na intenção
        startActivity(intent);
    }

    /*
        Exemplo: Abrindo uma atividade e aguardando um retorno.
        Foi utilizado o método startActivityForResult, passando como parâmetros:
            - A Intent;
            - O código de identificação da atividade que será aberta. Pode ser qualquer
              definido pelo desenvolvedor.
     */
    public void openExplicita3(View v) {
        Intent intent = new Intent(MainActivity.this, Explicita3Activity.class);
        startActivityForResult(intent, 1);
    }

    /*
        Abrindo uma intenção implícita para enviar e-mail
     */
    public void openEmail(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL,	new String[] { "contato@utfpr.edu.br" });
        startActivity(Intent.createChooser(intent, "Enviar email"));
    }

    /*
        Abrindo uma intenção implícita para realizar uma chamada telefônica
     */
    public void openTelefone(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:3240-8000"));
        startActivity(intent);
    }

    /*
        Abrindo uma intenção implícita para acessar um site
     */
    public void openSite(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.utfpr.edu.br"));
        startActivity(intent);
    }

    /*
        O método onActivityResult é invocado quando uma atividade foi aberta
        utilizando o método startActivityForResult.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        StringBuilder sb = new StringBuilder();
        sb.append("Atividade de código ");
        sb.append(requestCode);
        sb.append(" foi encerrada!\n");
        sb.append("O código de retorno foi ");
        sb.append(requestCode);
        sb.append("\nA mensagem retornada foi: ");
        sb.append(data.getExtras().getString("mensagem"));
        Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();


        super.onActivityResult(requestCode, resultCode, data);
    }
}