package cn.edu.gdpt.topline171032ywj.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdpt.topline171032ywj.R;
import cn.edu.gdpt.topline171032ywj.adapter.VideoDetailPagerAdapter;

public class VideoDetailActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LayoutInflater minflater;
    private List<String> mTitleList=new ArrayList<>();//页卡标题集合
    private View view1,view2;                           //页卡视图
    private List<View> mViewList=new ArrayList<>();     //页卡视图集合
    private String intro;//视频简介
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        intro=getIntent().getStringExtra("intro");
        initView();
        initViewPager();
    }

    private void initViewPager() {
        mTitleList.add("视频简介");
        mTitleList.add("视频目录");
        minflater=LayoutInflater.from(this);
        view1=minflater.inflate(R.layout.video_detail_viewpager1,null);
        TextView tv_intro=(TextView)view1.findViewById(R.id.tv_intro);
        tv_intro.setText(intro);
        view2=minflater.inflate(R.layout.video_detail_viewpager2,null);
        mViewList.add(view1);
        mViewList.add(view2);
        VideoDetailPagerAdapter mAdapter=new VideoDetailPagerAdapter(mViewList,mTitleList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mViewPager=(ViewPager)findViewById(R.id.vp_view);
        mTabLayout=(TabLayout)findViewById(R.id.tabs);
    }
}
