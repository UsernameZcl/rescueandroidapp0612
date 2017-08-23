package com.example.rescueandroidapp.Framgment.OrderFragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rescueandroidapp.Framgment.OrderFragment.adapter.MyOrderGrabAdapter;
import com.example.rescueandroidapp.Framgment.OrderFragment.model.MyGrabOrderPay;
import com.example.rescueandroidapp.R;
import com.rescueandroid.utils.myorder.utils.BaseModel;
import com.rescueandroid.utils.myorder.utils.HttpAppUtils;
import com.rescueandroid.utils.myorder.utils.HttpJsonUtils;
import com.rescueandroid.utils.myorder.utils.JsonCallBack;

import java.util.ArrayList;
import java.util.List;

import static com.rescueandroid.utils.myorder.utils.HttpAppUtils.ADDDATE;
import static com.rescueandroid.utils.myorder.utils.HttpAppUtils.UPDATE;

/**
 * Created by CC on 2017/5/29
 */

public class GrabOrderFragment extends Fragment implements JsonCallBack {

    private View view;

    private RecyclerView m_fragment_rv;
    private List<MyGrabOrderPay> datas;
    private MyOrderGrabAdapter myOrderPayAdapter;

    //根据不同页面和用户身份进行设置参数
    private String item = "0";
    private String ordertype = "0";
    private String state = "0";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initView();
        setupView();
        getTheArguments();
        fakeData(m_fragment_rv);
        initData();
    }

    private void initData() {
        HttpAppUtils.getGrabOrder(getActivity(), this, ADDDATE, "sfsy@163.com", "123456", ordertype, "0", item);
        HttpAppUtils.getGrabOrder(getActivity(), this, ADDDATE, "sfsy@163.com", "123456", ordertype, "1", item);
        HttpAppUtils.getGrabOrder(getActivity(), this, ADDDATE, "sfsy@163.com", "123456", ordertype, "2", item);
    }

    public void initData(String state) {
        Toast.makeText(getContext(), "item:" + item, Toast.LENGTH_SHORT).show();
    }

    private void getTheArguments() {
        Bundle arguments = getArguments();
        String flag = arguments.getString("title");
        switch (flag) {
            case "抢路线":
                ordertype = "1";
                item = "1";
                break;
            case "抢货源":
                ordertype = "1";
                item = "2";
                break;
            case "抢仓库":
                ordertype = "1";
                item = "3";
                break;
            case "接路线":
                ordertype = "2";
                item = "1";
                break;
            case "接货源":
                ordertype = "2";
                item = "2";
                break;
            case "接仓库":
                ordertype = "2";
                item = "3";
                break;
            default:
                Toast.makeText(getActivity(), "title is null", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initView() {
        m_fragment_rv = (RecyclerView) view.findViewById(R.id.fragment_rv);
    }

    private void setupView() {

    }

    private void fakeData(RecyclerView m_myOrder_List) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        m_myOrder_List.setLayoutManager(layoutManager);
        datas = new ArrayList<>();
        if ("1".equals(item) || "2".equals(item)) {
            myOrderPayAdapter = new MyOrderGrabAdapter(getActivity(), datas, R.layout.item_order_grab_item1);
        } else {
            myOrderPayAdapter = new MyOrderGrabAdapter(getActivity(), datas, R.layout.item_order_grab);
        }
        m_myOrder_List.setAdapter(myOrderPayAdapter);
    }

    /**
     * TODO 网络请求回调
     */
    @Override
    public void resultData(int requestCode, int resultCode, BaseModel reply) {
        switch (requestCode) {
            case UPDATE:
                if (resultCode == HttpJsonUtils.RESULT_SUCCESS) {
                    datas = (List<MyGrabOrderPay>) reply.getData();
                    myOrderPayAdapter.upDatas(datas);
                }
                break;
            case ADDDATE:
                if (resultCode == HttpJsonUtils.RESULT_SUCCESS) {
                    if (resultCode == HttpJsonUtils.RESULT_SUCCESS) {
                        datas = (List<MyGrabOrderPay>) reply.getData();
                        myOrderPayAdapter.addDatas(datas);
                    }
                    break;
                }
        }
    }
}
