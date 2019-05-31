package cn.edu.gdpt.topline171032ywj.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.gdpt.topline171032ywj.R;
import cn.edu.gdpt.topline171032ywj.activity.LoginActivity;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    private View view;
    private  CircleImageView iv_avatar;
    private boolean isLogin=false;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_me, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        collapsingToolbarLayout=(CollapsingToolbarLayout)view.findViewById(R.id.collapsing_tool_bar);
        collapsingToolbarLayout.setTitle("无登录");
        iv_avatar=(CircleImageView)view.findViewById(R.id.iv_avatar);
        iv_avatar.setImageResource(R.drawable.default_head);
        iv_avatar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_avatar:
                //跳转到登录界面
                Intent intent=new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                //startActivity(intent);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&data!=null){
            boolean isLogin=data.getBooleanExtra("isLogin",false);
            String userName=data.getStringExtra("loginUserName");
            collapsingToolbarLayout.setTitle(userName);
            this.isLogin=isLogin;
        }
    }
}
