package com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity;

import android.view.View;
import android.widget.ImageView;

import com.example.rescueandroidapp.R;
import com.rescueandroid.update.network.util.MyBaseActivity;

/**
 * Created by feq on 2017/6/2.
 * 关于马甲
 */

public class AboutActivity extends MyBaseActivity implements View.OnClickListener {
    private ImageView mImageView ;
    @Override
    public int setview() {
        return R.layout.about_activity;
    }

    @Override
    public void init() {
        mImageView = (ImageView)  findViewById(R.id.user_agreement_iv1);
        mImageView.setOnClickListener(this);

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
            case R.id.user_agreement_iv1:

                finish();
                break;


        }
    }
}
