package br.edu.utfpr.md.appcostura;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        final LatLng medianeira = new LatLng(-25.299528, -54.09389);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Medianeira - PR").snippet("A UTFPR está aqui").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(medianeira));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapsActivity.this, "clicou em "+marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(MapsActivity.this, "arrastou  "+marker.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        PolylineOptions rectOptions = new PolylineOptions()
                .add(new LatLng(-25.28, -54.00))
                .add(new LatLng(-25.33, -54.00))
                .add(new LatLng(-25.33, -54.05))
                .add(new LatLng(-25.28, -54.05))
                .add(new LatLng(-25.28, -54.00));

        Polyline polyline = mMap.addPolyline(rectOptions);

        // Instantiates a new Polygon object and adds points to define a rectangle
        PolygonOptions rectOptionsPoligono = new PolygonOptions()
                .add(new LatLng(-25.25, -54.0),
                        new LatLng(-25.28, -54.0),
                        new LatLng(-25.28, -54.188),
                        new LatLng(-25.25, -54.188),
                        new LatLng(-25.25, -54.0));


        Polygon polygon = mMap.addPolygon(rectOptionsPoligono);
        polygon.setStrokeColor(Color.RED);
        polygon.setFillColor(Color.YELLOW);

        GoogleMap map;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);


    }
}
