package com.example.torontoparking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            int c = 0;
            ArrayList<ParkingLot> parkingLots = ReadCSV.readCSV(getResources().openRawResource(R.raw.parking));
            for (ParkingLot p: parkingLots) {
                if (!p.gis.equals("") && p.getLat() != null && p.getLong() != null) {
                    LatLng temp = new LatLng(p.getLong(), p.getLat());
                    googleMap.addMarker(new MarkerOptions().position(temp).title(p.name));
                    if (c == 0) {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLng(temp));
                        c += 1;
                    }
                }
            }
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    Intent i = new Intent(getActivity(), InformationActivity.class);
                    for (ParkingLot p: parkingLots) {
                        if (p.name.equals(marker.getTitle())) {
                            i.putExtra("assetNumber", p.assetNumber);
                            i.putExtra("name", p.name);
                            i.putExtra("parkingSpaces", p.parkingSpaces);
                            i.putExtra("handicapSpaces", p.handicapSpaces);
                            i.putExtra("gis", p.gis);
                            i.putExtra("access", p.access);
                        }
                    }
                    startActivity(i);
                    return false;
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}