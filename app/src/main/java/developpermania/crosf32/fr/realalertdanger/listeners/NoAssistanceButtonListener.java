package developpermania.crosf32.fr.realalertdanger.listeners;

import android.view.View;

import developpermania.crosf32.fr.realalertdanger.MainActivity;


public class NoAssistanceButtonListener implements View.OnClickListener{
    MainActivity m;
    public NoAssistanceButtonListener(MainActivity m) {
        this.m = m;
    }
    @Override
    public void onClick(View v) {
        m.finish();
    }
}
