package cn.edu.gdpt.topline171032ywj.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.PullToRefreshView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import cn.edu.gdpt.topline171032ywj.Bean.NewsBean;
import cn.edu.gdpt.topline171032ywj.R;
import cn.edu.gdpt.topline171032ywj.adapter.AdBannerAdapter;
import cn.edu.gdpt.topline171032ywj.adapter.HomeListAdapter;
import cn.edu.gdpt.topline171032ywj.utils.Constant;
import cn.edu.gdpt.topline171032ywj.utils.JsonParse;
import cn.edu.gdpt.topline171032ywj.utils.UtilsHelper;
import cn.edu.gdpt.topline171032ywj.view.WrepRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private PullToRefreshView mPullToRefreshView;
    private WrepRecyclerView recyclerView;
    private HomeListAdapter adapter;
    private View adBannerLay;
    private ViewPager adPager;
    private AdBannerAdapter ada;
    private OkHttpClient okHttpClient;
    private MHandler mHandler;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        okHttpClient=new OkHttpClient();
        mHandler=new MHandler();
        getADData();
        View view=initView(inflater,container);
        return view;

    }
    class MHandler extends Handler{
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case 1:
                    if (msg.obj!=null){
                        String adResult=(String)msg.obj;
                        List<NewsBean> ad1= JsonParse.getInstance().getAdList(adResult);
                      //  Toast.makeText(getContext(),String.valueOf(ad1.size()),Toast.LENGTH_LONG).show();
                        if (ad1!=null){
                            if (ad1.size()>0){
                                ada.setData(ad1);
                            }
                        }
                    }
                    break;
            }
        }
    }

    private void getADData() {
        Request request=new Request.Builder().url(Constant.WED_SITE+Constant.REQUEST_AD_URL).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request arg0, IOException arg1) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res=response.body().string();
                //Log.i("AD",res);
                Message msg=new Message();
                msg.what=1;
                msg.obj=res;
                mHandler.sendMessage(msg);
            }
        });
    }

    private View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        mPullToRefreshView=(PullToRefreshView)view.findViewById(R.id.mPullToRefresh);
        recyclerView=(WrepRecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new HomeListAdapter();
        recyclerView.setAdapter(adapter);
        View headView=inflater.inflate(R.layout.head_view,container,false);
        recyclerView.addHeaderView(headView);
        adBannerLay=headView.findViewById(R.id.adbanner_layout);
        adPager=(ViewPager)headView.findViewById(R.id.slidingADvertBanner);
        ada=new AdBannerAdapter(getActivity().getSupportFragmentManager());
        adPager.setAdapter(ada);
        resetSize();
        return view;
    }

    private void resetSize() {
        int sw= UtilsHelper.getScrernWidth(getActivity());
        int adLheight=sw/2;
        ViewGroup.LayoutParams adlp=adBannerLay.getLayoutParams();
        adlp.width=sw;
        adlp.height=adLheight;
        adBannerLay.setLayoutParams(adlp);
    }

}
