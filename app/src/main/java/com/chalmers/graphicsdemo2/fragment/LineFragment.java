package com.chalmers.graphicsdemo2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chalmers.graphicsdemo2.R;
import com.chalmers.graphicsdemo2.view.LineView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 折线图
 */
public class LineFragment extends Fragment {

    @Bind(R.id.lv_line_view)
    public LineView lvLineView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line, container, false);
        ButterKnife.bind(this,view);

//        int data[] = new int[]{60,40,100,30,80,70,120,90,70,50};
        int data[] = new int[20];
        for(int i=0; i<data.length; i++){
            data[i] = (int)(Math.random()*100+20);
        }
        lvLineView.setData(data);

        return view;
    }

}
