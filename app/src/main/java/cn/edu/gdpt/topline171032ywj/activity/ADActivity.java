package cn.edu.gdpt.topline171032ywj.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.edu.gdpt.topline171032ywj.Bean.ADBean;
import cn.edu.gdpt.topline171032ywj.R;

public class ADActivity extends AppCompatActivity {
    private WebView adwebView;
    private ADBean bean;
    private String newsUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        initView();
        bean=(ADBean)getIntent().getSerializableExtra("ADBean");
        if (bean==null){
            return;
        }
        newsUrl=bean.getNewsUrl();
        initWebView();
    }

    private void initWebView() {
        adwebView.loadUrl(newsUrl);
        WebSettings mWebSettings=adwebView.getSettings();
        mWebSettings.setSupportMultipleWindows(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDefaultTextEncodingName("GBK");
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setJavaScriptEnabled(true);
        adwebView.setWebViewClient(new WebViewClient(){
                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                         view.loadUrl(url);
                                         return true;
                                     }
                                 }
        );
    }

    private void initView() {
        adwebView=(WebView)findViewById(R.id.adwebView);
    }
}
