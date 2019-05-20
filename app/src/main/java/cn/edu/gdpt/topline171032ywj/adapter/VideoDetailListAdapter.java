package cn.edu.gdpt.topline171032ywj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdpt.topline171032ywj.Bean.VideoDetailBean;
import cn.edu.gdpt.topline171032ywj.R;

public class VideoDetailListAdapter extends BaseAdapter {
    private Context mContext;
    private List<VideoDetailBean> vdb1;

    public VideoDetailListAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<VideoDetailBean> vdb1){
        this.vdb1=vdb1;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return vdb1==null?0: vdb1.size();
    }

    @Override
    public Object getItem(int position) {
        return vdb1==null?null:vdb1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(
                    R.layout.video_detail_item,null
            );
            vh.title=(TextView)convertView.findViewById(R.id.tv_video_name);
            vh.iv_icon=(ImageView)convertView.findViewById(R.id.iv_icon);
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder)convertView.getTag();
        }
        final VideoDetailBean bean= (VideoDetailBean) getItem(position);
        if (bean!=null){
            vh.title.setText(bean.getVideo_name());
            vh.iv_icon.setImageResource(R.drawable.iv_video_icon);
        }
        return convertView;
    }
    class ViewHolder{
        public TextView title;
        public ImageView iv_icon;
    }
}
