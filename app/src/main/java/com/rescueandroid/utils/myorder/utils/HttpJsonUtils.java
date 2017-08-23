package com.rescueandroid.utils.myorder.utils;

import android.app.Activity;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rescueandroid.utils.ui.ProgressDialogEx;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by CC on 2017/5/27
 */

public class HttpJsonUtils {
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_FAIL = 0;

//    private Handler mHandler = new Handler();
//    private static ProgressDialogEx progressDlgEx;

    public static int  numForDialog = 0;

    // loadingDialog
    private Activity activity;

    public HttpJsonUtils(Activity activity) {
        this.activity = activity;
//        if (progressDlgEx == null) {
//            progressDlgEx = new ProgressDialogEx(activity, mHandler);
//        }
    }

    public void getData(final JsonCallBack jsonCallBack, final int requestCode, String url, Map<String, String> params, final String contentString, final Class className) {
        RequestParams entity = new RequestParams(url);
        for (String key : params.keySet()) {
            String value = params.get(key);
            entity.addBodyParameter(key, value);
        }
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BaseModel model = gson.fromJson(result, BaseModel.class);
                JsonObject jObj = new JsonParser().parse(result).getAsJsonObject();
                if (jObj.get(contentString) != null && !jObj.get(contentString).isJsonNull()) {
                    if (jObj.get(contentString).isJsonArray()) {
                        JsonArray data = jObj.get(contentString).getAsJsonArray();
                        if (data != null && data.size() > 0) {
                            ArrayList list = new ArrayList();
                            for (int i = 0; i < data.size(); i++) {
                                list.add(new Gson().fromJson(data.get(i), className));
                            }
                            model.setData(list);
                        }
                    } else {
                        if (jObj.get(contentString).isJsonObject()) {
                            JsonObject data = jObj.get(contentString).getAsJsonObject();
                            model.setData(new Gson().fromJson(data, className));
                        } else {
                            String data = jObj.get(contentString).getAsString();
                            model.setData(data);
                        }
                    }
                } else {
                    LogUtil.e("JSON解析数据为空");
                }
                jsonCallBack.resultData(requestCode, RESULT_SUCCESS, model);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("网络获取失败:Error" + ex.getMessage());
                jsonCallBack.resultData(requestCode, 0, null);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                jsonCallBack.resultData(requestCode, 0, null);
            }

