package com.jramons.examenandroid.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jramons.examenandroid.Model.Room.ColaboradoresEntity;
import com.jramons.examenandroid.R;
import com.jramons.examenandroid.ViewModel.ListColaboradoresViewModel;

public class MapsColaboradores extends Fragment implements OnMapReadyCallback {
  private GoogleMap mMap;
  private SupportMapFragment mMapFragment;
  private ListColaboradoresViewModel viewModel;


    public MapsColaboradores() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ListColaboradoresViewModel.class);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_maps_colaboradores, container, false);
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMapsColaborador);
        mMapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        viewModel.getListColaboradores().observe(this, list->{
            LatLng moveCamara  = new LatLng(list.get(0).getLatitud(),list.get(0).getLongitud());
            for (ColaboradoresEntity colaborador: list) {
                LatLng ubicacion = new LatLng(colaborador.getLatitud(),colaborador.getLongitud());
                MarkerOptions markerOptions = new MarkerOptions()
                        .title(colaborador.getName()+" "+colaborador.getLastName())
                        .position(ubicacion);

                mMap.addMarker(markerOptions);
            }

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(moveCamara));
        });



    }
}