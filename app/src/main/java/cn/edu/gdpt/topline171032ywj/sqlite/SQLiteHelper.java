package cn.edu.gdpt.topline171032ywj.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private  static final int DB_VERSION=1;
    public static String DB_NAME="topline.db";
    public static final String U_USERINFO="userinfo";
    public SQLiteHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+U_USERINFO+"("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"userName VARCHAR,"    //用户名
                +"password VARCHAR,"    //登录密码
                +"nickName VARCHAR,"    //昵称
                +"sex VARCHAR,"         //性别
                +"signature VARCHAR,"   //签名
                +"head VARCHAR"         //头像
                +")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+U_USERINFO);
        onCreate(db);
    }
}
