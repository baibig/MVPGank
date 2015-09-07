package com.pierce.mvpgank.ui.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pierce.mvpgank.R;
import com.pierce.mvpgank.model.entity.Ganhuo;
import com.pierce.mvpgank.widget.TouchImageView;
import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends BaseFragment {

    @Bind(R.id.img)
    TouchImageView img;

    private String data;
    private OnTouchListener listener;

    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_image, container, false);
        ButterKnife.bind(this,v);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTouch();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=getArguments();
        data=bundle.getString("meizhi");
        Picasso.with(getContext())
                .load(data)
                .into(img);
    }

    public void setOnTouchListener(OnTouchListener listener){
        this.listener=listener;
    }

    public interface OnTouchListener{
        void onTouch();
    }
}
