package kr.co.kbinsure.bloodhomeworkparkjaewung.common;

import android.app.Application;
import android.content.Context;

public class BloodApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
    public static Context getGirlGroupContext(){
        return mContext;
    }

}
