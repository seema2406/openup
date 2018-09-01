package com.openup.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.openup.R;
import com.openup.adapters.ProfilePhotoAdaptor;
import com.openup.models.PhotoModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfilePhotoFragment extends Fragment {
    @BindView(R.id.photo_gridlayout)
    GridView photoGridlayout;
    ArrayList<PhotoModel> photoList = new ArrayList<>();

    public ProfilePhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView  = inflater.inflate(R.layout.fragment_profile_photo, container, false);
        ButterKnife.bind(this,rootView);

        prepareData();
        ProfilePhotoAdaptor profilePhotoAdaptor = new ProfilePhotoAdaptor( getContext(), photoList);
        photoGridlayout.setNumColumns(3);
        photoGridlayout.setAdapter(profilePhotoAdaptor);

        return rootView;
    }

    private void prepareData() {
        for (int i =0; i<12; i++){
            photoList.add(new PhotoModel(i));
        }
    }
}