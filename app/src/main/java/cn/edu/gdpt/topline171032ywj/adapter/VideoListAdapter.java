package cn.edu.gdpt.topline171032ywj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import cn.edu.gdpt.topline171032ywj.Bean.VideoBean;

public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<VideoBean> videoList;
    private Context context;

    public VideoListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<VideoBean> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return videoList==null?0:videoList.size();
    }
}
