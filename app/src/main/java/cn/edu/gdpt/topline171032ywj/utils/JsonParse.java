package cn.edu.gdpt.topline171032ywj.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.edu.gdpt.topline171032ywj.Bean.NewsBean;
import cn.edu.gdpt.topline171032ywj.Bean.PythonBean;
import cn.edu.gdpt.topline171032ywj.Bean.VideoBean;

public class JsonParse {//单例模式 单个模式（对象产生；构造方法）
    private static JsonParse instance=getInstance();
    private JsonParse(){//构造方法私有化，类外部不能调用，就是不能创建对象
    }
    public static JsonParse getInstance(){
        if (instance==null){
            instance=new JsonParse();
        }
        return instance;
    }
    public List<NewsBean> getAdList(String json){
        Gson gson=new Gson();
        Type listType=new TypeToken<List<NewsBean>>(){

        }.getType();
        List<NewsBean> adList=gson.fromJson(json,listType);
        return adList;
    }

    public List<NewsBean> getNewsListt(String json) {
        Gson gson=new Gson();
        Type listType=new TypeToken<List<NewsBean>>(){

        }.getType();
        List<NewsBean> newsList=gson.fromJson(json,listType);
        return newsList;
    }
    public List<PythonBean> getPythonList(String json){
        Gson gson=new Gson();
        Type listType=new TypeToken<List<PythonBean>>(){}.getType();
        List<PythonBean> pythonList=gson.fromJson(json,listType);
        return pythonList;
    }
    public List<VideoBean> getVideoList(String json){
        Gson gson=new Gson();
        Type listType=new TypeToken<List<VideoBean>>(){
        }.getType();
        List<VideoBean> videoList=gson.fromJson(json,listType);
        return videoList;
    }
}
