package com.example.torontoparking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;

public class MapsFragment extends Fragment {

    boolean double_clicked = false;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @SuppressLint("PotentialBehaviorOverride")
        @Override
        public void onMapReady(GoogleMap googleMap) {
            int c = 0;
            ArrayList<ParkingLot> parkingLots = ReadCSV.readCSV(getResources().openRawResource(R.raw.parking));
            ClusterManager<MyItem> clusterManager;
            if (getContext() != null) {
                clusterManager = new ClusterManager<MyItem>(getContext(), googleMap);
                // Point the map's listeners at the listeners implemented by the cluster
                // manager.
                googleMap.setOnCameraIdleListener(clusterManager);

                // Set the camera to Toronto (using GIS)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.6532, -79.3832), 10.5f));
                //googleMap.setOnMarkerClickListener(clusterManager);
                for (ParkingLot p : parkingLots) {
                    if (!p.gis.equals("") && p.getLat() != null && p.getLong() != null) {
                        LatLng temp = new LatLng(p.getLong(), p.getLat());
                        //googleMap.addMarker(new MarkerOptions().position(temp).title(p.name));
                        MyItem offsetItem = new MyItem(p.getLong(), p.getLat(), p.name, "Click to learn more. ");
                        clusterManager.addItem(offsetItem);
                    }

                }

                clusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<MyItem>() {
                    @Override
                    public void onClusterItemInfoWindowClick(MyItem item) {
                        openInformationActivity(parkingLots, item);
                    }
                });

            }

        }
    };

    public void openInformationActivity(ArrayList<ParkingLot> parkingLots, MyItem item) {
        Intent i = new Intent(getActivity(), InformationActivity.class);
        Intent j = new Intent(getActivity(), IdleMapsFragment.class);
        for (ParkingLot p: parkingLots) {
            if (p.name.equals(item.getTitle())) {
                i.putExtra("assetNumber", p.assetNumber);
                i.putExtra("name", p.name);
                i.putExtra("parkingSpaces", p.parkingSpaces);
                i.putExtra("handicapSpaces", p.handicapSpaces);
                i.putExtra("lat", p.getLat());
                i.putExtra("long", p.getLong());
                i.putExtra("access", p.access);
            }
        }
        startActivity(i);
    }

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