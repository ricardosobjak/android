package br.edu.utfpr.android.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtLatitude;
    private EditText edtLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.edtLatitude = (EditText) findViewById(R.id.edtLatitude);
        this.edtLongitude = (EditText) findViewById(R.id.edtLongitude);
    }



    /**
     * Método responsável por obter a localização via Serviços de Localização
     * @param v
     */
    public void obterLocalizacaoGPS(View v) {

        if(Permission.check(this, Manifest.permission.ACCESS_FINE_LOCATION, 1))
            if( Permission.check(this, Manifest.permission.ACCESS_COARSE_LOCATION, 2)) {
                obterLocalizacao();
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1 || requestCode == 2) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obterLocalizacao();
            } else {
                Toast.makeText(this, "Sem permissão para acessar a Localização", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void obterLocalizacao() {
               /*
		 * Obtém uma referência para o serviço de gerenciador de localização do SO.
		 * Não é possível instanciá-lo, deve ser buscado no contexto do sistema.
		 */
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Define um Listener para responder à eventos de atualização da localização
        LocationListener locationListener = new LocationListener() {
            /*
             * Invocado quando alterar a localização(non-Javadoc)
             * @see android.location.LocationListener#onLocationChanged(android.location.Location)
             */
            public void onLocationChanged(Location location) {
                Log.v(getClass().getSimpleName() + " onLocationChanged", "Localização alterada: LAT: " +
                        String.valueOf(location.getLatitude()) + ", LONG: " +
                        String.valueOf(location.getLongitude()));

                // Atualiza o valos dos campos na tela
                edtLatitude.setText(String.valueOf(location.getLatitude()));
                edtLongitude.setText(String.valueOf(location.getLongitude()));

                // Solicita a remoção do listener
                locationManager.removeUpdates(this);
            }

            /*
             * Invocado quando mudar o status do provedor
             * (OUT_OF_SERVICE / TEMPORARILY_UNAVAILABLE / AVAILABLE)
             *
             * @see android.location.LocationListener#onStatusChanged(java.lang.String, int, android.os.Bundle)
             */
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.v(getClass().getSimpleName() + " onStatusChanged", "Provider: " + provider + ", Status: " + status);
            }

            /*
             * Invocado quando o provedor de serviço é habilitado
             * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
             */
            public void onProviderEnabled(String provider) {
                Log.v(getClass().getSimpleName() + " onProviderEnabled", "Provider: " + provider);
            }

            /*
             * Invocado quando o provedor de serviço é desabilitado
             * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
             */
            public void onProviderDisabled(String provider) {
                Log.v(getClass().getSimpleName() + " onProviderDisabled", "Provider: " + provider);
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
