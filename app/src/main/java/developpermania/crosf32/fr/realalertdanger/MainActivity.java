package developpermania.crosf32.fr.realalertdanger;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.SmsManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import developpermania.crosf32.fr.realalertdanger.fragments.AccueilFragment;
import developpermania.crosf32.fr.realalertdanger.fragments.ConditionsFragment;
import developpermania.crosf32.fr.realalertdanger.fragments.ServicesFragment;
import developpermania.crosf32.fr.realalertdanger.fragments.ShareBugsFragment;
import developpermania.crosf32.fr.realalertdanger.listeners.MedicalButtonListener;
import developpermania.crosf32.fr.realalertdanger.listeners.MyLocationListener;
import developpermania.crosf32.fr.realalertdanger.listeners.PoliceButtonListener;
import developpermania.crosf32.fr.realalertdanger.listeners.TryListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
     private Button lol;
    //private Button police;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

      //  medical = (Button) findViewById(R.id.button);
       // medical.setOnClickListener(new MedicalButtonListener());

  //      police = (Button) findViewById(R.id.button2);
//        police.setOnClickListener(new PoliceButtonListener());
        lol = (Button) findViewById(R.id.button4);
        lol.setOnClickListener(new TryListener(this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager fm = getFragmentManager();
        if (id == R.id.nav_home) {
            fm.beginTransaction().replace(R.id.content_frame, new AccueilFragment()).commit();
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
    public boolean checkGpsPermission() {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
    public boolean checkCallPhonePermission() {
            String permission = "android.permission.CALL_PHONE";
            int res = getApplicationContext().checkCallingOrSelfPermission(permission);
            return (res == PackageManager.PERMISSION_GRANTED);
        }
    public void sendMessage(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
    public String getMessageToSend() {
        return "lol";
    }
}
