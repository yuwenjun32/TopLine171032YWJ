package cn.edu.gdpt.topline171032ywj.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.itheima.PullToRefreshView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import cn.edu.gdpt.topline171032ywj.Bean.PythonBean;
import cn.edu.gdpt.topline171032ywj.R;
import cn.edu.gdpt.topline171032ywj.adapter.PythonListAdapter;
import cn.edu.gdpt.topline171032ywj.utils.Constant;
import cn.edu.gdpt.topline171032ywj.utils.JsonParse;
import cn.edu.gdpt.topline171032ywj.view.WrepRecyclerView;

public class PythonActivity extends AppCompatActivity {
    private PullToRefreshView mPullToRefreshView;
    private WrepRecyclerView recyclerView;
    private MHandler mHandler;
    private PythonListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        mHandler=new MHandler();
        initData();
        intiView();
    }
    class MHandler extends Handler{
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case 1:
                    if (msg.obj!=null){
                        String vlResult=(String)msg.obj;
                        List<PythonBean> pythonList= JsonParse.getInstance().getPythonList(vlResult);
                        //Toast.makeText(getApplicationContext(),String.valueOf(pythonList.size()),Toast.LENGTH_LONG).show();
                        adapter.setData(pythonList);
                    }
                    break;
            }
        }
    }

    private void initData() {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(Constant.WED_SITE+
                Constant.REQUEST_AD_URL).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request arg0, IOException arg1) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res=response.body().string();
                //Log.i("Python",res);
                Message msg=new Message();
                msg.what=1;
                msg.obj=res;
                mHandler.sendMessage(msg);
            }
        });

    }

    private void intiView() {
        mPullToRefreshView=(PullToRefreshView)findViewById(R.id.mPullToRefresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                        initData();
                    }
                },1000);
            }
        });
        recyclerView=(WrepRecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new PythonListAdapter();
        recyclerView.setAdapter(adapter);
    }
}
