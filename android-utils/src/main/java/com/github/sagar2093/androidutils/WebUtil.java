package com.github.sagar2093.androidutils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;

import static com.github.sagar2093.androidutils.Util.isOnline;

/**
 * Created by Sagar Chapagain on 9/17/2017.
 * https://github.com/sagar2093
 * sagar2093@gmail.com
 */

public class WebUtil {
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
}
