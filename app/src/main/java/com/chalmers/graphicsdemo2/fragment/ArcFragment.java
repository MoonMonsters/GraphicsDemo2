package com.chalmers.graphicsdemo2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chalmers.graphicsdemo2.R;
import com.chalmers.graphicsdemo2.view.ArcView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 弧线图
 */
public class ArcFragment extends Fragment {

    @Bind(R.id.av_arc_view)
    ArcView avArcView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arc, container, false);
        ButterKnife.bind(this,view);

        int degree = (int)(Math.random() * 300);

        avArcView.setDegree(degree);

        return view;
    }

}
