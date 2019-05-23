package cn.edu.gdpt.topline171032ywj.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.edu.gdpt.topline171032ywj.Bean.VideoDetailBean;
import cn.edu.gdpt.topline171032ywj.R;
import cn.edu.gdpt.topline171032ywj.adapter.VideoDetailListAdapter;
import cn.edu.gdpt.topline171032ywj.adapter.VideoDetailPagerAdapter;

public class VideoDetailActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LayoutInflater minflater;
    private List<String> mTitleList=new ArrayList<>();//页卡标题集合
    private View view1,view2;                           //页卡视图
    private List<View> mViewList=new ArrayList<>();     //页卡视图集合
    private String intro;//视频简介
    private List<VideoDetailBean> videoList;            //视频列表
    private SurfaceView surfaceView;
    private MediaPlayer player;
    private SeekBar skbProgress;//进度条
    private ProgressBar bufferProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        intro=getIntent().getStringExtra("intro");
        videoList=(List<VideoDetailBean>)getIntent().getSerializableExtra("VideoDetailList");
        initView();
        initViewPager();
        VideoPlay();
    }

    private void VideoPlay() {
        player=new MediaPlayer();
        bufferProgressBar.setSystemUiVisibility(View.VISIBLE);
        try {
            player.setDataSource("http://61.142.174.202:8080/topline/video/v0.3gp");
            SurfaceHolder holder=surfaceView.getHolder();
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    player.setDisplay(holder);
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    bufferProgressBar.setVisibility(View.GONE);
                    player.start();//准备好后开始播放
                    skbProgress.setMax(player.getDuration());//设置最大值
                    Timer timer=new Timer();
                    TimerTask task=new TimerTask() {
                        @Override
                        public void run() {
                            skbProgress.setProgress(player.getCurrentPosition());
                        }
                    };
                    timer.schedule(task,0,100);//0秒后执行，每隔100MS执行一次
                }
            });
            skbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    player.seekTo(skbProgress.getProgress());
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initViewPager() {
        mTitleList.add("视频简介");
        mTitleList.add("视频目录");
        minflater=LayoutInflater.from(this);
        view1=minflater.inflate(R.layout.video_detail_viewpager1,null);
        TextView tv_intro=(TextView)view1.findViewById(R.id.tv_intro);
        tv_intro.setText(intro);
        view2=minflater.inflate(R.layout.video_detail_viewpager2,null);
        ListView lv_list=(ListView)view2.findViewById(R.id.lv_list);
        VideoDetailListAdapter videoDetailListAdapter=new VideoDetailListAdapter(VideoDetailActivity.this);
        videoDetailListAdapter.setData(videoList);
        lv_list.setAdapter(videoDetailListAdapter);
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
        surfaceView=(SurfaceView)findViewById(R.id.playerSurfaceView);
        skbProgress=(SeekBar)findViewById(R.id.skbProgress);
        bufferProgressBar=(ProgressBar)findViewById(R.id.bufferProgressBar);
    }
}
