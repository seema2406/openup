package com.openup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.openup.R;
import com.openup.fragments.ProfilePhotoFragment;
import com.openup.models.PhotoModel;

import java.util.ArrayList;

public class ProfilePhotoAdaptor extends BaseAdapter {
    private Context context;
    private ArrayList<PhotoModel> photoList;
    public ProfilePhotoAdaptor(Context context, ArrayList<PhotoModel> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public Object getItem(int position) {
        return photoList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate( R.layout.item_image_layout , null);
        ImageView image = rootView.findViewById(R.id.item_image);
        image.setImageResource(R.mipmap.user_image);
        return rootView;
    }
}
