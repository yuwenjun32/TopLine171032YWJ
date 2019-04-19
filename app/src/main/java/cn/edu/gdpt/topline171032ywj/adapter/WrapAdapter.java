package cn.edu.gdpt.topline171032ywj.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class WrapAdapter<T extends RecyclerView.Adapter> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final T mRealAdapter;
    private static final int BASE_HEADER_VIEW_TYPE=-1<<10;
    private ArrayList<FixedViewInfo> mHeaderViewInfos=new ArrayList();
    public class FixedViewInfo{
        public View view;
        public int viewType;
    }
    public WrapAdapter(T adapter) {
        super();
        this.mRealAdapter = adapter;
    }
    public void addHeaderView(View view){
        if (null==view){
            throw new IllegalArgumentException("the view to add must not be null!");
        }
        final FixedViewInfo info=new FixedViewInfo();
        info.view=view;
        info.viewType=BASE_HEADER_VIEW_TYPE+mHeaderViewInfos.size();
        mHeaderViewInfos.add(info);
      //  Log.i("aaa",String.valueOf(mHeaderViewInfos.size()));
        notifyDataSetChanged();
    }
    private boolean isHeader(int viewType){
        return viewType>=BASE_HEADER_VIEW_TYPE&&viewType<(BASE_HEADER_VIEW_TYPE+mHeaderViewInfos.size());
    }
    private boolean isHeaderPosition(int position){
        return position<mHeaderViewInfos.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (isHeader(viewType)){
            int whicHeader=Math.abs(viewType-BASE_HEADER_VIEW_TYPE);
            View headerView=mHeaderViewInfos.get(whicHeader).view;
            return new RecyclerView.ViewHolder(headerView) {};
        }else {
            return mRealAdapter.onCreateViewHolder(viewGroup,viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int poaition) {
        if (poaition<mHeaderViewInfos.size()){
        }else if (poaition<mHeaderViewInfos.size()+mRealAdapter.getItemCount()){
            mRealAdapter.onBindViewHolder(viewHolder,poaition-mHeaderViewInfos.size());
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderViewInfos.size()+mRealAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderPosition(position)){
            return mHeaderViewInfos.get(position).viewType;
        }else {
            return mRealAdapter.getItemViewType(position-mHeaderViewInfos.size());
        }
    }
}

