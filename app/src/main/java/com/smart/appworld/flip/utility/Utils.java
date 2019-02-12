package com.smart.appworld.flip.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.smart.appworld.flip.MyApp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static boolean isNetworkAvailable() {
        Context context = MyApp.appContext;
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String getTodaysDate(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
        String formattedDate = df.format(c);
        return formattedDate;
    }

    public static String getXdaysAfterDate(int x){
        Calendar cal =Calendar.getInstance();
        Date date = cal.getInstance().getTime();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, +x);
        Date newDate = cal.getTime();
        System.out.println(x+" after time => " + newDate);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
        String formattedDate = df.format(newDate);
        return formattedDate;
    }

}

