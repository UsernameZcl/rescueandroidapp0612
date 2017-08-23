package com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.PersonalCenter_Fragment;
import com.example.rescueandroidapp.R;
import com.rescueandroid.data.Data;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.SharedPreferencesUtils;


/**
 * Created by ZCL on 2017/5/9.
 */

//设置界面
public class Set_up_Activity extends MyBaseActivity implements View.OnClickListener {
    private ImageView mImageView;
    private LinearLayout mLinearLayout1, mLinearLayout2;
    private Button mButton;
    private TextView mTextView;

    @Override
    public int setview() {
        return R.layout.set_up_activity;
    }

    @Override
    public void init() {
        //点击退出当前界面
        mImageView = (ImageView) findViewById(R.id.set_up_activity_iv1);
//版本号
        mTextView= (TextView) findViewById(R.id.set_vp_tv3);
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
            String versionName = packInfo.versionName;
            mTextView.setText("v"+versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mImageView.setOnClickListener(this);
        //关于马甲
        mLinearLayout1 = (LinearLayout) findViewById(R.id.set_up_activity_linlay1);
        mLinearLayout1.setOnClickListener(this);
        //拨打电话
        mLinearLayout2 = (LinearLayout) findViewById(R.id.set_up_activity_linlay2);
        mLinearLayout2.setOnClickListener(this);
        //退出登录
        mButton = (Button) findViewById(R.id.set_up_activity_but1);
        mButton.setOnClickListener(this);

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
            case R.id.set_up_activity_iv1:
                finish();
                break;
            case R.id.set_up_activity_linlay1:

                startActivity(new Intent(Set_up_Activity.this,AboutActivity.class));

                break;
            case R.id.set_up_activity_linlay2:

                AlertDialog.Builder builde  = new AlertDialog.Builder(Set_up_Activity.this);
                builde.setMessage("400-663-5656" ) ;
                builde.setPositiveButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builde.setNegativeButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //用intent启动拨打电话
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ "400-663-5656"));
                        startActivity(intent);
                    }
                });
                builde.show();

                break;
            case R.id.set_up_activity_but1:

                if((Data.getInstance().isLogin) == false){
                    Set_up_Activity.this.startActivity(new Intent(Set_up_Activity.this, LoginActivity.class));

                }else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("确认退出吗？");
                    builder.setTitle("提示");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Data.getInstance().isLogin = false;
                            SharedPreferencesUtils.put(Set_up_Activity.this, "userName", "");
                            SharedPreferencesUtils.put(Set_up_Activity.this, "userPass", "");
                            Data.getInstance().roleid = "";
                            Set_up_Activity.this.setResult(2);
                            dialog.dismiss();
                            finish();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }

                break;
        }
    }
}
