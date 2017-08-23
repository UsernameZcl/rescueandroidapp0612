package com.example.rescueandroidapp.Framgment;

import java.io.File;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity.LoginActivity;
import com.example.rescueandroidapp.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.rescueandroid.config.Text;
import com.rescueandroid.data.BaseDataService;
import com.rescueandroid.data.Data;
import com.rescueandroid.exception.NetConnectionException;
import com.rescueandroid.utils.JsonUtils;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.ui.DialogUtils;
import com.umeng.onlineconfig.OnlineConfigAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


/**
 * launch页
 */
public class LaunchActivity extends Activity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnlineConfigAgent.getInstance().updateOnlineConfig(this);
        setContentView(R.layout.activity_launch);
        final ImageView ivImg = (ImageView) this.findViewById(R.id.ivImg);
        File cacheDir = StorageUtils.getOwnCacheDirectory(this, "imageloader/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).memoryCacheExtraOptions(480, 800)
                // maxwidth, maxheight，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)// 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You
                // can // pass // your // own // memory // cache
                // implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024).discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                // 将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO).discCacheFileCount(100) // 缓存的文件数量
                .discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000))
                // connectTimeout(5 s), readTimeout(30s)超时时间
                .build();// 开始构建
        ImageLoader.getInstance().init(config);
        gotoMain();
    }


    public void gotoMain() {
        // 查询是否登录 进行自动登录
        final String username = SharedPreferencesUtils.getString(this, "userName", "");// 获取用户名
        final String userPass = SharedPreferencesUtils.getString(this, "userPass", "");// 获取密码
//        if (!username.equals("")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        final JSONObject jsobj = BaseDataService.login(username, userPass);
                        String code = jsobj.getString("status");
                        if (code.equals("1")) {
                            final JSONObject result = jsobj.getJSONObject("data");
                            mHandler.post(new Runnable() {
                                public void run() {
                                    Map map = JsonUtils.parseJson(result);
                                    String name = map.get("username").toString();
                                    Data.getInstance().username = name;
                                    Data.getInstance().realname = map.get("realname").toString();
                                    Data.getInstance().roleid = map.get("roleid").toString();
                                    String photo = map.get("photo").toString();
                                    Data.getInstance().photo = photo;
                                    Data.getInstance().pwd = userPass;
                                    Data.getInstance().isLogin = true;
                                }
                            });
                        }
                    } catch (NetConnectionException e) {
                        // TODO Auto-generated catch block
                        DialogUtils.showPopMsgInHandleThread(LaunchActivity.this, mHandler, Text.NetConnectFault);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        DialogUtils.showPopMsgInHandleThread(LaunchActivity.this, mHandler, Text.ParseFault);
                        e.printStackTrace();
                    }
                }
            }).start();

//        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent();
                intent.setClass(LaunchActivity.this, MainFrameActivity.class);
                startActivity(intent);
                finish();
                //淡化消失的动画
                overridePendingTransition(R.anim.alpha_into, R.anim.alpha_out);
            }
        }, 2000);
    }

}
