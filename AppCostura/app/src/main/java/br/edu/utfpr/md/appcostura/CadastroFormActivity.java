package br.edu.utfpr.md.appcostura;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.edu.utfpr.md.appcostura.dao.PessoaDAO;
import br.edu.utfpr.md.appcostura.helper.Permission;
import br.edu.utfpr.md.appcostura.model.Endereco;
import br.edu.utfpr.md.appcostura.model.Pessoa;

public class CadastroFormActivity extends AppCompatActivity {
    /*
        Classe de suporte para as views da Atividade.
        Concentramos as referências para as views e ações das view nesta classe.
     */
    class ViewHolder {
        final EditText nome = (EditText)findViewById(R.id.edtNome);
        final EditText cpf = (EditText)findViewById(R.id.edtCpf);
        final EditText nascimento = (EditText)findViewById(R.id.edtNascimento);
        final EditText telefone = (EditText)findViewById(R.id.edtFixo);
        final EditText celular = (EditText)findViewById(R.id.edtCelular);
        final EditText email = (EditText)findViewById(R.id.edtEmail);
        final EditText senha = (EditText)findViewById(R.id.edtSenha);
        final EditText bairro = (EditText)findViewById(R.id.edtBairro);
        final EditText logradouro = (EditText)findViewById(R.id.edtLogradouro);
        final EditText numero = (EditText)findViewById(R.id.edtNumero);
        final EditText cep = (EditText)findViewById(R.id.edtCep);
        final EditText cidade = (EditText)findViewById(R.id.edtCidade);
        final EditText uf = (EditText)findViewById(R.id.edtEstado);
        final ImageView foto = (ImageView)findViewById(R.id.imageView);
        final TextView latlong = (TextView)findViewById(R.id.txtLatLong);

