package com.example.a3mpe.customfingerprint.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.a3mpe.customfingerprint.baseAplicaton.baseAplicatonItem;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by Gaaraj on 11.05.2017.
 */

public class LocalStorageUtil {

    private static Context appContext;

    private final static String TAG_PREFERENCES_FINGERPRINT = "asdfg";
    private final static String TAG_SESSION = "sessionasdf";

    public static void setFingerPrintSession(Context context, FingerPrintSession mSession) {
        SharedPreferences preferences = baseAplicatonItem.getContext().getSharedPreferences(TAG_PREFERENCES_FINGERPRINT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String jsonResult = gson.toJson(mSession);
        editor.putString(TAG_SESSION, jsonResult);
        editor.apply();
    }

    public static FingerPrintSession getFingerPrintSession() {
        appContext = baseAplicatonItem.getContext();
        SharedPreferences preferences = appContext.getSharedPreferences(TAG_PREFERENCES_FINGERPRINT, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        return gson.fromJson(preferences.getString(TAG_SESSION, null), FingerPrintSession.class);
    }

    public static void setOpenCount(Context context, int count) {
        if (count > 5) {
            count = 0;
        }
        SharedPreferences preferences = context.getSharedPreferences("openCount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("count", count);
        editor.apply();
    }

    public static int getOpenCount(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("openCount", Context.MODE_PRIVATE);
        return preferences.getInt("count", 0);
    }
}

