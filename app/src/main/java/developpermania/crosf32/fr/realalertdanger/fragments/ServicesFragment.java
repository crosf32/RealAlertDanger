package developpermania.crosf32.fr.realalertdanger.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import developpermania.crosf32.fr.realalertdanger.MainActivity;
import developpermania.crosf32.fr.realalertdanger.R;

public class ServicesFragment extends Fragment{
    private Button noAssistanceButton;
    private Button medicalButton;
    private Button policeButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.services_activity, container, false);
        noAssistanceButton = (Button) v.findViewById(R.id.button3);
        noAssistanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.m.finish();
            }
        });
        medicalButton = (Button)v.findViewById(R.id.button);
        medicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroToCall;
                SharedPreferences sp = MainActivity.m.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                if(sp.getString("none", "") != "") {
                    numeroToCall = sp.getString("none", "");
                } else {
                    numeroToCall = "0778241528";
                }
                MainActivity.m.sendSTestMS(numeroToCall, MainActivity.m.getMessageToSend(MainActivity.Danger.MEDICAL));
            }
        }) ;
        policeButton = (Button) v.findViewById(R.id.button2);
        policeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroToCall;
                SharedPreferences sp = MainActivity.m.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                if(sp.getString("ntwo", "") != "") {
                    numeroToCall = sp.getString("ntwo", "");
                } else {
                    numeroToCall = "0778241528";
                }
                MainActivity.m.sendSTestMS(numeroToCall, MainActivity.m.getMessageToSend(MainActivity.Danger.POLICE));
            }
        });
        return v;
    }
}
