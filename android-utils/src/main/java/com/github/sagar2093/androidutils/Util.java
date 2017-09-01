package com.github.sagar2093.androidutils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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

    public static void logError(String msg) {
        Log.e("LOG_ERROR", msg);
    }

    public static void logDebug(String msg) {
        Log.d("LOG_DEBUG", msg);
    }

    public static String fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(source).toString();
        }
    }

    public static void hideKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * Draws a toolbar in the appropriate place programmatically.
     *
     * @param activity the activity you want to display the toolbar on.
     * @param toolbar  your toolbar.
     * @param title    the toolbar title.
     */
    public static void drawToolbar(AppCompatActivity activity, Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        activity.setSupportActionBar(toolbar);
    }

    public static void setBackNavigation(final Activity activity, Toolbar toolbar) {
        //Navigation Icon
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    /**
     * Restarts an activity wherever the method is run.
     *
     * @param activity activity to be restarted.
     */
    public static void restart(Activity activity) {
        try {
            Intent i = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            int mPendingIntentId = 223344;
            PendingIntent mPendingIntent = PendingIntent.getActivity(activity, mPendingIntentId, i, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager mgr = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            activity.finish();
        }
    }

    public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isEmailValid(EditText editText, Context context) {
        boolean valid = android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText().toString().trim()).matches();
        if (valid) {
            editText.setError(null);
        } else {
            editText.setError(context.getString(R.string.warn_invalid_email));
            editText.requestFocus();
        }
        return valid;
    }

    public static void openCustomTab(Activity mActivity, String url, @ColorInt int toolbarColor) {

        if (isOnline(mActivity, true)) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(toolbarColor);
            builder.setShowTitle(true);
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(mActivity, Uri.parse(url));
        }
    }

    public static void openCustomTab(Activity mActivity, String url, @ColorInt int toolbarColor, @NonNull Bitmap closeButtonIcon) {

        if (isOnline(mActivity, true)) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(toolbarColor);
            builder.setShowTitle(true);
            builder.setCloseButtonIcon(closeButtonIcon);
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(mActivity, Uri.parse(url));
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
