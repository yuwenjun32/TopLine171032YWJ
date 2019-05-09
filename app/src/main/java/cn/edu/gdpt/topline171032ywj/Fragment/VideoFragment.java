package cn.edu.gdpt.topline171032ywj.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itheima.PullToRefreshView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import cn.edu.gdpt.topline171032ywj.Bean.VideoBean;
import cn.edu.gdpt.topline171032ywj.R;
import cn.edu.gdpt.topline171032ywj.utils.Constant;
import cn.edu.gdpt.topline171032ywj.utils.JsonParse;
import cn.edu.gdpt.topline171032ywj.view.WrepRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    private MHandle mHandle;
    private PullToRefreshView mPullToRefreshView;
    private WrepRecyclerView recyclerView;


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mHandle=new MHandle();
        initData();
        View view=initView(inflater,container);
        return view;
    }

    class MHandle extends Handler {

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case 1:
                    if (msg.obj!=null){
                        String vlResult=(String)msg.obj;
                        List<VideoBean> videoList= JsonParse.getInstance().getVideoList(vlResult);//使用Gson解析数据
                        Toast.makeText(getContext(),String.valueOf(videoList.size()),Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    private void initData() {//使用okHttpClient读取数据
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(Constant.WED_SITE+Constant.REQUEST_VIDEO_URL).build();
        Call call=okHttpClient.newCall(request);
        //开启异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request arg0, IOException arg1) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res=response.body().string();
                //Log.i("videoString",res);
                Message msg=new Message();
                msg.what=1;
                msg.obj=res;
                mHandle.sendMessage(msg);
            }
        });
    }

    private View initView(LayoutInflater inflater,ViewGroup contsiner){
        View view=inflater.inflate(R.layout.fragment_video,contsiner,false);
        recyclerView=(WrepRecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPullToRefreshView=(PullToRefreshView)view.findViewById(R.id.pull_to_refresh);
        return view;
    }

}
