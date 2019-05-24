package cn.edu.gdpt.topline171032ywj.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.gdpt.topline171032ywj.R;

public class LoginActivity extends AppCompatActivity {
    private EditText et_user_name,et_psw;
    private ImageView iv_show_psw;
    private TextView tv_quick_register,tv_forget_psw;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_user_name=(EditText)findViewById(R.id.et_user_name);
        et_psw=(EditText)findViewById(R.id.et_psw);
        iv_show_psw=(ImageView)findViewById(R.id.iv_show_psw);
        tv_quick_register=(TextView)findViewById(R.id.tv_quick_register);
        tv_forget_psw=(TextView)findViewById(R.id.tv_forget_psw);
        btn_login=(Button)findViewById(R.id.btn_login);
    }
}