        public ViewHolder() {

            //Setando a ação de click na view nascimento
            nascimento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openNascimentoAlert();
                }
            });

            //Setando a ação de recebimento de foco na view nascimento
            nascimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus) openNascimentoAlert();
                }
            });

        }

        public void bind(Pessoa p) {
            nome.setText(p.getNome());
            cpf.setText(p.getCpf());
            celular.setText(p.getCelular());
            telefone.setText(p.getTelefone());
        }
    }

    private static final int REQUEST_PICTURE = 100;
    private static final int REQUEST_PICTURE_GALLERY = 101;

    // Formatador de datas
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private ViewHolder viewHolder;

    private Calendar dataNascimento;

    private PessoaDAO pessoaDAO = new PessoaDAO(this);
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_form);

        this.viewHolder = new ViewHolder();

        this.dataNascimento = Calendar.getInstance();
        this.dataNascimento.set(Calendar.getInstance().get(Calendar.YEAR)-20,0,1,0,0,0);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Salvando cadastro...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                actionSave(view);
            }
        });


        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            int id = bundle.getInt("id_pessoa", 0);
            if(id > 0) {
                pessoaDAO.open();
                pessoa = pessoaDAO.get(id);
                pessoaDAO.close();
                viewHolder.bind(pessoa);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cadastro_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_sms) {
            Toast.makeText(this, "Enviar SMS", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.action_location) {
            if(Permission.check(this, Manifest.permission.ACCESS_FINE_LOCATION, 10)) {
                obterLocalizacaoGPS();
            }
        }
        if(item.getItemId() == R.id.action_maps) {
            Intent i = new Intent(this, MapsActivity.class);
            startActivity(i);
        }


        return true;
    }

    protected void openNascimentoAlert() {
        // Objeto 'ouvidor' que contém a ação que será realizada após selecionar uma
        // data no DatePickerDialog.
        DatePickerDialog.OnDateSetListener dtListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dataNascimento.set(year,month,dayOfMonth);
                viewHolder.nascimento.setText(dateFormat.format(dataNascimento.getTime()));
            }
        };

        // Instanciado e abrindo o alerta de seleção da data
        new DatePickerDialog(this, dtListener,
                dataNascimento.get(Calendar.YEAR),
                dataNascimento.get(Calendar.MONTH),
                dataNascimento.get(Calendar.DAY_OF_MONTH)
        ).show();

    }

    protected void actionSave(View v) {
        //FLAGS
        boolean cancel = false; //cancela o cadastro
        View focusView = null; //Colocar o foco na view que aqui for referenciada

        Pessoa p = getPessoaFromViews();

        if(TextUtils.isEmpty(viewHolder.nome.getText().toString())) {
            viewHolder.nome.setError("Este campo é obrigatório");
            focusView = viewHolder.nome;
            cancel=true;
        }
        //else if(proxima validacao) ...
        //else if(proxima validacao) ...

        if(cancel) {
            focusView.requestFocus();
        } else {
            //procede com o armazenamento
            pessoaDAO.open();
            int id = pessoaDAO.create(p);
            pessoaDAO.close();

            if(id > 0)
                Toast.makeText(this,
                        p.getNome() + ", bem vindo(a) ao Costura fácil APP!",
                        Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,
                        "Lamentamos, mas ocorreu um erro ao gravar o cadastro!",
                        Toast.LENGTH_LONG).show();
        }

    }

    protected Pessoa getPessoaFromViews() {
        Pessoa p = new Pessoa();

        if(pessoa != null && p.getId() > 0)
            p.setId(pessoa.getId());
        p.setNome(viewHolder.nome.getText().toString());
        p.setTelefone(viewHolder.telefone.getText().toString());
        p.setCelular(viewHolder.celular.getText().toString());
        p.setSenha(viewHolder.senha.getText().toString());
        p.setEmail(viewHolder.email.getText().toString());
        p.setCpf(viewHolder.cpf.getText().toString());

        Endereco e = new Endereco();
        e.setLogradouro(viewHolder.logradouro.getText().toString());
        e.setNumero(viewHolder.numero.getText().toString());
        e.setCep(viewHolder.cep.getText().toString());
        e.setBairro(viewHolder.bairro.getText().toString());
        e.setCidade(viewHolder.cidade.getText().toString());
        e.setUf(viewHolder.uf.getText().toString());
        p.setEndereco(e);

        if(dataNascimento != null)
            p.setDataNascimento(dataNascimento.getTime());

        try {
            Bitmap bitmap = ((BitmapDrawable)viewHolder.foto.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            p.setFoto(baos.toByteArray()); //implementamos depois
        } catch (NullPointerException ex) {
            p.setFoto(null);
        }

        return p;
    }

    // Tirar Foto
    protected void actionCamera(View v) {
        // Intenção implícita para app de captura de fotos
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(i, REQUEST_PICTURE);
    }

    // Buscar na galeria do Dispositivo
    protected void actionGallery(View v) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i, REQUEST_PICTURE_GALLERY);
    }

    protected void actionRemoverFoto(View v) {
        viewHolder.foto.setImageBitmap(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_PICTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap)extras.get("data");
            viewHolder.foto.setImageBitmap(imageBitmap);
        }
        if(requestCode == REQUEST_PICTURE_GALLERY && resultCode == RESULT_OK) {
            Uri image = data.getData();
            setPhotoToImageView(image);
        }
    }
    protected void setPhotoToImageView(Uri uri) {
        try(InputStream is = this.getContentResolver().openInputStream(uri)) {
            Bitmap image = BitmapFactory.decodeStream(is);
            viewHolder.foto.setImageBitmap(image);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implementado no AppCostura-Parte06
     * Método responsável por obter a localização via Serviços de Localização
     */
    public void obterLocalizacaoGPS() {
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Atualiza o valos dos campos na tela
                String pos = String.valueOf(location.getLatitude()) + ", " + String.valueOf(location.getLongitude());
                Toast.makeText(CadastroFormActivity.this, location.toString(), Toast.LENGTH_SHORT).show();
                //viewHolder.latlong.setText(pos);

                 // Solicita a remoção do listener
                locationManager.removeUpdates(this);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

		/*
		 *  Registrando um listener para ser invocado quando houver eventos de update
		 *  Argumentos:
		 *  	PROVEDOR,
		 *  	TEMPO MÍNIMO PARA MUDANÇA DE POSIÇÃO,
		 *  	DISTÂNCIA MÍNIMA PARA MUDANÇA DE POSIÇÃO
		 *  	LISTENER
		 */
        String locationProvider = LocationManager.GPS_PROVIDER; //GPS_PROVIDER ou NETWORK_PROVIDER ou PASSIVE_PROVIDER
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
    }
}
