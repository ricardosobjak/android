package br.edu.utfpr.android.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Esta activity mostra a integração com a câmera usando a aplicação nativa de câmera do Android
 */
public class Camera1Activity extends AppCompatActivity {

	/**
	 * Código de requisição para poder identificar quando a activity da câmera é finalizada
	 */
	private static final int REQUEST_PICTURE = 1000;


	private static final int REQUEST_PERMISSION_CAMERA = 1;
	private static final int REQUEST_PERMISSION_READ_STORAGE = 2;

	/**
	 * View onde a foto tirada será exibida
	 */
	private ImageView imageView;
	
	/**
	 * Local de armazenamento da foto tirada 
	 */
	private File imageFile;

	/**
	 * Invocado quando a activity é criada
	 * @see Activity#onCreate(Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.imageView = (ImageView) findViewById(R.id.imagem);
		
		// Obtém o local onde as fotos são armazenadas na memória externa do dispositivo
		File picsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		// Define o local completo onde a foto será armazenada (diretório + arquivo)
		this.imageFile = new File(picsDir, "foto.jpg");
	}

	/**
	 * Método que tira uma foto
	 * @param v
	 */
	public void takePicture(View v) {
		if (ContextCompat.checkSelfPermission(this,
				Manifest.permission.CAMERA)
				!= PackageManager.PERMISSION_GRANTED) {

			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.CAMERA},
					REQUEST_PERMISSION_CAMERA);

		} else {
			// Abre a aplicação de câmera
			openCamera();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		//super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		switch (requestCode) {
			case REQUEST_PERMISSION_CAMERA : {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					openCamera();
				} else {
					Toast.makeText(this, "Sem permissão para acessar a Câmera", Toast.LENGTH_SHORT).show();
				}
				return;
			}
			case REQUEST_PERMISSION_READ_STORAGE : { // Permissão:  Manifest.permission.READ_EXTERNAL_STORAGE
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					readImageFromFile(this.imageFile);
				} else {
					Toast.makeText(this, "Sem permissão para acessar o storage", Toast.LENGTH_SHORT).show();
				}
				return;
			}
			default:
				super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
	}

	public void openCamera() {
		// Cria uma Intent que seré usada para abrir a aplicação da câmera
		Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		// Indica na Intent o local onde a foto tirada deve ser armazenada
		i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));

		// Abre o aplicativo da Câmera
		startActivityForResult(i, REQUEST_PICTURE);
	}

	public void readImageFromFile(File imageFile) {
		FileInputStream fis = null;

		try {
			try {
				// Cria um FileInputStream para ler a foto tirada pela câmera
				fis = new FileInputStream(imageFile);

				// Converte a stream em um objeto Bitmap
				Bitmap picture = BitmapFactory.decodeStream(fis);

				// Exibe o bitmap na view, para que o usuário veja a foto tirada
				imageView.setImageBitmap(picture);
			} finally {
				if (fis != null) {
					fis.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**
	 * Método chamado quando a aplicação nativa da câmera é finalizada
	 * @see Activity#onActivityResult(int, int, Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Verifica o código de requisição e se o resultado é OK (outro resultado indica que
		// o usuário cancelou a tirada da foto)
		if (requestCode == REQUEST_PICTURE && resultCode == RESULT_OK) {
			if (Permission.check(this, Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_PERMISSION_READ_STORAGE)) {
				readImageFromFile(this.imageFile);
			}
		}
	}
}