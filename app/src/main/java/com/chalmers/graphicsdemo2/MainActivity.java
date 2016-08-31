package com.chalmers.graphicsdemo2;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chalmers.graphicsdemo2.fragment.ArcFragment;
import com.chalmers.graphicsdemo2.fragment.BarChartFragment;
import com.chalmers.graphicsdemo2.fragment.LineFragment;
import com.chalmers.graphicsdemo2.fragment.PieFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tl_main_tab)
    public TabLayout tlMainTab;
    @Bind(R.id.vp_main_content)
    public ViewPager vpMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new LineFragment());
        fragments.add(new PieFragment());
        fragments.add(new ArcFragment());
        fragments.add(new BarChartFragment());

        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(),
                fragments);
        vpMainContent.setAdapter(adapter);
        tlMainTab.setupWithViewPager(vpMainContent);
    }
}
