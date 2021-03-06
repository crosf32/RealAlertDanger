package developpermania.crosf32.fr.realalertdanger;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.List;
import developpermania.crosf32.fr.realalertdanger.fragments.AccueilFragment;
import developpermania.crosf32.fr.realalertdanger.fragments.ConditionsFragment;
import developpermania.crosf32.fr.realalertdanger.fragments.ParametersFragment;
import developpermania.crosf32.fr.realalertdanger.fragments.ServicesFragment;
import developpermania.crosf32.fr.realalertdanger.fragments.ShareBugsFragment;
import developpermania.crosf32.fr.realalertdanger.listeners.MyLocationListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // implements : NavigationView pr la navigationDrawer

    //static car je ne peux pas acceder autrement avec les fragments
    public static MainActivity m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Une transaction que tu verras plus tard, ici elle me permet de ne pas atterir sur activity_main mais sur alert_main (je t'expliquerai)
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new AccueilFragment()).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Demande d'autorisations
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                10);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                10);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SEND_SMS},
                10);
        m = this;
    }

    @Override // Code généré automatiquement
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override // Code généré automatiquement
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override // Code généré automatiquement
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId(); // Pr les settings
        android.app.FragmentManager fm = getFragmentManager();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            fm.beginTransaction().replace(R.id.content_frame, new ParametersFragment()).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) { // Pr choisir l'activity avec la navigation drawer
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager fm = getFragmentManager();
        if (id == R.id.nav_home) {
            fm.beginTransaction().replace(R.id.content_frame, new AccueilFragment()).commit(); // les fameuses transaction ^^
        } else if (id == R.id.nav_services) {
            fm.beginTransaction().replace(R.id.content_frame, new ServicesFragment()).commit();
        } else if (id == R.id.nav_conditions) {
            fm.beginTransaction().replace(R.id.content_frame, new ConditionsFragment()).commit();
        } else if (id == R.id.nav_sharebugs) {
            fm.beginTransaction().replace(R.id.content_frame, new ShareBugsFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public boolean checkGpsPermission() { // Pr eviter les echec de perm (il faut check c'est obligatoire)
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        Log.d("Permission ", String.valueOf((res == PackageManager.PERMISSION_GRANTED)));
        return (res == PackageManager.PERMISSION_GRANTED);
    }
    private boolean checkSmsPermission() { // également
        String permission = "android.permission.SEND_SMS";
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        Log.i("Permission SMS ", String.valueOf(res ==  PackageManager.PERMISSION_GRANTED));
        return (res == PackageManager.PERMISSION_GRANTED);
    }
    //Pr envoyer l'SMS
    public void sendSTestMS(String phoneNumber, String message)
    {
        if(checkSmsPermission()) {
            // Aucune idée ce qu'est le PendingIntent mais il permet a fonction de fonctionner ^^ sans le pending ca marchait pas
            PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, pi, null);
            Toast.makeText(getApplicationContext(), "The message has been sent", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    10);
        }
    }
    // Pr envoyer en toast les notifs ^^ (toast = message qui apparait en bas)
    public void sendErrorNotif(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
    // pr recuperer le message a envoyer ( Vous pouvez changer les messages)
    public String getMessageToSend(Danger d) {
        String loc = getLocation();
        if(loc.equals("error")) {
            return "error";
        } else {
            if(d.equals(Danger.MEDICAL)) {
                //Toast.makeText(getApplicationContext(), "Bonjour, j'ai un problème médical, veuillez me rejoindre au plus vite en " + getLocation(), Toast.LENGTH_SHORT).show();
                return "Hello, i need a medical assistance. I'm in " + loc;
            } else {
                //Toast.makeText(getApplicationContext(), "Bonjour, je suis en danger, veuillez me rejoindre au plus vite en " + getLocation(), Toast.LENGTH_SHORT).show();
                return "Hello, i need a police assistance. I'm in " + loc;
            }
        }
    }

    private String getLocation() {
        try {
            MyLocationListener loc = new MyLocationListener(m, m);
            return getAddress(loc.getLongitude(), loc.getLatitude()) + "(long:" + loc.getLongitude() + ", lat:" + loc.getLatitude() + ", alt:" + loc.getAltitude() + ")";
        } catch(Exception e) {
            return "error";
        }
    }

    // Geolocalisation : reverse geocoding
    private String getAddress(double longitude, double latitude) {
        String add1 = "";
        String add2 = "";
        String pays = "";
        try {
            Geocoder geo = new Geocoder(this);
            List<Address> addresses = null;
            addresses = geo.getFromLocation(latitude, longitude, 2);
            add1 = addresses.get(0).getAddressLine(0);
            add2 = addresses.get(0).getAddressLine(1);
            pays = addresses.get(0).getCountryName();
        } catch(Exception e) {
           // Log.i("Information", "Geocoding impossible" + e.getMessage());
            return "";
        }
        if(add1 == "" || add2 == "" || pays == "") {
            return "";
        } else {
            return "(" + pays + " " + add2 + " " + add1 + ")";
        }
    } // Enum = liste (ici medical ou danger, c'est plus propre ^^)
    public enum Danger {
        MEDICAL,
        POLICE
    }
}
