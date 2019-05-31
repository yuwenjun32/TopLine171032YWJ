package cn.edu.gdpt.topline171032ywj.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.edu.gdpt.topline171032ywj.sqlite.SQLiteHelper;

public class DBUtils {
    private static DBUtils instance=null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;

    public DBUtils(Context context) {
        helper=new SQLiteHelper(context);
        db=helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context){
        if (instance==null){
            instance=new DBUtils(context);
        }
        return instance;
    }
    /*
    *判断用户名是否存在
     **/
    public boolean userIsExist(String userName){
        boolean result=false;
        String sql="SELECT * FROM " + SQLiteHelper.U_USERINFO + " WHERE userName=?";
        Cursor cursor=db.rawQuery(sql,new String[]{userName});
        if (cursor.getCount()>0){
            result=true;
        }
        return result;
    }
    /*
     *用户注册
     **/
    public boolean userRegister(String userName,String password){
        boolean result=false;
        ContentValues cv=new ContentValues();
        cv.put("userName",userName);
        cv.put("password",password);
        long id=db.insert(SQLiteHelper.U_USERINFO,null,cv);
        if (id>0){
            result=true;
        }
        return result;
    }
    /*
     *用户登录
     **/
    public boolean userLogin(String userName,String password){
        boolean result=false;
        String sql="SELECT * FROM " + SQLiteHelper.U_USERINFO + " WHERE userName=? and password=?";
        Cursor cursor=db.rawQuery(sql,new String[]{userName,password});
        if (cursor.getCount()>0){
            result=true;
        }
        return result;
    }
}
