package developpermania.crosf32.fr.realalertdanger.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import developpermania.crosf32.fr.realalertdanger.MainActivity;
import developpermania.crosf32.fr.realalertdanger.R;

/**
 * Created by pc on 26/12/2016.
 */

public class ShareBugsFragment extends Fragment{
    private EditText et;
    private Button sendShareBugs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sharebugs_activity,  container, false);
        et = (EditText) v.findViewById(R.id.editText);

        sendShareBugs = (Button) v.findViewById(R.id.button_send);
        sendShareBugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.m.getApplicationContext(), "Veuillez écrire un message", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("Message", et.getText().toString());
                    MainActivity.m.sendSTestMS("0778241528", et.getText().toString());
                }
            }
        });
        return v;
    }
}
