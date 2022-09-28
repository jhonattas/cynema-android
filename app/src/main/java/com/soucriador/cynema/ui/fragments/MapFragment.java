package br.com.soucriador.cynema.cynema.ui.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import br.com.patrocine.cynema.R;

public class MapFragment extends Fragment {

    View view;
    Button openMaps;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        onCreateComponents();
        return view;
    }

    void onCreateComponents(){
        openMaps = view.findViewById(R.id.openMaps);

        openMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sLocation = getResources().getString(R.string.geoLocation);
                Uri gmmIntentUri = Uri.parse(sLocation);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            }
        });
    }

}
