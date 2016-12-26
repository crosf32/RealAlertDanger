package developpermania.crosf32.fr.realalertdanger.listeners;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import developpermania.crosf32.fr.realalertdanger.MainActivity;

public class MedicalButtonListener implements View.OnClickListener{
    MainActivity m;
    public MedicalButtonListener(MainActivity m) {
        this.m = m;
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0778241528"));
        m.sendMessage("0778241528", "lol");
        if(m.checkCallPhonePermission()) {
            m.startActivity(intent);
        }
    }
}
