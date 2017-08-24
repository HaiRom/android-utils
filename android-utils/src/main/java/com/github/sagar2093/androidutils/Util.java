package com.github.sagar2093.androidutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.StringRes;
import android.text.Html;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Sagar Chapagain on 8/16/2017.
 * https://github.com/sagar2093
 * sagar2093@gmail.com
 */

public class Util {

    public static boolean isOnline(Context context, boolean showToast) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            /*if (netInfo.getType() != ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(context, "This app doesn't work without wifi", Toast.LENGTH_LONG).show();
                return false;
            }*/
            return true;
        } else {
            if (showToast) {
                toastNoInternet(context);
            }
            return false;
        }
    }

    public static boolean isWifiAvailable(Context context, boolean showToast) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting() && netInfo.getType() != ConnectivityManager.TYPE_WIFI) {
            return true;
        } else {
            if (showToast) {
                toastNoWifi(context);
            }
            return false;
        }
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, @StringRes int messageId) {
        Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show();
    }

    public static void toastNoInternet(Context context) {
        Toast.makeText(context, R.string.toast_no_internet, Toast.LENGTH_LONG).show();
    }

    public static void toastNoWifi(Context context) {
        Toast.makeText(context, R.string.toast_no_wifi, Toast.LENGTH_LONG).show();
    }

    public static String fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(source).toString();
        }
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0".concat(hashtext);
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
