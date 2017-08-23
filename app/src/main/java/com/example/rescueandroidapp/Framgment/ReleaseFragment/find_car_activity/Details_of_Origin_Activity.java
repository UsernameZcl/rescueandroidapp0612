package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.baen.GetJsonDataUtil;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.baen.JsonBean;
import com.example.rescueandroidapp.R;
import com.google.gson.Gson;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.mytoast.NToast;

import org.json.JSONArray;

import java.util.ArrayList;

import static com.example.rescueandroidapp.R.id.odetails_of_origin_activity_tv1;

/**
 * Created by ZCL on 2017/5/29.
 */
//选择始发地信息
public class Details_of_Origin_Activity extends MyBaseActivity implements View.OnClickListener {
    private ImageView mImageView;
    private TextView mTextView;
    private EditText mEditText1, mEditText2, mEditText3;
    private String mProvince1;
    private String mArea1;
    private String mCity1;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private boolean isLoaded = false;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

//                        Toast.makeText(Details_of_Origin_Activity.this,"开始解析数据",Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
//                    Toast.makeText(Details_of_Origin_Activity.this,"解析数据成功",Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
//                    Toast.makeText(Details_of_Origin_Activity.this,"解析数据失败",Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    @Override
    public int setview() {
        return R.layout.details_of_origin_activity;

    }

    @Override
    public void init() {
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        mImageView = (ImageView) findViewById(R.id.details_of_origin_activity_iv1);
        mTextView = (TextView) findViewById(odetails_of_origin_activity_tv1);
        mEditText1 = (EditText) findViewById(R.id.details_of_origin_activity_et1);
        mEditText2 = (EditText) findViewById(R.id.details_of_origin_activity_et2);
        mEditText3 = (EditText) findViewById(R.id.details_of_origin_activity_et3);
        mImageView.setOnClickListener(this);
        mEditText1.setOnClickListener(this);
        mTextView.setOnClickListener(this);
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
            //点击图片消失的
            case R.id.details_of_origin_activity_iv1:
                finish();
                break;
            //始发地
            case R.id.details_of_origin_activity_et1:
//                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(Details_of_Origin_Activity.this);
//                mChangeAddressPopwindow.setAddress("北京", "北京", "东城区");
//                mChangeAddressPopwindow.showAtLocation(mEditText1, Gravity.BOTTOM, 0, 0);
//                mChangeAddressPopwindow.setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
//                    @Override
//                    public void onClick(String province, String city, String area) {
//
//                        mEditText1.setText(province+city+ area);
//                        mProvince1 = province + "";
//                        mCity1 = city + "";
//                        mArea1 = area + "";
//                    }
//                });
                if (isLoaded) {
                    ShowPickerView();
                } else {
                    Toast.makeText(Details_of_Origin_Activity.this, "数据暂未解析成功，请等待", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.odetails_of_origin_activity_tv1:
                Intent intent = new Intent(Details_of_Origin_Activity.this, Release_Car_Supply_Activity.class);
                String trim = mEditText1.getText().toString().trim();
                String trim1 = mEditText2.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {

                    NToast.shortToast(Details_of_Origin_Activity.this, "请输入正确的地址");
                    return;

                }
                if(TextUtils.isEmpty(trim1)){
                    NToast.shortToast(Details_of_Origin_Activity.this, "请输入正确的地址详细地址");
                    return;
                }

                    String s = trim + trim1;
                    intent.putExtra("key", s);
                    intent.putExtra("detailed_address", trim1);
                    intent.putExtra("province", mProvince1);

                    intent.putExtra("city", mCity1);
                    intent.putExtra("area", mArea1);
                    setResult(RESULT_OK, intent);
                    finish();

                break;
        }
    }

    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                //options1Items.get(options1).getPickerViewText()+"";

                mEditText1.setText(tx);
                mProvince1 = options1Items.get(options1).getPickerViewText() + "";
                mCity1 = options2Items.get(options1).get(options2) + "";
                mArea1 = options3Items.get(options1).get(options2).get(options3) + "";

//                Toast.makeText(Details_of_Origin_Activity.this,tx,Toast.LENGTH_SHORT).show();
            }
        })

//                .setTitleText("城市选择")
                .setDividerColor(Color.parseColor("#d8d8d8"))
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }
}
