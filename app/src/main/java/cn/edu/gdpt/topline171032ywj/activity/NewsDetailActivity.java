package cn.edu.gdpt.topline171032ywj.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.edu.gdpt.topline171032ywj.Bean.NewsBean;
import cn.edu.gdpt.topline171032ywj.R;

public class NewsDetailActivity extends AppCompatActivity {
    private WebView webView;
    private NewsBean bean;
    private String newsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
        bean=(NewsBean)getIntent().getSerializableExtra("newsBean");
        if (bean==null){
            return;
        }
        newsUrl=bean.getNewsUrl();
        initWebView();
    }

    private void initWebView() {
        webView.loadUrl(newsUrl);
        WebSettings mWebSettings=webView.getSettings();
        mWebSettings.setSupportMultipleWindows(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDefaultTextEncodingName("GBK");
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }
        );
    }

    private void initView() {
        webView=(WebView)findViewById(R.id.webView);
    }
}
