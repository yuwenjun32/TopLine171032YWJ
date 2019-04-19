package cn.edu.gdpt.topline171032ywj.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.edu.gdpt.topline171032ywj.Bean.NewsBean;
import cn.edu.gdpt.topline171032ywj.R;

public class AdBannerFragment extends Fragment {
    private NewsBean nb;
    private ImageView iv;
    public static AdBannerFragment newInstance(Bundle args){
        AdBannerFragment af=new AdBannerFragment();
        af.setArguments(args);
        return af;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg=getArguments();
        nb=(NewsBean)arg.getSerializable("ad");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (nb!=null){
            Glide
                    .with(getActivity())
                    .load(nb.getImg1())
                    .error(R.mipmap.ic_launcher)
                    .into(iv);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        iv=new ImageView(getActivity());
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        iv.setBackgroundColor(888888);
        iv.setLayoutParams(lp);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return iv;
    }
}
