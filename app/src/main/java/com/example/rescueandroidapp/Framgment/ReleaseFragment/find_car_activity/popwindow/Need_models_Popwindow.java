package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.rescueandroidapp.R;
import com.rescueandroid.wheelview.OnWheelChangedListener;
import com.rescueandroid.wheelview.OnWheelScrollListener;
import com.rescueandroid.wheelview.WheelView;
import com.rescueandroid.wheelview.adapter.AbstractWheelTextAdapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZCL on 2017/5/2.
 */
//需要的类型
public class Need_models_Popwindow extends PopupWindow implements View.OnClickListener {
    private Context mContext;
    private TextView btnSure;
    private TextView btnCancel;
    private View lyChangeAddressChild;
    private final WheelView mType;
    private String strProvince = "普通货车";
    // TODO: 2017/5/2
    private OnAddressCListener onAddressCListener;
    private int maxsize = 14;
    private int minsize = 12;
    ArrayList<String> strings1 = new ArrayList<>();

    private final TypeTextAdapter mTypeTextAdapter;
    public Need_models_Popwindow(Context context) {
        super(context);
        mContext = context;
        View view=View.inflate(context, R.layout.edit_changetype_pop_layout,null);
        //要显示的内容
        mType = (WheelView) view.findViewById(R.id.wv_type);


        lyChangeAddressChild = view.findViewById(R.id.ly_myinfo_changeaddress_child);

        //确定按钮
        btnSure = (TextView) view.findViewById(R.id.btn_myinfo_sure);
        //取消按钮
        btnCancel = (TextView)view. findViewById(R.id.btn_myinfo_cancel);



        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//		this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);



        lyChangeAddressChild.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        //加载数据
        initDatas();

        mTypeTextAdapter = new TypeTextAdapter(mContext, strings1, getTypeItem(strProvince), maxsize, minsize);
        mType.setVisibleItems(5);
        mType.setViewAdapter(mTypeTextAdapter);
        mType.setCurrentItem( getTypeItem(strProvince));
        mType.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String itemText = (String) mTypeTextAdapter.getItemText(wheel.getCurrentItem());
                strProvince = itemText;
                setTextviewSize(itemText, mTypeTextAdapter);


            }
        });

        mType.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mTypeTextAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mTypeTextAdapter);
            }
        });

    }
    //返回检索，没有就 返回默认“水产品”

    public  int  getTypeItem(String province){

        int size = strings1.size();
        int provinceIndex = 0;
        boolean noprovince = true;
        for (int i = 0; i <size ; i++) {
            if(province.equals(strings1.get(i))){
                noprovince = false;
                return provinceIndex;

            }
            else {
                provinceIndex++;
            }





        }
        if (noprovince) {
            strProvince = "普通货车";
            return 0;
        }
        return provinceIndex;

    }
    private List<String> initDatas() {
        String[] strings = {"普通货车","冷藏车","平板车","常温箱式车","集装箱车","高拦车"};

        for (int i = 0; i < strings.length; i++) {
            strings1.add( strings[i]);
        }
        return strings1;
    }
    public void setAddresskListener(OnAddressCListener onAddressCListener) {
        this.onAddressCListener = onAddressCListener;
    }
    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        if (view == btnSure) {
            if (onAddressCListener != null) {
                onAddressCListener.onClick(strProvince);
            }
        } else if (view == btnCancel) {

        } else if (view == lyChangeAddressChild) {
            return;
        } else {
//			dismiss();
        }
        dismiss();
    }
    class  TypeTextAdapter extends AbstractWheelTextAdapter1 {
        ArrayList<String> list;
        protected TypeTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View convertView, ViewGroup parent) {
            View view = super.getItem(index, convertView, parent);
            return view;
        }
        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) ;
        }
        @Override
        public int getItemsCount() {
            int size = list.size();

            return size;
        }
    }
    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText,TypeTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(14);
            } else {
                textvew.setTextSize(12);
            }
        }
    }
    /**
     * 回调接口
     *
     * @author Administrator
     *
     */
    public interface OnAddressCListener {
        public void onClick(String type);
    }

    /**
     * 初始化类型
     *
     */
    public  void  setType(String  type){
        if (type != null && type.length() > 0) {
            this.strProvince = type;
        }
    }
}
