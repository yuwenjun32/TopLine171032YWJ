package cn.edu.gdpt.topline171032ywj.Fragment;


import android.content.Intent;
import android.os.Bundle;
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
    CircleImageView iv_avatar;
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
        iv_avatar=(CircleImageView)view.findViewById(R.id.iv_avatar);
        iv_avatar.setImageResource(R.drawable.default_head);
        iv_avatar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_avatar:
                //跳转到登录界面
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
