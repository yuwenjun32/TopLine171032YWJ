package cn.edu.gdpt.topline171032ywj.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdpt.topline171032ywj.Bean.PythonBean;
import cn.edu.gdpt.topline171032ywj.R;

public class PythonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<PythonBean> pb1;
    public void setData(List<PythonBean> pd1){
        this.pb1=pd1;
        notifyDataSetChanged();
    }
    @NonNull
    @Override//创建视图
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.python_list_item,viewGroup,false
        );
        PythonViewHolder viewHolder=new PythonViewHolder(view);
        return viewHolder;
    }

    @Override//绑定数据
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        final PythonBean bean=pb1.get(i);
        ((PythonViewHolder)holder).tv_address.setText(bean.getAddress());
        ((PythonViewHolder)holder).tv_content.setText(bean.getContent());
    }

    @Override//数据数量
    public int getItemCount() {
        return pb1==null?0:pb1.size();
    }

    public class PythonViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_address,tv_content;
        private ImageView im_img;
        public PythonViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_address=(TextView)itemView.findViewById(R.id.tv_address);
            tv_content=(TextView)itemView.findViewById(R.id.tv_content);
            im_img=(ImageView)itemView.findViewById(R.id.iv_fire);
        }
    }
}
