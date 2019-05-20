package cn.edu.gdpt.topline171032ywj.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import cn.edu.gdpt.topline171032ywj.Bean.VideoBean;
import cn.edu.gdpt.topline171032ywj.R;
import cn.edu.gdpt.topline171032ywj.activity.VideoDetailActivity;

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
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_list_item,
                viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv_img;
        public ViewHolder(View itemView){
            super(itemView);
            iv_img=(ImageView)itemView.findViewById(R.id.iv_img_rpund);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final VideoBean bean=videoList.get(i);
        Glide
                .with(context)
                .load(bean.getImg())
                .error(R.mipmap.ic_launcher)
                .into(((ViewHolder)viewHolder).iv_img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, VideoDetailActivity.class);
                intent.putExtra("intro",bean.getIntro());
                intent.putExtra("VideoDetailList",(Serializable)bean.getVideoDetailList());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList==null?0:videoList.size();
    }
}
