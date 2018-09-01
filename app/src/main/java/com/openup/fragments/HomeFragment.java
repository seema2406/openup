package com.openup.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openup.R;
import com.openup.utils.IconMarker;

import java.util.ArrayList;

public class HomeFragment extends Fragment /*implements View.OnClickListener*/ {

    /*private static final LatLng CENTER = new LatLng(36.778261, -119.417932);
    private static final int ZOOM = 6;

    private static final LatLng[] LOCATIONS = new LatLng[]{
            new LatLng(37.71515248, -120.7508706),
            new LatLng(36.37386931, -118.47269228),
            new LatLng(36.84395714, -121.66135364),
            new LatLng(37.44175323, -119.08338699),
            new LatLng(35.36812138, -120.75986352)
    };

    private static ImageLoader imageLoader;

    private MarkerManager<IconMarker> iconMarkerManager;

    private GoogleMap map;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        rootView.findViewById(R.id.button_icon_markers).setOnClickListener(this);

        final SupportMapFragment mapFragment = SupportMapFragment.newInstance();

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.map_container, mapFragment);
        transaction.commit();

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                iconMarkerManager = new MarkerManager<>(googleMap);

                iconMarkerManager.setOnMarkerClickListener(new DisableClick<IconMarker>());

                map = googleMap;
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(CENTER, ZOOM));
            }
        });

        // Keep a static image loader instance.
        if (imageLoader == null) {

            final RequestQueue queue = Volley.newRequestQueue(this);
            imageLoader = new ImageLoader(queue, new NoImageCache());
        }
        return rootView;
    }
    @Override
    public void onClick(View v) {

        iconMarkerManager.clear();

        switch (v.getId()) {

            case R.id.button_icon_markers:
                map.setOnMarkerClickListener(iconMarkerManager);
                iconMarkerManager.addMarkers(createIconMarkers());
                break;

        }
    }

    *//**
     * Icons courtesy of https://mapicons.mapsmarker.com/
     *//*
    private ArrayList<IconMarker> createIconMarkers() {

        final ArrayList<IconMarker> iconMarkers = new ArrayList<>(LOCATIONS.length);

        iconMarkers.add(new IconMarker(LOCATIONS[0], R.mipmap.account_icon, R.mipmap.bash_icon));
        iconMarkers.add(new IconMarker(LOCATIONS[1], R.mipmap.account_icon, R.mipmap.bash_icon));
        iconMarkers.add(new IconMarker(LOCATIONS[2], R.mipmap.account_icon, R.mipmap.bash_icon));
        iconMarkers.add(new IconMarker(LOCATIONS[3], R.mipmap.account_icon, R.mipmap.bash_icon));
        iconMarkers.add(new IconMarker(LOCATIONS[4], R.mipmap.account_icon, R.mipmap.bash_icon));

        return iconMarkers;
    }


    *//**
     * Not interested in caching images.
     *//*
    private static class NoImageCache implements ImageLoader.ImageCache {

        @Override
        public Bitmap getBitmap(String url) {
            return null;
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            // Do nothing
        }
    }

    *//**
     * Marker click listener that always returns true.
     *//*
    private static class DisableClick<T extends CustomMarker>
            implements MarkerManager.OnMarkerClickListener<T> {

        @Override
        public boolean onMarkerClick(T marker) {
            return true;
        }
    }*/

}