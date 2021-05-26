package br.edu.ceunsp.gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textViewLat;
    TextView textViewLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewLat = findViewById(R.id.textViewLat);
        textViewLong = findViewById(R.id.textViewLong);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Aprovado!!!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Reprovado!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void onClick(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {

                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        Double latPoint = location.getLatitude();
                        Double lngPoint = location.getLongitude();

                        textViewLat.setText(latPoint.toString());
                        textViewLong.setText(lngPoint.toString());
                    }

                    public void onProviderEnabled(String provider) { }

                    public void onProviderDisabled(String provider) { }
                };
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }


    }
}