            @Override
            public void onFinished() {
            }
        });
    }

    public void getDataLoading(final JsonCallBack jsonCallBack, final int requestCode, String url, Map<String, String> params, final String contentString, final Class className) {
        RequestParams entity = new RequestParams(url);
        for (String key : params.keySet()) {
            String value = params.get(key);
            entity.addBodyParameter(key, value);
        }
        // loadingDialog
        if (numForDialog==0) {
//            progressDlgEx.simpleModeShowHandleThread();
            ProgressDialogEx.showDialog(activity);
        }
        numForDialog++;
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BaseModel model = gson.fromJson(result, BaseModel.class);
                JsonObject jObj = new JsonParser().parse(result).getAsJsonObject();
                if (jObj.get(contentString) != null && !jObj.get(contentString).isJsonNull()) {
                    if (jObj.get(contentString).isJsonArray()) {
                        JsonArray data = jObj.get(contentString).getAsJsonArray();
                        if (data != null && data.size() > 0) {
                            ArrayList list = new ArrayList();
                            for (int i = 0; i < data.size(); i++) {
                                list.add(new Gson().fromJson(data.get(i), className));
                            }
                            model.setData(list);
                        }
                    } else {
                        if (jObj.get(contentString).isJsonObject()) {
                            JsonObject data = jObj.get(contentString).getAsJsonObject();
                            model.setData(new Gson().fromJson(data, className));
                        } else {
                            String data = jObj.get(contentString).getAsString();
                            model.setData(data);
                        }
                    }
                } else {
                    LogUtil.e("JSON解析数据为空");
                }
                jsonCallBack.resultData(requestCode, RESULT_SUCCESS, model);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("网络获取失败:Error" + ex.getMessage());
                jsonCallBack.resultData(requestCode, RESULT_FAIL, null);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                jsonCallBack.resultData(requestCode, RESULT_FAIL, null);
            }

            @Override
            public void onFinished() {
                // loadingDialog
                numForDialog--;
                if (numForDialog==0){
                    ProgressDialogEx.hideDialog();
//                    progressDlgEx.closeHandleThread();
                }
            }
        });
    }

    public void postData(final JsonCallBack jsonCallBack, final int requestCode, String url, Map<String, String> params, final String contentString, final Class className) {
        RequestParams entity = new RequestParams(url);
        for (String key : params.keySet()) {
            String value = params.get(key);
            entity.addBodyParameter(key, value);
        }
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BaseModel model = gson.fromJson(result, BaseModel.class);
                JsonObject jObj = new JsonParser().parse(result).getAsJsonObject();
                if (jObj.get(contentString) != null && !jObj.get(contentString).isJsonNull()) {
                    if (jObj.get(contentString).isJsonArray()) {
                        JsonArray data = jObj.get(contentString).getAsJsonArray();
                        if (data != null && data.size() > 0) {
                            ArrayList list = new ArrayList();
                            for (int i = 0; i < data.size(); i++) {
                                list.add(new Gson().fromJson(data.get(i), className));
                            }
                            model.setData(list);
                        }
                    } else {
                        if (jObj.get(contentString).isJsonObject()) {
                            JsonObject data = jObj.get(contentString).getAsJsonObject();
                            model.setData(new Gson().fromJson(data, className));
                        } else {
                            String data = jObj.get(contentString).getAsString();
                            model.setData(data);
                        }
                    }
                } else {
                    LogUtil.e("JSON解析数据为空");
                }
                jsonCallBack.resultData(requestCode, RESULT_SUCCESS, model);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("网络获取失败:Error" + ex.getMessage());
                jsonCallBack.resultData(requestCode, 0, null);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                jsonCallBack.resultData(requestCode, 0, null);
            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void postDataLoading(final JsonCallBack jsonCallBack, final int requestCode, String url, Map<String, String> params, final String contentString, final Class className) {
        RequestParams entity = new RequestParams(url);
        for (String key : params.keySet()) {
            String value = params.get(key);
            entity.addBodyParameter(key, value);
        }
        // loadingDialog
        if (numForDialog==0) {
//            progressDlgEx.simpleModeShowHandleThread();
        }
        numForDialog++;
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BaseModel model = gson.fromJson(result, BaseModel.class);
                JsonObject jObj = new JsonParser().parse(result).getAsJsonObject();
                if (jObj.get(contentString) != null && !jObj.get(contentString).isJsonNull()) {
                    if (jObj.get(contentString).isJsonArray()) {
                        JsonArray data = jObj.get(contentString).getAsJsonArray();
                        if (data != null && data.size() > 0) {
                            ArrayList list = new ArrayList();
                            for (int i = 0; i < data.size(); i++) {
                                list.add(new Gson().fromJson(data.get(i), className));
                            }
                            model.setData(list);
                        }
                    } else {
                        if (jObj.get(contentString).isJsonObject()) {
                            JsonObject data = jObj.get(contentString).getAsJsonObject();
                            model.setData(new Gson().fromJson(data, className));
                        } else {
                            String data = jObj.get(contentString).getAsString();
                            model.setData(data);
                        }
                    }
                } else {
                    LogUtil.e("JSON解析数据为空");
                }
                jsonCallBack.resultData(requestCode, RESULT_SUCCESS, model);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("网络获取失败:Error" + ex.getMessage());
                jsonCallBack.resultData(requestCode, RESULT_FAIL, null);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                jsonCallBack.resultData(requestCode, RESULT_FAIL, null);
            }

            @Override
            public void onFinished() {
                // loadingDialog
                numForDialog--;
                if (numForDialog==0){
//                    progressDlgEx.closeHandleThread();
                }
            }
        });
    }

    public void putDataString(final JsonCallBackString jsonCallBack, final int requestCode, final String url, final Map<String, String> params, String contentString) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();

                FormBody.Builder builder = new FormBody.Builder();
                for (String key : params.keySet()) {
                    String value = params.get(key);
                    builder.add(key, value);
                }
                FormBody build = builder.build();
                Request build1 = new Request.Builder()
                        .url(url)
                        .put(build)
                        .build();
                okHttpClient.newCall(build1).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        jsonCallBack.resultData(requestCode, RESULT_FAIL, "error");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Looper.prepare();
                        String string = response.body().string();
                        if (jsonCallBack != null) {
                            jsonCallBack.resultData(requestCode, RESULT_FAIL, string);
                        }
                        Looper.loop();
                    }
                });
            }
        });
        thread.start();
    }

    // 直接添加参数
    public static String addParam(String url, Map<String, String> params) {
        if (null == url) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("?");
        if (params != null && params.size() != 0) {
            try {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    // 如果请求参数中有中文，需要进行URLEncoder编码
                    sb.append(entry.getKey())
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "utf-8"));
                    sb.append("&");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        return url;
    }
}
