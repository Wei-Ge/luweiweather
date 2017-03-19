package com.luweiweather.app.util;

/**
 * Created by user on 2017/3/18 0018.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
