package developpermania.crosf32.fr.realalertdanger.listeners;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import developpermania.crosf32.fr.realalertdanger.MainActivity;

public class PoliceButtonListener implements View.OnClickListener{
        MainActivity m;
        public PoliceButtonListener(MainActivity m) {
            this.m = m;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0778241528"));
            if(m.checkCallPhonePermission()) {
                m.startActivity(intent);
            }
        }
    }
