package developpermania.crosf32.fr.realalertdanger.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import developpermania.crosf32.fr.realalertdanger.R;

/**
 * Created by pc on 26/12/2016.
 */

public class ServicesFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.services_activity, container, false);
        return v;
    }
}
