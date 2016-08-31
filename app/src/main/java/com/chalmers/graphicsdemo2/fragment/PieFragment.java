package com.chalmers.graphicsdemo2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chalmers.graphicsdemo2.R;
import com.chalmers.graphicsdemo2.view.PieView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 饼状图
 */
public class PieFragment extends Fragment {

    @Bind(R.id.pv_pie_view)
    public PieView pvPieView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie, container, false);
        ButterKnife.bind(this,view);

//        int[] data = new int[]{30,40,50,75,105,180,220,37,46,100};
        int data[] = new int[12];
        for(int i=0; i<data.length; i++){
            data[i] = (int)(Math.random()*100+20);
        }
        int[] degrees = dataToDegrees(data);

        pvPieView.setDegrees(degrees);
        pvPieView.start();
        return view;
    }

    private int[] dataToDegrees(int[] data) {
        int totle = 0;
        for(int i : data){
            totle += i;
        }
        int degrees[] = new int[data.length];
        int num = 0;
        for(int i=0; i<data.length-1; i++){
            degrees[i] = (int)(data[i]*1.0/totle*360);
            num += degrees[i];
        }
        degrees[data.length-1] = 360 - num;
        return degrees;
    }
}
