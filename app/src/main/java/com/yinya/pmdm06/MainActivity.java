package com.yinya.pmdm06;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else Log.d("Google maps", "Map error");

        // Inicializa el cliente de ubicación
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        //LUGARES
        LatLng mrk1station = new LatLng(36.80934308883409, -2.5832089509371428);
        LatLng mrk2station = new LatLng(37.6322, -3.8214);
        LatLng mrk3station = new LatLng(37.90481944, -4.77816389);
        LatLng mrk4station = new LatLng(37.345707, -5.981003);
        LatLng mrk5station = new LatLng(37.2771751, -6.9412007);
        LatLng mrk6station = new LatLng(36.5299, -6.2994);
        LatLng mrk7station = new LatLng(36.593506689594, -4.5293087804356);
        LatLng mrk8station = new LatLng(36.6928291, -4.4477315);
        LatLng mrk9station = new LatLng(37.183862, -3.602518);
        LatLng mrk10station = new LatLng(36.83837697453805, -2.4538493345695076);

        //MARCADORES
        mMap.addMarker(new MarkerOptions()
                .position(mrk1station)
                .title(getString(R.string.marker_title_1station))
                .snippet(getString(R.string.marker_snippet_1station))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_mkr1station)));
//Mostrar ventana con la informacion de la actividad
        mMap.setOnMarkerClickListener(marker -> {
            marker.showInfoWindow(); // mostramos el título y snippet
            return true; // true si consumes el evento (para evitar zoom automático)
        });
        mMap.setOnInfoWindowClickListener(marker -> {
            // Al pulsar en la ventana del marcador, abrimos el FragmentDialog
            String titulo = marker.getTitle();
            String descripcion = marker.getSnippet();

            // TODO Crea y muestra tu DialogFragment
            Toast toast = Toast.makeText(getApplicationContext(), titulo +" "+ descripcion, Toast.LENGTH_SHORT);
            toast.show();
        });

        mMap.addMarker(new MarkerOptions()
                .position(mrk2station)
                .title(getString(R.string.marker_title_2station))
                .snippet(getString(R.string.marker_snippet_2station))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_mkr2station)));

        mMap.addMarker(new MarkerOptions()
                .position(mrk3station)
                .title(getString(R.string.marker_title_3station))
                .snippet(getString(R.string.marker_snippet_3station))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_mkr3station)));
        mMap.addMarker(new MarkerOptions()
                .position(mrk4station)
                .title(getString(R.string.marker_title_4station))
                .snippet(getString(R.string.marker_snippet_4station))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_mkr4station)));
        mMap.addMarker(new MarkerOptions()
                .position(mrk5station)
                .title(getString(R.string.marker_title_5station))
                .snippet(getString(R.string.marker_snippet_5station))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_mkr5station)));
        mMap.addMarker(new MarkerOptions()
                .position(mrk6station)
                .title(getString(R.string.marker_title_6station))
                .snippet(getString(R.string.marker_snippet_6station))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_mkr6station)));
        mMap.addMarker(new MarkerOptions()
                .position(mrk7station)
                .title(getString(R.string.marker_title_7station))
                .snippet(getString(R.string.marker_snippet_7station))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_mkr7station)));
        mMap.addMarker(new MarkerOptions()
                .position(mrk8station)
                .title(getString(R.string.marker_title_8station))
                .snippet(getString(R.string.marker_snippet_8station))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_mkr8station)));
        mMap.addMarker(new MarkerOptions()
                .position(mrk9station)
                .title(getString(R.string.marker_title_9station))
                .snippet(getString(R.string.marker_snippet_9station))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_mkr9station)));
        mMap.addMarker(new MarkerOptions()
                .position(mrk10station)
                .title(getString(R.string.marker_title_10station))
                .snippet(getString(R.string.marker_snippet_10station))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_mkr10station)));

        //Petición de permisos de ubicación al usuario mediante dialogo
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

        showMyLocationOnMap();

    }

        //Muestra la localización del usuario en el mapa y mueve la cámara esa ubicación
    private void showMyLocationOnMap() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 10));
                        }
                    });
        }
    }


}