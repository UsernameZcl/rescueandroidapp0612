package com.example.rescueandroidapp.Framgment.HomePagerFragment;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.rescueandroidapp.Framgment.HomePagerFragment.activity.FindrouteActivity;
import com.example.rescueandroidapp.Framgment.HomePagerFragment.activity.FindwarehouseActivity;

import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity.LoginActivity;
import com.example.rescueandroidapp.R;
import com.rescueandroid.data.Data;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.ui.DialogUtils;
import com.rescueandroid.utils.ui.WindowsBase;

/**
 * 主页
 */
public class HomePager_Fragment extends WindowsBase implements View.OnClickListener {

    public static HomePager_Fragment mHomePager_fragment = null;
    private Handler mHandler = new Handler();
    private LayoutInflater factory;
    private LinearLayout mLinearLayout1, mLinearLayout2, mLinearLayout3, mLinearLayout4;

    //    private ListView mListView;
    private final String mUsername;
    private WebView mWebView;
    private String url = "http://114.215.122.32:16000/mobile/aroundmap";


    public HomePager_Fragment(Context context) {
        super(context);
        mHomePager_fragment = this;
        factory = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout) factory.inflate(R.layout.homepager_fragment, null);

        // 获取用户
        mUsername = SharedPreferencesUtils.getString(HomePager_Fragment.this.getContext(), "userName", "");
        final String userPass = SharedPreferencesUtils.getString(HomePager_Fragment.this.getContext(), "userPass", "");// 获取密码

        addView(layout, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        mLinearLayout1 = (LinearLayout) HomePager_Fragment.this.findViewById(R.id.homepager_fragment_linlay1);
        mLinearLayout2 = (LinearLayout) HomePager_Fragment.this.findViewById(R.id.homepager_fragment_linlay2);
        mLinearLayout3 = (LinearLayout) HomePager_Fragment.this.findViewById(R.id.homepager_fragment_linlay3);
        mLinearLayout4 = (LinearLayout) HomePager_Fragment.this.findViewById(R.id.homepager_fragment_linlay4);
//        mListView=(ListView) HomePager_Fragment.this.findViewById(R.id.homepager_fragment_lv);


        mWebView = (WebView) HomePager_Fragment.this.findViewById(R.id.homepager_fragment_wv);
        init();

        mLinearLayout1.setOnClickListener(this);
        mLinearLayout2.setOnClickListener(this);
        mLinearLayout3.setOnClickListener(this);
        mLinearLayout4.setOnClickListener(this);

    }

    private void init() {
        mWebView.loadUrl(url);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, final int newProgress) {

                if (newProgress == 100) {

                    mWebView.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.homepager_fragment_linlay1://找路

                if ((Data.getInstance().isLogin) == false) {
                    HomePager_Fragment.this.getContext().startActivity(new Intent(HomePager_Fragment.this.getContext(), LoginActivity.class));

                } else {
                    HomePager_Fragment.this.getContext().startActivity(new Intent(HomePager_Fragment.this.getContext(), FindrouteActivity.class));

                }


                break;
            case R.id.homepager_fragment_linlay2://司机找活

                DialogUtils.showPopMsgInHandleThread(HomePager_Fragment.this.getContext(), mHandler, "正在开发，请稍后重试！");


                break;
            case R.id.homepager_fragment_linlay3://仓库找货

                DialogUtils.showPopMsgInHandleThread(HomePager_Fragment.this.getContext(), mHandler, "正在开发，请稍后重试！");


                break;
            case R.id.homepager_fragment_linlay4://我要找库

                if ((Data.getInstance().isLogin) == false) {
                    HomePager_Fragment.this.getContext().startActivity(new Intent(HomePager_Fragment.this.getContext(), LoginActivity.class));

                } else {

                    HomePager_Fragment.this.getContext().startActivity(new Intent(HomePager_Fragment.this.getContext(), FindwarehouseActivity.class));
//                DialogUtils.showPopMsgInHandleThread(HomePager_Fragment.this.getContext(), mHandler, "正在开发，请稍后重试！");
                }

                break;

        }
    }
}
