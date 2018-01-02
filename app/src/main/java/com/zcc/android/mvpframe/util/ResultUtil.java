package com.zcc.android.mvpframe.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zcc.android.mvpframe.data.Constant;
import com.zcc.android.mvpframe.data.ResponseResult;
import com.zcc.android.mvpframe.mvpc.model.ICallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

/**
 * Created by DeMon on 2017/11/7.
 * 请求结果处理类
 */

public class ResultUtil {
    private static final String TAG = "ResultUtil";

    /**
     * 将接口返回的字符串去掉XML头
     *
     * @param datas 源字符串
     * @return 结果字符串（已去掉了XML头）
     */
    public static String parseXML(String datas) {
        XmlPullParserFactory factory = null;
        String result = "";
        try {
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(datas));
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("string".equals(nodeName)) {
                            result = parser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 针对Api返回数据格式进行统一处理
     *
     * @param result
     * @param callback
     */
    public static void dealResult(String result, ICallBack callback) {
        String json = parseXML(result);
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(Constant.SUCCESS)) {
                String success = jsonObject.getString(Constant.SUCCESS);
                JSONObject successJson = new JSONObject(success);
                // 判断Json字符串是否包含response字段
                if (successJson.has(Constant.RESPONSE)) {
                    if (successJson.get(Constant.RESPONSE) instanceof JSONObject) {
                        //response是JsonObject
                        JSONObject response = successJson.getJSONObject(Constant.RESPONSE);
                        Log.i(TAG, "dealResult: " + response.toString());
                        callback.resultSuccess(response.toString());
                    } else if (successJson.get(Constant.RESPONSE) instanceof JSONArray) {
                        //response是JsonArray
                        JSONArray response = successJson.getJSONArray(Constant.RESPONSE);
                        Log.i(TAG, "dealResult: " + response.toString());
                        callback.resultSuccess(response.toString());
                    }
                } else {
                    Log.i(TAG, "dealResult: " + success);
                    callback.resultSuccess(success);
                }
            } else {
                Log.i(TAG, "dealResult: " + json);
                String error = jsonObject.getString(Constant.ERROR);
                ResponseResult errorResult = new Gson().fromJson(error, ResponseResult.class);
                callback.resultSuccess(json);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 公告消息的处理
     *
     * @param result
     * @param callback
     */
    public static void dealNotice(String result, ICallBack callback) {
        String json = parseXML(result);
        String s = json.substring(json.indexOf("|") + 1, json.length() - 2);
        Log.i(TAG, "dealNotice: " + json);
        callback.resultSuccess(s);
    }
}
