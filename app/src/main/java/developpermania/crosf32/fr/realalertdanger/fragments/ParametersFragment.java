package developpermania.crosf32.fr.realalertdanger.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
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
 * Created by pc on 02/01/2017.
 */

public class ParametersFragment extends Fragment{
    EditText eOne, eTwo;
    Button save;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.parameters_activity, container, false);
        eOne = (EditText) v.findViewById(R.id.editText2);
        eTwo = (EditText) v.findViewById(R.id.editText3);
        save = (Button) v.findViewById(R.id.button4);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nOne = eOne.getText().toString();
                String nTwo = eTwo.getText().toString();
                if(nOne == "" && nTwo == "") {
                    return;
                } else {
                    if(nOne != "") {
                        saveNOne(nOne);
                    } else {
                        saveNTwo(nTwo);
                    }
                }
            }
        });
        return v;
    }
    private void saveNOne(String n) {
        SharedPreferences sp = MainActivity.m.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("none", n);
        editor.apply();
        Toast.makeText(MainActivity.m.getApplicationContext(), "Informations enregistrées", Toast.LENGTH_LONG).show();
    }
    private void saveNTwo(String n) {
        SharedPreferences sp = MainActivity.m.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ntwo", n);
        editor.apply();
        Toast.makeText(MainActivity.m.getApplicationContext(), "Informations enregistrées", Toast.LENGTH_LONG).show();
    }
}
