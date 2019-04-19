package cn.edu.gdpt.topline171032ywj.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdpt.topline171032ywj.Bean.NewsBean;
import cn.edu.gdpt.topline171032ywj.Fragment.AdBannerFragment;

public class AdBannerAdapter extends FragmentStatePagerAdapter {
    private List<NewsBean> ab1;
    public AdBannerAdapter(FragmentManager fm){
        super(fm);
        ab1=new ArrayList<NewsBean>();
    }
    public void setData(List<NewsBean> ab1){
        this.ab1=ab1;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int index) {
        Bundle args=new Bundle();
        if (ab1.size()>0)
            args.putSerializable("ad",ab1.get(index%ab1.size()));
        return AdBannerFragment.newInstance(args);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
