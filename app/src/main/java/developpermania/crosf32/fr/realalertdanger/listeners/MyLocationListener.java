package developpermania.crosf32.fr.realalertdanger.listeners;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import developpermania.crosf32.fr.realalertdanger.MainActivity;

public class MyLocationListener extends Service implements LocationListener{
    private LocationListener listener;
    private LocationManager locationManager;
    private Context context;
    private boolean isGpsEnabled;
    private boolean isNetworkEnabled;
    private boolean canGetLocation = true;
    Location location;

    double latitude;
    double longitude;
    double altitude;

    private MainActivity m;
    public MyLocationListener(Context context, MainActivity m) {
        this.context = context;
        this.m = m;
        getLocation();
    }
    public Location getLocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if(!isGpsEnabled && !isNetworkEnabled) {

            } else {
                this.canGetLocation = true;
                if(isNetworkEnabled) {
                    if(m.checkGpsPermission()) {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, (1000*60*1), 10, this);
                    }
                }
                if(locationManager != null) {
                    if(m.checkGpsPermission()) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                    if(location != null) {
                        latitude = location.getLatitude();
                        altitude = location.getAltitude();
                        longitude = location.getLongitude();
                    }
                }
            }
            if(isGpsEnabled) {
                if (location == null) {
                    if(m.checkGpsPermission()) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (1000 * 60 * 1), 10, this);
                    }
                    if(locationManager != null) {
                        if(m.checkGpsPermission()) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                        if(location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            altitude = location.getAltitude();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }
    @Override
    public void onLocationChanged(Location location) {
        if(location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            altitude = location.getAltitude();
        }
    }
    @Override
    public void onProviderEnabled(String provider) {

    }
    @Override
    public void onProviderDisabled(String provider) {

    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public double getLatitude() {
        if(locationManager != null && location != null) {
            latitude = location.getLatitude();
        }
        return latitude;
    }
    public double getAltitude() {
        if(locationManager != null) {
            latitude = location.getAltitude();
        }
        return altitude;
    }
    public double getLongitude() {
        if(locationManager != null && location != null) {
            latitude = location.getLongitude();
        }
        return longitude;
    }
    public boolean canGetLocation() {
        return this.canGetLocation;
    }
}
