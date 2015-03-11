package jp.co.indival.shotalert.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shhirats on 3/3/15.
 */
public class Util {

    public static String getStrPreferences(Context context,String key){
        SharedPreferences pref = context.getSharedPreferences("shotalert",context.MODE_PRIVATE);
        String val = pref.getString(key, null);
        return val;
    }

    public static int getIntPreferences(Context context,String key){
        SharedPreferences pref = context.getSharedPreferences("shotalert",context.MODE_PRIVATE);
        int val = pref.getInt(key, 0);
        return val;
    }

    /**
     * キーに対応したStringをSharedPreferencesに保存
     */
    public static void saveStrPreferences(String key,String val,Context context){
        SharedPreferences pref = context.getSharedPreferences("shotalert",context.MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        e.putString(key,val);
        e.commit();
    }
}
