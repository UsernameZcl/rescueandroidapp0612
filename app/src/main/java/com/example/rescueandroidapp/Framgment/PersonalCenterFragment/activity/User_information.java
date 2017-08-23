package com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rescueandroidapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rescueandroid.config.Define;
import com.rescueandroid.data.Data;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.ui.RoundAngleImageView;

/**
 * Created by 冯志强 on 2017/6/1 0001.
 * 用户信息
 */

public class User_information extends MyBaseActivity implements View.OnClickListener {

    private ImageView info_back ;
    private RoundAngleImageView User_infor_image;
    private TextView user_info_tel,user_info_name,user_info_name2;
    private LinearLayout layoutUser_pwd,layoutUser_pay_pwd,user_adress,user_mycar;

    @Override
    public int setview() {
        return R.layout.user_information;
    }

    @Override
    public void init() {

        info_back = (ImageView) findViewById(R.id.info_back);
        info_back.setOnClickListener(this);
        User_infor_image = (RoundAngleImageView) findViewById(R.id.User_infor_image);//头像
        user_info_tel = (TextView) findViewById(R.id.user_info_tel);//电话号码
        user_info_name = (TextView) findViewById(R.id.user_info_name);//身份认证
        user_info_name2 = (TextView) findViewById(R.id.user_info_name2);//平台认证
        layoutUser_pwd = (LinearLayout) findViewById(R.id.layoutUser_pwd);//修改密码
        layoutUser_pwd.setOnClickListener(this);
        layoutUser_pay_pwd = (LinearLayout) findViewById(R.id.layoutUser_pay_pwd);//支付密码
        user_adress = (LinearLayout) findViewById(R.id.user_adress);//我的地址
        user_mycar = (LinearLayout) findViewById(R.id.user_mycar);//我的车辆

        show();
    }

    private void show(){

        String icon = Data.getInstance().photo;

        ImageLoader.getInstance().displayImage(Define.BASEURL + icon, User_infor_image,
                    Define.getDisplayImageOptions());

        String name = Data.getInstance().username;
        user_info_tel.setText(name);

    }

    @Override
    public void setbase() {

    }

    @Override
    public MyBaseActivity getactivity() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.info_back:

                finish();

                break;

            case R.id.layoutUser_pwd:

                startActivity(new Intent(User_information.this,ChangePwd.class));

                break;

        }
    }
}
