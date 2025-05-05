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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, StationCallback {
    //LUGARES
    private final LatLng mrk1station = new LatLng(36.80934308883409, -2.5832089509371428);
    private final LatLng mrk2station = new LatLng(37.6322, -3.8214);
    private final LatLng mrk3station = new LatLng(37.90481944, -4.77816389);
    private final LatLng mrk4station = new LatLng(37.345707, -5.981003);
    private final LatLng mrk5station = new LatLng(37.2771751, -6.9412007);
    private final LatLng mrk6station = new LatLng(36.5299, -6.2994);
    private final LatLng mrk7station = new LatLng(36.593506689594, -4.5293087804356);
    private final LatLng mrk8station = new LatLng(36.6928291, -4.4477315);
    private final LatLng mrk9station = new LatLng(37.183862, -3.602518);
    private final LatLng mrk10station = new LatLng(36.83837697453805, -2.4538493345695076);

    //MARCADORES
    private final LatLng[] stationPositions = new LatLng[]{
            mrk1station,
            mrk2station,
            mrk3station,
            mrk4station,
            mrk5station,
            mrk6station,
            mrk7station,
            mrk8station,
            mrk9station,
            mrk10station
    };
    private final int[] stationIcons = new int[]{
            R.drawable.img_mkr1station,
            R.drawable.img_mkr2station,
            R.drawable.img_mkr3station,
            R.drawable.img_mkr4station,
            R.drawable.img_mkr5station,
            R.drawable.img_mkr6station,
            R.drawable.img_mkr7station,
            R.drawable.img_mkr8station,
            R.drawable.img_mkr9station,
            R.drawable.img_mkr10station
    };
    private final int[] stationImages = new int[]{
            R.drawable.img_station1,
            R.drawable.img_station2,
            R.drawable.img_station3,
            R.drawable.img_station4,
            R.drawable.img_station5,
            R.drawable.img_station6,
            R.drawable.img_station7,
            R.drawable.img_station8,
            R.drawable.img_station9,
            R.drawable.img_station10
    };
    private String[] stationNames;
    private String[] stationSnippets;
    private String[] stationTitles;
    private String[] stationDescriptions;
    private String[] stationPasswords;

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

        stationNames = getResources().getStringArray(R.array.marker_name_station);
        stationSnippets = getResources().getStringArray(R.array.marker_snippet_station);
        stationTitles = getResources().getStringArray(R.array.dialog_title_station);
        stationDescriptions = getResources().getStringArray(R.array.dialog_description_station);
        stationPasswords = getResources().getStringArray(R.array.dialog_password_station);


        for (int i = 0; i < stationNames.length; i++) {
            Station station = new Station(
                    i,
                    stationPositions[i],
                    stationNames[i],
                    stationSnippets[i],
                    stationTitles[i],
                    stationDescriptions[i],
                    stationPasswords[i],
                    stationIcons[i],
                    stationImages[i]
            );

            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(station.getPosition())
                    .title(station.getName())
                    .snippet(station.getSnippet())
                    .icon(BitmapDescriptorFactory.fromResource(station.getIcon())));

            if (marker == null) {
                throw new NullPointerException();
            }
            marker.setTag(station);
        }

        //Mostrar ventana con la informacion breve de la actividad
        mMap.setOnMarkerClickListener(marker -> {
            marker.showInfoWindow(); // mostramos el título y snippet
            return true; // true si consumes el evento (para evitar zoom automático)
        });

        // Al pulsar en la ventana del marcador, abrimos el FragmentDialog
        mMap.setOnInfoWindowClickListener(marker -> {
            StationFragment dialog = new StationFragment((Station) marker.getTag(), this::onStationCompleted);
            dialog.show(this.getSupportFragmentManager(),"");
        });

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

    @Override
    public void onStationCompleted(Station station, String password) {
        if (station.password.equals(password)) {
            String nextStation = stationNames[station.getId() + 1];
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.correct_password_txt) + nextStation, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.wrong_password_txt, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}