package developpermania.crosf32.fr.realalertdanger.listeners;

import android.view.View;
import android.widget.Toast;

import developpermania.crosf32.fr.realalertdanger.MainActivity;

public class TryListener implements View.OnClickListener{
    MainActivity m;
    public TryListener(MainActivity m) {
        this.m = m;
    }
    @Override
    public void onClick(View v) {
        MyLocationListener loc = new MyLocationListener(m, m);
        if(m.checkGpsPermission()) {
            if(loc.canGetLocation()) {
                Toast.makeText(m, "ok", Toast.LENGTH_SHORT);
                Toast.makeText(m, "Altitude " + loc.getAltitude() + "\nLatitude " + loc.getLatitude() + "\nLongitude " + loc.getLongitude(), Toast.LENGTH_LONG);
            } else {
                Toast.makeText(m, "Nope !!", Toast.LENGTH_LONG);
            }
        }
    }
}
