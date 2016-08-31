package com.chalmers.graphicsdemo2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chalmers.graphicsdemo2.R;
import com.chalmers.graphicsdemo2.view.BarChartView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 柱状图
 */
public class BarChartFragment extends Fragment {
    @Bind(R.id.bcv_barchar_view)
    BarChartView bcvBarCharView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar_chart, container, false);

        ButterKnife.bind(this,view);

        int data[] = new int[10];
        for(int i=0; i<data.length; i++){
            data[i] = (int)(Math.random()*100+10);
        }
        bcvBarCharView.setData(data);

        return view;
    }
}